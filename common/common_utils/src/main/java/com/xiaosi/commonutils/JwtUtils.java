package com.xiaosi.commonutils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 顾育司
 */
public class JwtUtils {
    //过期时间
    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    //加密令牌
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    //给定id 和名字 创建一个jwt
    public static String getJwtToken(String id, String nickname){
        String JwtToken = Jwts.builder()
                //头 描述JWT元数据的JSON对象
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //有效载荷  JWT的主体内容部分，也是一个JSON对象
                .setSubject("guli-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("id", id)
                .claim("nickname", nickname)
                //防伪码（签名） 签名哈希部分是对上面两部分数据签名，通过指定的算法生成哈希，以确保数据不会被篡改
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
        return JwtToken;
    }
        /**
         * 判断token是否存在与有效
         * @param jwtToken
         * @return
         */
    public static boolean checkToken(String jwtToken) {
        if(StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
        /**
         * 判断token是否存在与有效
         * @param request
         * @return
         */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if(StringUtils.isEmpty(jwtToken)) return false;
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
        /**
         * 根据token获取会员id
         * @param request
         * @return
         */
    public static String getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if(StringUtils.isEmpty(jwtToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("id");
    }
}
