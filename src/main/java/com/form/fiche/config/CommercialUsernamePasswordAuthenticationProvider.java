package com.form.fiche.config;


import com.form.fiche.entities.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommercialUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    PasswordEncoder passwordEncoder;
    UserDetailsService userDetailsService;

    public CommercialUsernamePasswordAuthenticationProvider(PasswordEncoder passwordEncoder , UserDetailsService userDetailsService){
        this.passwordEncoder=passwordEncoder;
        this.userDetailsService=userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username= authentication.getName();
        String password= authentication.getCredentials().toString();
        User user =(User) userDetailsService.loadUserByUsername(username);
        if(user!=null){
            if(passwordEncoder.matches(password,user.getPassword())){
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(user.getRole()));
                return new UsernamePasswordAuthenticationToken(user,password,authorities);
            }else{
                throw new BadCredentialsException("Invalid password !");
            }
        }else{
            throw new BadCredentialsException("No user registered with this details !");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
