package com.yj.util;

import com.yj.enums.CodeEnum;

public class EnumUtil {

    public static<T extends CodeEnum> String getByCode(Integer code, Class<T> t){
        for(T item: t.getEnumConstants()){
            if(item.getCode().equals(code)){
                return item.getMsg();
            }
        }
        return "";
    }
}
