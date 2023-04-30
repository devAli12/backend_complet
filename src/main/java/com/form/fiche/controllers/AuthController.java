package com.form.fiche.controllers;

import com.form.fiche.constantes.SecurityConstantes;
import com.form.fiche.dto.*;
import com.form.fiche.entities.User;
import com.form.fiche.services.UserService;
import com.form.fiche.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;

    private final JwtUtil jwtProvider;

    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService , JwtUtil jwtProvider , AuthenticationManager authenticationManager) {
        this.userService=userService;
        this.jwtProvider=jwtProvider;
        this.authenticationManager=authenticationManager;
    }
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
            userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<Map<String, Object>> register(@RequestBody UserLoginDto userLogin){
        Authentication authentication = new UsernamePasswordAuthenticationToken(userLogin.getEmail(),userLogin.getPassword());
     Authentication authResult;
     try{
         authResult= authenticationManager.authenticate(authentication);
     }catch (AuthenticationException exc){
         throw new RuntimeException("Authentication failed");
     }
     final User user = (User) authResult.getPrincipal();
     JwtToken accessToken = jwtProvider.generateToken(user,7*24*30*30);
     JwtToken refreshToken = jwtProvider.generateToken(user,15*24*30*30);
        UserDataDto userData = UserDataDto.builder().email(user.getEmail()).fullName(user.getFullName()).id(user.getId()).build();
     Map<String,Object> map=new HashMap<>();
     map.put("accessToken",accessToken);
     map.put("refreshToken",refreshToken);
     map.put("user",userData);
     return ResponseEntity.status(HttpStatus.OK).body(map);
    }
    @GetMapping("/token/refresh")
    public ResponseEntity<Map<String,JwtToken>> refreshToken(HttpServletRequest request ){
         Map<String,JwtToken> map = new HashMap<>();
        try{
            String refreshToken = jwtProvider.extractTokenFromRequest(request);
            SecretKey key = Keys.hmacShaKeyFor(SecurityConstantes.JWT_KEY.getBytes(StandardCharsets.UTF_8));
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(refreshToken).getBody();
            User user= userService.findByEmail(String.valueOf(claims.get("username")));
            JwtToken accessToken= jwtProvider.generateToken(user,7*24*30*30);
            JwtToken refresh_Token=jwtProvider.generateToken(user,15*24*30*30);
            map.put("accesstoken",accessToken);
            map.put("refreshToken",refresh_Token);
        }catch (Exception excep){
            excep.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }
   @PostMapping("/resetPassword")
    public void resetPassword(@RequestBody ResetPasswordDto resetPasswordDto){
        userService.resetPassword(resetPasswordDto.getEmail());
        System.out.println("resePasswordController");
   }
   @PostMapping("/resetPassword/confirmation")
    public ResponseEntity<String> confirm(@RequestBody ResetPasswordDtoConfirmation object){

        try{
            userService.confirmToken(object.getToken());
         }catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed");
         }

        try{
            userService.updatePassword(object.getEmail(), object.getNewPassword());
         }catch (RuntimeException exc){
            return ResponseEntity.status(HttpStatus.CREATED).body("failed updating");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("confirmed");
   }

}
