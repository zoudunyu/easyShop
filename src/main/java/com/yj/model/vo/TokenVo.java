package com.yj.model.vo;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * Token登录信息封装要素
 *
 * @author xyf
 */
@Data
public class TokenVo {

    private boolean valid;
    private String validMessage;

    private String username;
    private String userType;

    public static TokenVo success(String username, String userType) {
        // 检查有值为空，就应该返回false
        if (StringUtils.isAnyEmpty(username, userType)) {
            return failed("Token包含信息不正确");
        }
        // 正确的情况走这里
        TokenVo data = new TokenVo();
        data.setValid(true);
        data.setUsername(username);
        data.setUserType(userType);
        return data;
    }

    public static TokenVo failed(String message) {
        TokenVo data = new TokenVo();
        data.setValid(false);
        data.setValidMessage(message);
        return data;
    }
}
