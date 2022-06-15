/*
package com.yj.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yj.config.SecretConfig;
import com.yj.enums.UserTypeEnum;
import com.yj.exception.MyException;
import com.yj.model.vo.TokenVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Component
public class RequestFilter implements Filter {

    private static final String MSG_REQUIRE_AUTH = "请提供授权认证信息";
    private static final String MSG_AUTH_INVALID = "授权认证失败，请重新获取授权";
    private static final String MSG_PERMISSION_INVALID = "权限不足";

    */
/** 拦截的URL *//*

    String[] includeUrls = new String[] {"/Login", "/Register"};

    */
/**
     * 初始化 filter对象只会创建一次，init方法也只会执行一次。
     *
     * @param filterConfig 过滤器配置
     * @throws ServletException servlet异常
     *//*

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    */
/**
     * 拦截请求过滤器
     *
     * @param servletRequest servlet请求
     * @param response servlet响应
     * @param chain 过滤器链
     * @throws IOException ioexception
     * @throws ServletException servlet异常
     *//*

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        try {
            // 1.获取访问路径
            String requestURI = request.getRequestURI();
            log.info("访问URL:{}", requestURI);
            if (isNeedFilter(requestURI)) {
                // 2.获取token
                String token = request.getHeader("Authorization");
                // 3.token为空，则抛出异常
                if (StringUtils.isBlank(token)) {
                    throw new MyException(MSG_REQUIRE_AUTH, "Token验证不通过", HttpStatus.UNAUTHORIZED);
                }
                // 4.验证token是否有效
                TokenVo tokenDataValue = getTokenDataValue(token);
                // 验证失败，则抛出异常
                if (!tokenDataValue.isValid()) {
                    throw new MyException(MSG_AUTH_INVALID, "Token验证不通过", HttpStatus.UNAUTHORIZED);
                }
                if (tokenDataValue.getUserType().equals(UserTypeEnum.admin.getMsg())) {
                    chain.doFilter(servletRequest, response);
                }
            }
            chain.doFilter(servletRequest, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    */
/**
     * 是需要过滤访问路径
     *
     * @param uri uri
     * @return boolean
     *//*

    private boolean isNeedFilter(String uri) {
        for (String includeUrl : includeUrls) {
            if (uri.contains(includeUrl)) {
                return false;
            }
        }

        return true;
    }

    */
/**
     * 解析token，获得令牌数据值
     *
     * @param token 令牌
     * @return {@link TokenVo}
     *//*

    private TokenVo getTokenDataValue(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SecretConfig.SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(SecretConfig.ISSUER).build();
            DecodedJWT jwt = verifier.verify(token);
            return TokenVo.success(
                    jwt.getClaim("username").asString(), jwt.getClaim("userType").asString());
        } catch (JWTVerificationException exception) {
            // Invalid signature/claims
            log.error("授权验证失败：{}", exception.getMessage());
            return TokenVo.failed("授权验证失败");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
*/
