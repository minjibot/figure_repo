package com.study.figure.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.study.figure.dto.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenProvider {
    // JWT 생성 및 검증을 위한 키
    private static final String SECURITY_KEY = "jwtkeyisfigure";

    /**
     * JWT 생성 매서드
     * @param user
     * @return
     */
    public String createToken (User user) {
        if(user == null || user.validation(true)) return "";

        String id = Long.toString(user.getUserId());
        
        // 비밀번호 정보 초기화
        user.setPassword("");
        user.setSalt("");

        // 토큰 만료 날짜 현재 날짜 + 1시간 설정
        Date exprTime = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
        // JWT를 생성
        return Jwts.builder()
                // 암호화 알고리즘 및 키 세팅
                .signWith(SignatureAlgorithm.HS512, SECURITY_KEY)
                // jwt 제목과 생성일, 만료일 세팅
                .setSubject(id).setIssuedAt(new Date()).setExpiration(exprTime)
                // 유저 정보
                .setHeader(new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).convertValue(user, Map.class))
                // 생성
                .compact();
    }

    /**
     * JWT 검증
     * @param token
     * @return
     */
    public String validate (String token) {
        // 토큰을 검증 키를 이용하여 복호화 (디코딩)
        Claims claims = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody();
        // 복호화 된 토큰의 payload에서 제목 가져오기
        return claims.getSubject();
    }
}
