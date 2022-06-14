package com.yj.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserTypeEnum {
    admin(1, "管理员"),
    ordinary(2, "普通用户");

    private final Integer code;
    private final String stateName;
}
