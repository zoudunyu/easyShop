package com.yj.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Slf4j
public class RequestGzipUtil {
    public final static String USE_MY_COMPRESS = "use-my-compress";
    public final static String USE_MY_CACHE = "use-my-cache";

    /**
     * 压缩接口返回数据
     *
     * @param requestSupplier
     * @return java.lang.Object
     * @author 邹敦宇
     * @date 2021-07-16 08:54:26
     **/
    public static Object compress(Supplier<?> requestSupplier) throws Exception {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
        HttpServletRequest request =
                servletRequestAttributes.getRequest();
        String useCompress = request.getHeader(USE_MY_COMPRESS);
        if (useCompress == null || "false".equals(useCompress)) {
            Object data = requestSupplier.get();
            if (data instanceof Exception) {
                throw (Exception) data;
            }
            return data;
        }
        Object data = requestSupplier.get();
        if (data instanceof Exception) {
            throw (Exception) data;
        }
        String json = JsonUtil.encode(data);
        if (json == null) {
            throw new Exception("编码数据失败");
        }
        String compressData = GzipUtil.compress(json);
        if (compressData == null) {
            throw new Exception("压缩数据失败");
        }
        return compressData;
    }

    /**
     * 压缩接口返回数据, 使用缓存
     *
     * @param requestSupplier
     * @param cacheExpiration
     * @param cacheTimeUnit
     * @return java.lang.Object
     * @author 邹敦宇
     * @date 2022-01-18 16:57:09
     **/
    public static Object compressAndUseCache(Supplier<?> requestSupplier, long cacheExpiration, TimeUnit cacheTimeUnit)
            throws Exception {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String useCache = request.getHeader(USE_MY_CACHE);
        if ("true".equals(useCache)) {
            // 使用浏览器缓存
            HttpServletResponse response = servletRequestAttributes.getResponse();
            assert response != null;
            // 1天
            long maxAge = 86400L;
            if (cacheExpiration > 0) {
                maxAge = cacheTimeUnit.toSeconds(cacheExpiration);
            }
            response.setHeader("Cache-Control", "max-age=" + maxAge);
        }
        return compress(requestSupplier);
    }

    /**
     * 压缩接口返回数据, 使用缓存
     *
     * @param requestSupplier
     * @return java.lang.Object
     * @author 邹敦宇
     * @date 2021-07-16 17:16:06
     **/
    public static Object compressAndUseCache(Supplier<?> requestSupplier) throws Exception {
        return compressAndUseCache(requestSupplier, 0, null);
    }
}
