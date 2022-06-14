package com.yj.service;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.yj.config.SecretConfig;
import com.yj.dao.master.service.UserInfoService;
import com.yj.enums.UserTypeEnum;
import com.yj.exception.MyException;
import com.yj.model.dto.LoginDto;
import com.yj.model.vo.LoginVo;
import com.yj.util.EnumUtil;
import com.yj.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class LoginService {

    private final SecretConfig secretConfig;

    private final UserInfoService userInfoService;

    /** 服务器和客户端验证的时间差（默认10分钟） */
    private static final long CHECK_TIME_MILLIS = 600000;

    /**
     * 登录
     *
     * @param encryptStr 登录加密后的字符
     * @return {@link String}
     */
    public String signIn(String encryptStr) {
        return signInDecrypt(encryptStr);
    }

    private String signInDecrypt(String encryptStr) {
        String json = JwtUtil.decrypt(encryptStr);
        Optional.ofNullable(json)
                .orElseThrow(() -> new MyException("登录信息格式错误", "登录异常", HttpStatus.BAD_REQUEST));
        LoginVo loginVo = JSON.parseObject(json, LoginVo.class);
        return getToken(signForUserSign(loginVo));
    }

    private LoginDto signForUserSign(LoginVo loginVo) {
        if (Math.abs(System.currentTimeMillis() - loginVo.getT()) > CHECK_TIME_MILLIS) {
            log.info("服务器与客户端时差验证超过容许范围：{}", loginVo.getUsername());
            throw new MyException("服务器与客户端时差验证超过容许范围", "登录异常", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LoginDto loginDto =
                userInfoService.beanToDto(userInfoService.findByUsername(loginVo.getUsername()));
        if (ObjectUtils.isEmpty(loginDto)) {
            throw new MyException("用户不存在", "登录异常", HttpStatus.BAD_REQUEST);
        }
        if (!loginDto.getPassWord().equalsIgnoreCase(loginVo.getPassword())) {
            throw new MyException("账号密码错误", "登录异常", HttpStatus.BAD_REQUEST);
        }
        return loginDto;
    }

    private String getToken(LoginDto result) {
        // 发行时间提前十分钟，解决服务器时间差导致的无法认证的问题
        Date startDate = new Date(System.currentTimeMillis() - 10 * 60 * 1000);
        // 令牌为7天过期
        Date expDate =
                new Date(
                        System.currentTimeMillis()
                                + (long) secretConfig.getExpDays() * 24 * 60 * 60 * 1000);
        Algorithm algorithmHs = Algorithm.HMAC256(secretConfig.getSecret());
        String token =
                JWT.create()
                        .withClaim("username", result.getUsername())
                        .withClaim(
                                "userType",
                                EnumUtil.getByCode(result.getUserType(), UserTypeEnum.class))
                        .withIssuer(secretConfig.getIssuer())
                        .withIssuedAt(startDate)
                        .withExpiresAt(expDate)
                        .sign(algorithmHs);
        return token;
    }
}
