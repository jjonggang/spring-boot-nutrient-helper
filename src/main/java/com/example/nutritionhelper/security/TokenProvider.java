package com.example.nutritionhelper.security;

import com.example.nutritionhelper.domain.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
public class TokenProvider {
    @Value("${secret.key}")
    private String SECRET_KEY;

    public String create(User user){
        // 기한 설정
        Date expiryDate = Date.from(
                Instant.now()
                        .plus(30, ChronoUnit.DAYS)
                );
        return Jwts.builder()
                // SECRET_KEY
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                //payload 내용
                .setSubject(String.valueOf(user.getUserId()))
                .setIssuer("jk")
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .compact();
    }

    public String validateAndGetUserId(String token){

        // parseClaimsJws 메서드가 Base64로 디코딩 및 파싱
        // 헤더와 페이로드를 setSigningKey로 넘어온 시크릿을 이용해 서명한 후 token의 서명과 비교
        // 위조되지 않았다면 페이로드(Claims) 리턴, 위조라면 예외를 날림
        // 그 중 userId가 필요하므로 getBody를 부른다.
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
