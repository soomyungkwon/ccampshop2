package org.zerock.club.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;

@Log4j2
public class JWTUtil {
    private String secretKey="zerock12345678zerock12345678zerock12345678zerock12345678zerock12345678zerock12345678";

    private long expire = 60 * 24 * 30;

    //    JWT 토큰 생성
    public String generateToken(String content) throws Exception {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(expire).toInstant()))
                //.setExpiration(Date.from(ZonedDateTime.now().plusSeconds(1).toInstant()))
                .claim("sub",content)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    public String validateAndExtract(String tokenStr) throws Exception{
        String contentValue=null;
        try {
            Jws  jws = (Jws) Jwts.parser()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(tokenStr);

            log.info(jws);
            log.info(jws.getBody().getClass());

            Claims claims = (Claims) jws.getBody();

            log.info("-----------------------------------------");

            contentValue = claims.getSubject();

        } catch (Exception e){
            e.printStackTrace();

            contentValue = null;
        }
        return contentValue;
    }

}
