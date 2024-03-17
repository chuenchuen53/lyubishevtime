package com.example.lyubishevtime.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@Data
@ConfigurationProperties(prefix = "jwt.token")
public class JwtHelper {
    private long tokenExpiration;
    private String tokenSignKey;

    public String createToken(Long userId) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(tokenSignKey));
        return Jwts.builder()
                .subject(userId.toString())
                .expiration(new Date(System.currentTimeMillis() + tokenExpiration * 1000 * 60))
                .signWith(key)
                .compressWith(Jwts.ZIP.GZIP)
                .compact();
    }

    public Long getUserId(String authorizationHeader) {
        String token = authorizationHeader.substring(7); // Remove "Bearer " prefix
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(tokenSignKey));
        Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
        String subject = claimsJws.getPayload().getSubject();
        return Long.parseLong(subject);
    }

    public boolean isExpiration(String authorizationHeader) {
        try {
            String token = authorizationHeader.substring(7); // Remove "Bearer " prefix
            if (!StringUtils.hasText(token)) {
                return true;
            }
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(tokenSignKey));
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getExpiration()
                    .before(new Date());
        } catch (Exception e) {
            return true;
        }
    }
}