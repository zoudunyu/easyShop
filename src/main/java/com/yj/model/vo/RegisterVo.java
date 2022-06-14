package com.yj.model.vo;

import com.yj.exception.MyException;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
public class RegisterVo implements Serializable {

    private String username;

    private String password;

    private String name;

    private Long t;

    public void setUsername(String username) {
        if (ObjectUtils.isEmpty(username)) {
            throw new MyException("用户名不能为空", "注册异常", HttpStatus.BAD_REQUEST);
        }
        this.username = username;
    }

    public void setPassword(String password) {
        if (ObjectUtils.isEmpty(password)) {
            throw new MyException("密码不能为空", "注册异常", HttpStatus.BAD_REQUEST);
        }
        this.password = password;
    }

    public void setName(String name) {
        if (ObjectUtils.isEmpty(name)) {
            throw new MyException("姓名不能为空", "注册异常", HttpStatus.BAD_REQUEST);
        }
        this.name = name;
    }
}
