package com.example.barkamol_avlod.config;

import com.example.barkamol_avlod.dto.UserDto;
import com.example.barkamol_avlod.entity.Users;
import com.example.barkamol_avlod.entity.UserSession;
import com.example.barkamol_avlod.repository.UserSessionRepository;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtService {
    @Value("${spring.security.secret.key}")
    public String secretKey;
    @Autowired
    private Gson gson;
    @Autowired
    private UserSessionRepository userSessionRepository;

    public String generateToken(Users users) {
        String uuid = UUID.randomUUID().toString();
        userSessionRepository.save(new UserSession(uuid,gson.toJson(users)));
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .setSubject(uuid)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
    public boolean isExpired(String token) {
        return getClaims(token).getExpiration().getTime() < System.currentTimeMillis();
    }
    public UserDto getSubject(String token){
        String uuid = getClaims(token).getSubject();
        return userSessionRepository.findById(uuid).map(s -> gson.fromJson(s.getUserInfo(), UserDto.class))
                .orElseThrow(() -> new JwtException("Token is expired"));
    }
}
