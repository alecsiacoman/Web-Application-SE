package com.example.web.security.jwt;

import com.example.web.security.userService.UserDataImpl;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private String jwtSecret = "Sd34E52#5C12RreE&7T";
    private int jwtExpirationMs = 1000 * 60 * 60 * 2; //2 ore

    public String generateJwtToken(Authentication authentication) {

        UserDataImpl userPrincipal = null;
        if(authentication.getPrincipal() instanceof UserDataImpl)
            userPrincipal = (UserDataImpl) authentication.getPrincipal();
        if(authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User)
            userPrincipal = new UserDataImpl((org.springframework.security.core.userdetails.User) authentication.getPrincipal());

        return Jwts.builder()
                .setSubject(userPrincipal != null ? userPrincipal.getEmail() : null)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
//        UserDataImpl user = (UserDataImpl)authentication.getPrincipal();
//
//        Date now = new Date();
//        return Jwts.builder()
//                .setSubject(user.getEmail())
//                .setIssuedAt(now)
//                .setExpiration(new Date(now.getTime() + jwtExpirationMs))
//                .signWith(SignatureAlgorithm.HS512, jwtSecret)
//                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }
}

