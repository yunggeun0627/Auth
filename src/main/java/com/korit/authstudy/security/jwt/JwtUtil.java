package com.korit.authstudy.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key KEY;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public boolean isBearer(String token) {
        if (token == null) {
            return false;
        }
        if (!token.startsWith("Bearer ")) {
            return false;
        }
        return true;
    }

    public String removeBearer(String bearerToken) {
        return bearerToken.replaceFirst("Bearer ", "");
    }

    public String generateAccessToken(String id) {
        JwtBuilder jwtBuilder = Jwts.builder(); // 토큰생성에 필요한 정보를 입력
        jwtBuilder.subject("AccessToken");
        jwtBuilder.id(id);
        Date expirDate = new Date(new Date().getTime() + (1000l * 60l * 60l * 24l * 30l));
        jwtBuilder.expiration(expirDate);
        jwtBuilder.signWith(KEY);
        String token = jwtBuilder.compact();    // 입력된 정보로 문자열 JWT토큰 생성
        System.out.println(token);
        return token;
    }

    public Claims getClaims(String token) throws JwtException {
        JwtParserBuilder jwtParserBuilder = Jwts.parser();
        jwtParserBuilder.setSigningKey(KEY);
        JwtParser jwtParser = jwtParserBuilder.build();
        return jwtParser.parseClaimsJws(token).getPayload();
    }
}
