package com.form.fiche.filter;


import com.form.fiche.constantes.SecurityConstantes;
import com.form.fiche.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JwtTokenValidatorFilter extends OncePerRequestFilter {
    private final JwtUtil jwtProvider;
     public JwtTokenValidatorFilter(JwtUtil jwtProvider){
         this.jwtProvider=jwtProvider;
     }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return  request.getServletPath().startsWith("/api/") ;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String accessToken= jwtProvider.extractTokenFromRequest(request);
            if(accessToken!=null){
                try{
                    SecretKey secret= Keys.hmacShaKeyFor(SecurityConstantes.JWT_KEY.getBytes(StandardCharsets.UTF_8));
                    Claims claims= Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(accessToken).getBody();
                    String username=String.valueOf(claims.get("username"));
                    String authorities = (String) claims.get("authorities");
                    Authentication authentication = new UsernamePasswordAuthenticationToken(username,null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }catch (Exception exc){
                 throw  new BadCredentialsException("Invalid Token received !");

                }
            }
        filterChain.doFilter(request,response);
    }


}
