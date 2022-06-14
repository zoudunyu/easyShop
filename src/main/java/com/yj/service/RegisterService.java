package com.yj.service;

import com.alibaba.fastjson.JSON;
import com.yj.dao.master.entity.UserInfo;
import com.yj.dao.master.service.UserInfoService;
import com.yj.exception.MyException;
import com.yj.model.vo.RegisterVo;
import com.yj.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class RegisterService {

    private final UserInfoService userInfoService;

    /** 服务器和客户端验证的时间差（默认10分钟） */
    private static final long CHECK_TIME_MILLIS = 600000;

    public String register(String encryptStr) {
        return signInDecrypt(encryptStr);
    }

    private String signInDecrypt(String encryptStr) {
        String json = JwtUtil.decrypt(encryptStr);
        Optional.ofNullable(json)
                .orElseThrow(() -> new MyException("注册信息格式错误", "注册异常", HttpStatus.BAD_REQUEST));
        RegisterVo registerVo = JSON.parseObject(json, RegisterVo.class);
        return signForUserSign(registerVo);
    }

    private String signForUserSign(RegisterVo registerVo) {
        if (Math.abs(System.currentTimeMillis() - registerVo.getT()) > CHECK_TIME_MILLIS) {
            log.info("服务器与客户端时差验证超过容许范围：{}", registerVo.getUsername());
            throw new MyException("服务器与客户端时差验证超过容许范围", "注册异常", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        UserInfo userInfo = userInfoService.findByUsername(registerVo.getUsername());
        if (ObjectUtils.isEmpty(userInfo)) {
            // 保存数据
            userInfoService.save(userInfoService.voToBean(registerVo));
        } else {
            throw new MyException("该用户名已被占用", "注册异常", HttpStatus.BAD_REQUEST);
        }
        return "注册成功";
    }
}
