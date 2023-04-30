package com.form.fiche.utils;

/*import com.commercial.commercial.constantes.SecurityConstantes;
import com.commercial.commercial.dto.JwtToken;
import com.commercial.commercial.model.User;*/
import com.form.fiche.constantes.SecurityConstantes;
import com.form.fiche.dto.JwtToken;
import com.form.fiche.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Component
public class JwtUtil {
    public JwtToken generateToken(User user, int expiration) throws RuntimeException {
        Instant creationDate = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant();
        Instant expiryDate = creationDate.plusSeconds(expiration);


        SecretKey key = Keys.hmacShaKeyFor(SecurityConstantes.JWT_KEY.getBytes(StandardCharsets.UTF_8));
        String token = Jwts.builder().setIssuer("Commerciaux-app").setSubject("JWT Token")
                .claim("username", user.getEmail())
                .claim("authorities", populateAuthorities(user.getAuthorities()))
                .setIssuedAt(Date.from(creationDate))
                .setExpiration(Date.from(expiryDate))
                .signWith(key).compact();

        return JwtToken.builder().createdAt(creationDate).expireIn(expiryDate).token(token).build();

    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }

    public String extractTokenFromRequest(HttpServletRequest request) throws RuntimeException {
        final String bearerPrefix = "Bearer ";

        String authorizationHeader = request.getHeader(SecurityConstantes.JWT_HEADER);
        if (authorizationHeader == null || authorizationHeader.isEmpty())
            throw new RuntimeException();

        if (!authorizationHeader.startsWith(bearerPrefix))
            throw new RuntimeException();

        String token = authorizationHeader.replace(bearerPrefix, Strings.EMPTY);
        if (token == null || token.isEmpty())
            throw new RuntimeException();

        return token;
    }
}
