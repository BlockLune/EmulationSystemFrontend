package com.njupt.epsm.common;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liangwei
 * @date 2022/10/06
 */
@Component
public class JwtTokenUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    private static final String secret = "writedog!@#";
    private static final int expiration = 36000;

    private static String secretTmp;

    @PostConstruct
    public void init() {
        secretTmp = secret;
    }

    /**
     * 根据负责生成JWT的token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从token中获取登录用户名
     */
    public static JwtUserInfo getUserInfoFromToken(String token) {
        JwtUserInfo jwtUserInfo;
        String subject = null;
        try {
            Claims claims = null;
            try {
                claims = Jwts.parser()
                        .setSigningKey(secretTmp)
                        .parseClaimsJws(token)
                        .getBody();
            } catch (Exception e) {
                LOGGER.error("JWT格式验证失败:{}", e);
            }
            subject = claims.getSubject();
            jwtUserInfo = JSON.parseObject(subject, JwtUserInfo.class);
        } catch (Exception e) {
            LOGGER.error("parseObject:{}", subject);
            jwtUserInfo = null;
        }
        return jwtUserInfo;
    }

    /**
     * 从token中获取登录用户名
     */
    public static JwtUserInfo getUserInfoFromExpireToken(String token) {
        JwtUserInfo jwtUserInfo;
        String subject = null;
        try {
            Claims claims = null;
            try {
                claims = Jwts.parser()
                        .setSigningKey(secretTmp)
                        .parseClaimsJws(token)
                        .getBody();
            } catch (ExpiredJwtException e) {
                claims = e.getClaims();
            } catch (Exception e) {
                LOGGER.error("JWT格式验证失败:{}", e);
            }
            subject = claims.getSubject();
            jwtUserInfo = JSON.parseObject(subject, JwtUserInfo.class);
        } catch (Exception e) {
            LOGGER.error("parseObject:{}", subject);
            jwtUserInfo = null;
        }
        return jwtUserInfo;
    }

    public static JwtUserInfo allowTokenExpire(String token) {
        JwtUserInfo jwtUserInfo;
        String subject = null;
        try {
            Claims claims = null;
            try {
                claims = Jwts.parser()
                        .setSigningKey(secretTmp)
                        .parseClaimsJws(token)
                        .getBody();
            } catch (Exception e) {
                LOGGER.error("JWT格式验证失败:{}", e);
            }
            subject = claims.getSubject();
            jwtUserInfo = JSON.parseObject(subject, JwtUserInfo.class);
        } catch (Exception e) {
            LOGGER.error("parseObject:{}", subject);
            jwtUserInfo = null;
        }
        return jwtUserInfo;
    }

    /**
     * 从token中获取JWT中的负载
     */
    public Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.info("JWT格式验证失败:{}", token);
        }
        return claims;
    }

    /**
     * 从token中获取登录用户名
     */
    public JwtUserInfo getUserNameFromToken(String token) {
        JwtUserInfo jwtUserInfo;
        try {
            Claims claims = getClaimsFromToken(token);
            String subject = claims.getSubject();
            jwtUserInfo = JSON.parseObject(subject, JwtUserInfo.class);
        } catch (Exception e) {
            jwtUserInfo = null;
        }
        return jwtUserInfo;
    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }


    /**
     * 根据用户信息生成token
     */
    public String generateToken(JwtUserInfo jwtUserInfo) {
        Map<String, Object> claims = new HashMap<>(8);
        claims.put(CLAIM_KEY_USERNAME, JSON.toJSONString(jwtUserInfo));
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    public String generateRefreshToken() {
        Map<String, Object> claims = new HashMap<>(8);
        claims.put(CLAIM_KEY_USERNAME, "");
        claims.put(CLAIM_KEY_CREATED, new Date());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 验证token是否还有效
     *
     * @param token 客户端传入的token
     */
    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }


    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }


}
