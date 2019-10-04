package com.learn.idea_ws_demo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * token验证的工具类
 * @author Administrator
 */
public class JwtUtil {
    /**
     * 定义过期时间 15分钟
     */
    private static final long EXPIRE_TIME = 15 * 60 * 1000;

    /**
     * token的私钥
     * 用于加密和解密
     */
    private static final String TOKEN_SECRET = "8f7064aef4924dba8d2dc0fe60703ce5";

    /**
     * 生成签名方法
     * @param username
     * @param userId
     * @return
     */
    public static String sign(String username, String userId){
        try {
            //过期时间(是一个时间点,现在时间+15钟就是过期时间)
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            //加密算法
            //签名信息
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "JWT");
            //算法是否是固定的???(HS256)
            header.put("alg", "HS256");

            //返回token
            return JWT.create()
                    .withHeader(header)
                    .withClaim("loginName", username)
                    .withClaim("userId", userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 验证token是否合法
     * @param token
     * @return
     */
    public static boolean verify(String token){
        try {
            //这两段代码获取一个解码器
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            //利用解密器对token进行验证,如果没有异常,说明验证成功
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    /**
     * 获取token里面的username
     * @param token
     * @return
     */
    public static String getUsername(String token){
        String name = null;
        try {
            DecodedJWT jwt = JWT.decode(token);
            name = jwt.getClaim("loginName").asString();
        } catch (JWTDecodeException e) {
        }
        return name;
    }

    /**
     * 获取token里面的UserId
     * @param token
     * @return
     */
    public static String getUserId(String token){
        String UserId = null;
        try {
            DecodedJWT jwt = JWT.decode(token);
            UserId = jwt.getClaim("userId").asString();
        } catch (JWTDecodeException e) {
        }
        return UserId;
    }
}








