package com.yj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 封装的接口返回数据
 *
 * <p>版本: 1.0 创建人: 邹敦宇 创建时间: 2019-04-09 10:08:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String SUCCESS_CODE = "200";
    public static final String ERROR_CODE = "500";
    public static final String UNLOGIN_CODE = "304";
    public static final String NO_INFO_CODE = "305";
    public static final String UNAUTH_CODE = "401";
    public static final String NOT_FOUND_CODE = "404";

    String code;
    String msg;
    Object data;

    public static ResponseData success() {
        return new ResponseData(SUCCESS_CODE, "ok", null);
    }

    public static ResponseData success(Object data) {
        return new ResponseData(SUCCESS_CODE, "ok", data);
    }

    public static ResponseData error() {
        return new ResponseData(ERROR_CODE, "服务器出现错误!", null);
    }

    public static ResponseData error(String msg) {
        return new ResponseData(ERROR_CODE, msg, null);
    }

    public static ResponseData unLogin() {
        return new ResponseData(UNLOGIN_CODE, "请重新登陆!", null);
    }

    public static ResponseData noInfo() {
        return new ResponseData(NO_INFO_CODE, "请填写信息!", null);
    }

    public static ResponseData unAuth() {
        return new ResponseData(UNAUTH_CODE, "没有权限", null);
    }

    public static ResponseData notFound() {
        return new ResponseData(NOT_FOUND_CODE, "该资源不存在!", null);
    }
}
