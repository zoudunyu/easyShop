package com.yj.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 秘密配置
 *
 * @author zdy
 * @date 2022/02/10
 */
@Component
public class SecretConfig implements InitializingBean {

    private final String secret = "zdy1001";
    private final String issuer = "zdy1001";
    private final int expDays = 7;

    public static String SECRET;
    public static String ISSUER;
    public static int EXPDAYS;

    public String getSecret() {
        return secret;
    }

    public String getIssuer() {
        return issuer;
    }

    public int getExpDays() {
        return expDays;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        SECRET = secret;
        ISSUER = issuer;
        EXPDAYS = expDays;
    }
}
