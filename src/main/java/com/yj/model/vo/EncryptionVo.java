package com.yj.model.vo;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
@Valid
public class EncryptionVo {

    @NotBlank(message = "请输入加密后的数据")
    private String data;
}
