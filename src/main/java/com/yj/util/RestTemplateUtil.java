package com.yj.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

/**
 * 网络请求工具类
 *
 * @author 邹敦宇
 * @version 1.0
 * @date 2022-05-06 15:49:05
 */
@Slf4j
public class RestTemplateUtil {

    /**
     * 获得RestTemplate实例
     *
     * @param
     * @return org.springframework.web.client.RestTemplate
     * @author 邹敦宇
     * @date 2021-06-15 12:13:48
     **/
    private static RestTemplate getRestTemplate() {
        return BeanUtil.getBean(RestTemplate.class);
    }

    /**
     * 下载文件
     *
     * @param url
     * @param filePath
     * @return
     * @author 邹敦宇
     * @date 2021-06-15 12:24:35
     **/
    public static void downloadFile(String url, String filePath) throws IOException {
        RestTemplate restTemplate = getRestTemplate();
        ResponseEntity<byte[]> responseEntity = restTemplate.getForEntity(url, byte[].class);
        // 将下载下来的文件内容保留到本地
        Files.write(Paths.get(filePath), Objects.requireNonNull(responseEntity.getBody(),
                "未获取到下载文件"));
    }

    /**
     * 下载大文件
     *
     * @param url
     * @param filePath
     * @return
     * @author 邹敦宇
     * @date 2021-06-15 12:24:42
     **/
    public static void downloadBigFile(String url, String filePath) {
        RestTemplate restTemplate = getRestTemplate();
        RequestCallback requestCallback = request -> request.getHeaders()
                .setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM, MediaType.ALL));

        // 对响应进行流式处理而不是将其全部加载到内存中
        restTemplate.execute(url, HttpMethod.GET, requestCallback, clientHttpResponse -> {
            Files.copy(clientHttpResponse.getBody(), Paths.get(filePath));
            return null;
        });
    }
}
