package com.chs.security;

import ch.qos.logback.core.subst.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtHelper {
    public static final long JWT_TOKEN_VALIDITY = 5*60*60;
//    @Value("${jwt.secret}") // Read the secret from an environment variable
//    private String secret;
//
//    @PostConstruct
//    public void init() {
//        if (secret.isEmpty()) {
//            secret = generateSecretKey();
//        }
//    }
//
//    private String generateSecretKey() {
//        byte[] secretBytes = new byte[64];
//        new SecureRandom().nextBytes(secretBytes);
//        return Base64.getEncoder().encodeToString(secretBytes);
//    }
//    public String getSecret() {
//        return secret;
//    }

    private String secret ="ZXlKMGVYQWlPaUpLVjFRaUxDSmhiR2NpT2lKSVV6STFOaUo5LmV5SnBjM01pT2lKUGJteHBibVVnU2xkVUlFSjFhV3hrWlhJaUxDSnBZWFFpT2pFMk9USTNPRFk1TkRBc0ltVjRjQ0k2TVRjeU5ETXlNamswTUN3aVlYVmtJam9pZDNkM0xtVjRZVzF3YkdVdVkyOXRJaXdpYzNWaUlqb2lhbkp2WTJ0bGRFQmxlR0Z0Y0d4bExtTnZiU0lzSWtkcGRtVnVUbUZ0WlNJNklrcHZhRzV1ZVNJc0lsTjFjbTVoYldVaU9pSlNiMk5yWlhRaUxDSkZiV0ZwYkNJNkltcHliMk5yWlhSQVpYaGhiWEJzWlM1amIyMGlMQ0pTYjJ4bElqcGJJazFoYm1GblpYSWlMQ0pRY205cVpXTjBJRUZrYldsdWFYTjBjbUYwYjNJaVhYMC5KdldSRU5ITEdJMlpuMFJfbzJOSGppWHExS2F6Wl9oQVB0aHNneVRiTGkw";
    public String getUserNameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token,Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }
    private String doGenerateToken(Map<String,Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000))
                .signWith(SignatureAlgorithm.HS256,secret).compact();
    }
    public Boolean validateToken(String token,UserDetails userDetails){
        final String username = getUserNameFromToken(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }



}
