package com.yj.controller;

import com.yj.model.ResponseData;
import io.swagger.annotations.Api;

import java.util.List;

@Api(hidden = true)
public class BaseController {

    /**
     * 返回状态码对应的msg数据
     *
     * @param code 状态码
     * @return
     */
    public ResponseData render(Integer code) {
        return ResponseData.success(code);
    }

    /**
     * 返回ResponseEntity，code为200
     *
     * @param data 需要返回的数据
     * @return
     */
    public <T> ResponseData render(T data) {
        return ResponseData.success(data);
    }

    /**
     * 返回ResponseEntity，code为200
     *
     * @param data 需要返回的数据
     * @return
     */
    public <T> ResponseData render(List<T> data) {
        return ResponseData.success(data);
    }
}
