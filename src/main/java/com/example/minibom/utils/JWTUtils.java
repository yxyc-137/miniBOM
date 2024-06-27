package com.example.minibom.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    private static String SIGNATURE = "token!@#$%^7890";

    /**
     * 验证token
     * @param token
     */
    public static void verify(String token){
        JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
    }

    public static String getToken(Long id, String name) {
        HashMap<String,Object> map = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,3600);
        String token = JWT.create()
                .withHeader(map) //可以不设定，就是使用默认的
                .withClaim("id",id)//payload  //自定义用户名
                .withClaim("name",name)//payload  //自定义用户名
                .withExpiresAt(instance.getTime()) //指定令牌过期时间
                .sign(Algorithm.HMAC256(SIGNATURE));//签名
        return token;
    }
}

