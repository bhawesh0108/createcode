package com.bhawesh.createCode.security;

import com.bhawesh.createCode.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

@Component
public class AuthUtil {

    @Value("${jwt.security.secret-key}")
    private String jwtSecretKey;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user){
        return Jwts.builder().setSubject(user.getId().toString())
                .claim("username",user.getUsername())
                .claim("name",user.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*10))
                .signWith(getSecretKey())
                .compact();
    }

    public JwtUserPrincipal getUserFromToken(String token){
        Claims claims = Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).getBody();
        Long userId = Long.parseLong(claims.getSubject());
        String userName = claims.get("username").toString();
        return new JwtUserPrincipal(userId,userName,new ArrayList<>());
    }
}
