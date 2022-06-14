package com.yj.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * java8 stream工具
 *
 * @author 邹敦宇
 * @version 1.0
 * @date 2020/6/24 10:52
 */
@Slf4j
public class StreamUtil {
    /**
     * streatm filter去重
     *
     * @param keyExtractor
     * @return java.util.function.Predicate<T>
     * @author 邹敦宇
     * @date 2020-03-13 14:03:35
     **/
    public static <T> Predicate<T> distinct(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static void main(String[] args) {
        List<JSONObject> list = new ArrayList<>();
        Date now = new Date();
        JSONObject data1 = new JSONObject();
        data1.put("name", "test1");
        data1.put("date", now);
        list.add(data1);
        JSONObject data2 = new JSONObject();
        data2.put("name", "test1");
        data2.put("date", now);
        list.add(data2);
        JSONObject data3 = new JSONObject();
        data3.put("name", "test2");
        data3.put("date", now);
        list.add(data3);
        list = list.stream().filter(distinct(jsonObject -> {
            String date = DateUtil.format(jsonObject.getDate("date"), "yyyy-MM-dd HH:mm:ss");
            String name = jsonObject.getString("name");
            return new StringBuffer().append(name).append(date).toString();
        })).collect(Collectors.toList());
        log.info(list.toString());
    }
}
