package com.yj.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * 通用异常类
 *
 * @author zdy
 * @date 2022/01/05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MyException extends RuntimeException {

    private final String tips;

    private final String type;

    private final HttpStatus status;

    public MyException(String tips, String type, HttpStatus status) {
        this.tips = tips;
        this.type = type;
        this.status = status;
    }
}
