package com.github.mrag.helloim.security;

import com.github.mrag.helloim.common.DateTimeUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public final class HttpTokenUtils {

    private static final String BASE64_ENCODED_KEY = TextCodec.BASE64.encode("mrag.io");

    public String tokenSerialize(HttpToken token) {
        return Jwts.builder()
                   .setId(token.getUserId().toString())
                   .setSubject(token.getUsername())
                   .setIssuedAt(DateTimeUtils.localDateTime2Date(token.getIssuedAt()))
                   .claim("before_expiration", DateTimeUtils.format(token.getBeforeExpiration()))
                   .setExpiration(DateTimeUtils.localDateTime2Date(token.getExpiration()))
                   .signWith(SignatureAlgorithm.HS256, BASE64_ENCODED_KEY)
                   .compact();
    }

    public HttpToken tokenDeserialize(String tokenString) {
        HttpToken inst = new HttpToken();
        try {
            Claims claims = Jwts.parser()
                                .setSigningKey(BASE64_ENCODED_KEY)
                                .parseClaimsJws(tokenString)
                                .getBody();
            inst.setUserId(Integer.parseInt(claims.getId()));
            inst.setUsername(claims.getSubject());
            inst.setIssuedAt(DateTimeUtils.date2LocalDateTime(claims.getIssuedAt()));
            inst.setExpiration(DateTimeUtils.date2LocalDateTime(claims.getExpiration()));
            inst.setBeforeExpiration(DateTimeUtils.parse(claims.get("before_expiration", String.class)));
        } catch (ExpiredJwtException exp) {
            inst.setExpState(true);
        }
        return inst;
    }

    public String isNeedUpdate(HttpToken tokenInst) {
        LocalDateTime beforeExpiration = tokenInst.getBeforeExpiration();
        if (LocalDateTime.now().isAfter(beforeExpiration)) {
            return tokenSerialize(new HttpToken(tokenInst.getUserId(), tokenInst.getUsername()));
        }
        return null;
    }
}
