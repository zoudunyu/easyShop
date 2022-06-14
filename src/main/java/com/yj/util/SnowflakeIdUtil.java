package com.yj.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * 雪花算法唯一id
 *
 * @author 邹敦宇
 * @version 1.0
 * @date 2021/9/7 16:36
 */
public class SnowflakeIdUtil {

    public static Snowflake snowflake = IdUtil.getSnowflake();

    /**
     * 获取id
     *
     * @param
     * @return long
     * @author 邹敦宇
     * @date 2021-09-07 16:41:12
     **/
    public static long getId() {
        return snowflake.nextId();
    }
}
