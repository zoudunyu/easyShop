package com.yj.controller;

import com.yj.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 捕获异常控制器
 *
 * @author zdy
 * @date 2022/01/05
 */
@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MyException.class)
    public ResponseEntity<String> handleException(MyException e) {
        // 返回I_AM_A_TEAPOT（和网关约定好，这个就是登录错误，提供锁定IP等操作）
        return new ResponseEntity<>(e.getTips(), e.getStatus());
    }
}
