package com.alkemy.wallet.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtils {

    private static SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String extractUsername(String token){ return extractClaim(token, Claims::getSubject);}

    public Date extractExpiration(String token){ return extractClaim(token, Claims::getExpiration);}

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token){ return extractExpiration(token).before(new Date());}

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    public static String createToken(Map<String,Object> claims, String subject){
        Date expirationDate = new Date(System.currentTimeMillis() * 1000 * 60 * 60 * 10);
       return Jwts.builder()
               .setSubject(subject)
               .setExpiration(expirationDate)
               .addClaims(claims)
               .signWith(key)
               .compact();
    }


    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}

