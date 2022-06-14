package com.yj.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;
import java.io.File;

/**
 * 上传设置
 *
 * @author 邹敦宇
 * @version 1.0
 * @date 2022-05-06 15:49:27
 */
@Configuration
public class UploadConfig {

    /**
     * 重新设置文件上传临时路径, 防止centos清理temp文件夹
     *
     * @param
     * @return javax.servlet.MultipartConfigElement
     * @author 邹敦宇
     * @date 2019-04-10 11:19:45
     **/
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        String location = System.getProperty("user.dir") + "/data/tmp";
        File tmpFile = new File(location);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        factory.setLocation(location);
        return factory.createMultipartConfig();
    }
}
