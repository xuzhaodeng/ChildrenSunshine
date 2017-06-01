package com.pas.edu.utils;

import com.pas.edu.entity.TokenInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;


/**
 * Author : eric
 * CreateDate : 2017/5/19  13:43
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :  token工具类
 * Modified :
 */
public class JwtUtils {
    public static final String KEY = "dG9rZW4xMjM0NTY=";
    //token有效时间，单位s
    private static final long EXPIRE_TIME = 60 * 60;

    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(KEY))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            return null;
        }
    }

    public static TokenInfo createJWT(String userId) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .claim("userId", userId)
                .setIssuer("issuer")
                .setAudience("audience")
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        long expMillis = nowMillis + EXPIRE_TIME * 1000;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp).setNotBefore(now);

        //生成JWT
        String token = builder.compact();
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setToken(token);
        tokenInfo.setExpireTime(EXPIRE_TIME);
        return tokenInfo;
    }
}
