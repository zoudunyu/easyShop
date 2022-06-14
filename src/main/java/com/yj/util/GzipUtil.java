package com.yj.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Gzip工具
 *
 * @author 邹敦宇
 * @version 1.0
 * @date 2022-05-06 15:47:46
 */
@Slf4j
public class GzipUtil {

    /**
     * 压缩
     *
     * @param uncompressStr
     * @return java.lang.String
     * @author 邹敦宇
     * @date 2022-05-06 15:47:49
     **/
    public static String compress(String uncompressStr) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(uncompressStr.getBytes());
            gzip.finish();
            return Base64.getEncoder().encodeToString(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(gzip);
            IOUtils.closeQuietly(out);
        }
        return null;
    }

    /**
     * 解压
     *
     * @param compressedStr
     * @return java.lang.String
     * @author 邹敦宇
     * @date 2022-05-06 15:47:56
     **/
    public static String uncompress(String compressedStr) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        byte[] compressed;
        String decompressed = null;
        try {
            compressed = Base64.getDecoder().decode(compressedStr);
            in = new ByteArrayInputStream(compressed);
            ginzip = new GZIPInputStream(in);

            byte[] buffer = new byte[1024];
            int offset;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(ginzip);
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }
        return decompressed;
    }

}
