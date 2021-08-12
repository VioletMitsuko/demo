package com.example.system.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.system.domain.User;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author VioletMitsuko
 */
@Component
public class TokenService {
    /**
     * 设置过期时间,30分钟 30 * 60 * 1000
     */
    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    /**
     *  公共密匙
     */
    private static final String SECRED = "VioletMitsuko";

    /**
     * 生成Token
     * @param user  用户Id
     * @return 返回token
     */
    public static String getToken(User user, List<String> menuName) throws Exception{

        /**
         *  生成头部信息
         */
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        /**
         *  过期时间
         */
        Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);

        /**
         * 签发时间
         */
        Date issuedAt = new Date();

        return JWT.create()
                .withHeader(map)
                .withClaim("userId",user.getId())  //  设置可用信息
                .withClaim("userName",user.getName())  //  设置可用信息
                .withClaim("menuName",menuName)  //  设置可用信息
                .withExpiresAt(expiresAt)    //设置过期时间
                .withIssuedAt(issuedAt)  //设置签发时间
                .sign(Algorithm.HMAC256(SECRED));
    }

    /**
     * 验证Token
     * @param token token
     * @return  .
     * @throws Exception 抛出异常
     */
    public static Map<String, Claim> verifierToken(String token) throws Exception{
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRED)).build();
        DecodedJWT jwt = null;
        jwt = verifier.verify(token);
        return jwt.getClaims();
    }

    /**
     * 解析Token
     * @param token token
     * @return  。
     */
    public static Map<String, Claim> parseToken(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaims();
    }
}
