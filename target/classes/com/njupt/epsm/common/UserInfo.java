package com.njupt.epsm.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class UserInfo {

    private static final Logger log = LoggerFactory.getLogger(UserInfo.class);


    public static Long getUserId(HttpServletRequest request) {
        try {
            String authToken = request.getHeader("authorization").substring("Bearer ".length());
            JwtUserInfo jwtUserInfo = JwtTokenUtil.getUserInfoFromToken(authToken);
            return jwtUserInfo != null ? Long.valueOf(jwtUserInfo.getUserId()) : -1L;
        } catch (Exception e) {
            log.error("getUserId error", e);
        }
        return -1L;
    }

    public static Long getRoleId(HttpServletRequest request) {
        try {
            String authToken = request.getHeader("authorization").substring("Bearer ".length());
            JwtUserInfo jwtUserInfo = JwtTokenUtil.getUserInfoFromToken(authToken);
            return jwtUserInfo != null ? Long.valueOf(jwtUserInfo.getRoleId()) : -1L;
        } catch (Exception e) {
            log.error("getRoleId error", e);
        }
        return -1L;
    }

    public static String getUserName(HttpServletRequest request) {
        try {
            String authToken = request.getHeader("authorization").substring("Bearer ".length());
            JwtUserInfo jwtUserInfo = JwtTokenUtil.getUserInfoFromToken(authToken);
            return jwtUserInfo != null ? jwtUserInfo.getUserName() : "";
        } catch (Exception e) {
            log.error("getUserName error", e);
        }
        return "";
    }

}
