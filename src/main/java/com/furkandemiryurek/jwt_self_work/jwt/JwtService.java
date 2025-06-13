package com.furkandemiryurek.jwt_self_work.jwt;

import com.furkandemiryurek.jwt_self_work.dto.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
    private static final String SECRET_KEY = "/kjNRJWqkFBMiC6WSSDzLLDOZrQAELXxRrKRXuFWRVQ=";

    public String generateToken(UserDto userDto){
        return Jwts.builder()
                .setSubject(userDto.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token, UserDto user){
        final String _username = extractUsername(token);
        return (_username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token){
        return extractExpirationDate(token).before(new Date());
    }

    public Date extractExpirationDate(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

}
