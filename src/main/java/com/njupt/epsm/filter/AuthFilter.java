package com.njupt.epsm.filter;


import com.njupt.epsm.common.JwtTokenUtil;
import com.njupt.epsm.common.JwtUserInfo;
import com.njupt.epsm.util.RestUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) {
        try {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;
            // 设置允许跨域请求的来源
            response.setHeader("Access-Control-Allow-Origin", "*");
            // 允许使用的方法
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            // 允许的自定义请求头
            response.setHeader("Access-Control-Allow-Headers", "*");
            // 设置是否允许请求带有身份信息（例如cookie）
            response.setHeader("Access-Control-Allow-Credentials", "true");

            String token = StringUtils.isNotBlank(request.getHeader("authorization")) ?
                    request.getHeader("authorization").substring("Bearer ".length()) : null;
            if (request.getMethod().equals(HttpMethod.OPTIONS.toString())) {
                chain.doFilter(request, response);
                return;
            }
            //白名单请求直接放行
            PathMatcher pathMatcher = new AntPathMatcher();
            for (String path : ignoreUrlsConfig.getUrls()) {
                if (pathMatcher.match(path, request.getRequestURI())) {
                    chain.doFilter(request, response);
                    return;
                }
            }
            if (token != null) {
                if (jwtTokenUtil.getClaimsFromToken(token) == null) {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    RestUtil.printData(response, "token错误");
                    return;
                }
                if (!jwtTokenUtil.validateToken(token)) {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    RestUtil.printData(response, "token已过期");
                    return;
                }
                JwtUserInfo jwtUserInfo = jwtTokenUtil.getUserNameFromToken(token);
                if (jwtUserInfo == null) {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    RestUtil.printData(response, "token无效");
                    return;
                }
            } else {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                RestUtil.printData(response, "header中无授权信息");
                return;
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            logger.error("doFilter error", e);
        }
    }


}
