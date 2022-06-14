package com.yj.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * JSON工具
 *
 * @author 邹敦宇
 * @version 1.0
 * @date 2022-05-06 15:49:50
 */
@Slf4j
public class JsonUtil {
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 根据inputStream获取jsonObject
     *
     * @param inputStream
     * @return com.alibaba.fastjson.JSONObject
     * @author 邹敦宇
     * @date 2019-09-05 10:18:29
     **/
    public static JSONObject getJSONObject(InputStream inputStream) throws IOException {
        HashMap hashMap;
        try {
            hashMap = OBJECT_MAPPER.readValue(inputStream, HashMap.class);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return new JSONObject(hashMap);
    }

    /**
     * 根据resources文件夹下的json文件获取jsonObject
     *
     * @param fileName
     * @return com.alibaba.fastjson.JSONObject
     * @author 邹敦宇
     * @date 2019-09-05 10:18:29
     **/
    public static JSONObject getJSONObject(String fileName) throws Exception {
        JSONObject jsonObject;
        ClassPathResource classPathResource = new ClassPathResource(fileName);
        InputStream inputStream = null;
        try {
            inputStream = classPathResource.getInputStream();
            jsonObject = getJSONObject(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("解析JSON文件出错!");
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return jsonObject;
    }

    /**
     * 根据resources文件夹下的json文件获取边界点集合
     *
     * @param fileName
     * @return com.alibaba.fastjson.JSONArray
     * @author 邹敦宇
     * @date 2019-09-05 10:18:29
     **/
    public static JSONArray getBorderList(String fileName) throws Exception {
        JSONArray borderList;
        try {
            borderList = getJSONObject(fileName).getJSONArray("features").getJSONObject(0).getJSONObject(
                    "geometry").getJSONArray("coordinates").getJSONArray(0);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("读取边界JSON出错!");
        }
        return borderList;
    }

    /**
     * 对象序列化成json字符串
     *
     * @param obj
     * @return java.lang.String
     * @author 邹敦宇
     * @date 2019-11-08 17:00:37
     **/
    public static String encode(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("encode(Object)", e);
        }
        return null;
    }

    /**
     * json字符串反序列化成对象
     *
     * @param json
     * @param valueType
     * @return T
     * @author 邹敦宇
     * @date 2019-11-08 17:00:55
     **/
    public static <T> T decode(String json, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(json, valueType);
        } catch (Exception e) {
            log.error("decode(String, Class<T>)", e);
        }
        return null;
    }

    /**
     * json字符串反序列化成对象
     *
     * @param json
     * @param javaType
     * @return T
     * @author 邹敦宇
     * @date 2021-09-18 11:33:07
     **/
    public static <T> T decode(String json, JavaType javaType) {
        try {
            return OBJECT_MAPPER.readValue(json, javaType);
        } catch (Exception e) {
            log.error("decode(String, JavaType)", e);
        }
        return null;
    }

    /**
     * json字符串反序列化成对象
     *
     * @param json
     * @param valueTypeRef eg: new TypeReference<List<Integer>>() {}
     * @return T
     * @author 邹敦宇
     * @date 2020-05-26 10:01:43
     **/
    public static <T> T decode(String json, TypeReference<T> valueTypeRef) {
        try {
            return OBJECT_MAPPER.readValue(json, valueTypeRef);
        } catch (Exception e) {
            log.error("decode(String, TypeReference<T>)", e);
        }
        return null;
    }

    /**
     * 对象序列化成json字符串byte数组
     *
     * @param obj
     * @return java.lang.String
     * @author 邹敦宇
     * @date 2019-11-08 17:00:37
     **/
    public static byte[] encodeToBytes(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsBytes(obj);
        } catch (Exception e) {
            log.error("encodeToBytes(Object)", e);
        }
        return null;
    }

    /**
     * json字符串byte数组反序列化成对象
     *
     * @param bytes
     * @param valueType
     * @return T
     * @author 邹敦宇
     * @date 2019-11-08 17:00:55
     **/
    public static <T> T decode(byte[] bytes, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(bytes, valueType);
        } catch (Exception e) {
            log.error("decode(byte[], Class<T>)", e);
        }
        return null;
    }

    /**
     * json字符串byte数组反序列化成对象
     *
     * @param bytes
     * @param valueTypeRef eg: new TypeReference<List<Integer>>() {}
     * @return T
     * @author 邹敦宇
     * @date 2020-05-26 10:02:23
     **/
    public static <T> T decode(byte[] bytes, TypeReference<T> valueTypeRef) {
        try {
            return OBJECT_MAPPER.readValue(bytes, valueTypeRef);
        } catch (Exception e) {
            log.error("decode(byte[], TypeReference<T>)", e);
        }
        return null;
    }

    /**
     * json字符串byte数组反序列化成对象
     *
     * @param bytes
     * @param javaType
     * @return T
     * @author 邹敦宇
     * @date 2021-09-18 11:33:38
     **/
    public static <T> T decode(byte[] bytes, JavaType javaType) {
        try {
            return OBJECT_MAPPER.readValue(bytes, javaType);
        } catch (Exception e) {
            log.error("decode(byte[], TypeReference<T>)", e);
        }
        return null;
    }

    /**
     * inputstream反序列化成对象
     *
     * @param inputStream
     * @param valueType
     * @return T
     * @author 邹敦宇
     * @date 2019-11-08 17:00:55
     **/
    public static <T> T decode(InputStream inputStream, Class<T> valueType) throws IOException {
        try {
            return OBJECT_MAPPER.readValue(inputStream, valueType);
        } catch (IOException e) {
            log.error("decode(inputStream, Class<T>)", e);
            throw e;
        }
    }

    /**
     * inputstream反序列化成对象
     *
     * @param inputStream
     * @param valueTypeRef eg: new TypeReference<List<Integer>>() {}
     * @return T
     * @author 邹敦宇
     * @date 2020-05-26 10:02:23
     **/
    public static <T> T decode(InputStream inputStream, TypeReference<T> valueTypeRef) throws IOException {
        try {
            return OBJECT_MAPPER.readValue(inputStream, valueTypeRef);
        } catch (IOException e) {
            log.error("decode(inputStream, TypeReference<T>)", e);
            throw e;
        }
    }

    /**
     * 将对象序列化后输出
     *
     * @param outputStream
     * @param obj
     * @return
     * @author 邹敦宇
     * @date 2019-11-08 17:03:58
     **/
    public static void writeValue(OutputStream outputStream, Object obj) throws IOException {
        OBJECT_MAPPER.writeValue(outputStream, obj);
    }

    /**
     * 保存对象序列化到文件
     *
     * @param savePath
     * @param obj
     * @return
     * @author 邹敦宇
     * @date 2019-11-14 13:45:49
     **/
    public static void saveFile(String savePath, Object obj) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(new File(savePath))) {
            writeValue(outputStream, obj);
            log.info("保存文件: {}", savePath);
        }
    }

    /**
     * 对象序列化后反序列化成对象
     *
     * @param obj
     * @param valueType
     * @return T
     * @author 邹敦宇
     * @date 2019-11-15 10:23:06
     **/
    public static <T> T decode(Object obj, Class<T> valueType) {
        return decode(encode(obj), valueType);
    }

    /**
     * 对象序列化后反序列化成对象
     *
     * @param obj
     * @param valueTypeRef eg: new TypeReference<List<Integer>>() {}
     * @return T
     * @author 邹敦宇
     * @date 2020-05-26 10:02:48
     **/
    public static <T> T decode(Object obj, TypeReference<T> valueTypeRef) {
        return decode(encode(obj), valueTypeRef);
    }
}
