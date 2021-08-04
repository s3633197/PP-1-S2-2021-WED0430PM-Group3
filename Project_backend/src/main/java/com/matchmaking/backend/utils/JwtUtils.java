package com.matchmaking.backend.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
@ConfigurationProperties(prefix = "token.jwt")
public class JwtUtils {

    private String secret;

    private long expire;

    private String header;


    public String generateToken(String email){
        Date createDate = new Date();
        Date expireTime = new Date(createDate.getTime()+1000*expire);
        System.out.println(expireTime);
        System.out.println(createDate);

        return   Jwts.builder()
                .setHeaderParam("type","JWT")
                .setSubject(email)
                .setIssuedAt(createDate)
                .setExpiration(expireTime)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();

    }


    public Claims getClaimByToken(String jwt){
        return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJwt(jwt)
                    .getBody();
    }

    public boolean isTokenExpired(Claims claims){
        return claims.getExpiration().before(new Date());
    }

}
