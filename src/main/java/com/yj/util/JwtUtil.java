package com.yj.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/** JWT工具类 @Author zdy @Date 2021/7/12 10:52 @Version @Fix xyf */
@Slf4j
public class JwtUtil {

    private static final String KEY = "zdy1001";
    private static final String PUBLIC_KEY =
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj/DtwV4Y4f2t56D1GQ8GkuLF0ReI/0/sspeNSQfUr9UOt3DRY6V4/FqnSbCBci0v+S0UJYnatx9z2eJJ7TD225/iwj39TxSNKIjg1mXXPC1pnjmTyx9LTQsEyxwHcucK/oH1rLxImdBoJQ6HKSboWiNwsTfalf7pxHAjbttUD3A+GUeFhQfjoXNmNkiEpchl6SFIb6b7FaH1xKxMfgjq+fTXCCJkKvg5GZYs/HcXjFWvkSuq1PX7X2cm6Jtu/BG6G3D6iqlbRgz2FLbz7VGsuNPTUvKW2dk295qO3bTCTSTOTfJS7nmLHKFD6QDiaJ8JFNXP9vD9XFMhRKi1CFQb7wIDAQAB";
    private static final String PRIVATE_KEY =
            "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCP8O3BXhjh/a3noPUZDwaS4sXRF4j/T+yyl41JB9Sv1Q63cNFjpXj8WqdJsIFyLS/5LRQlidq3H3PZ4kntMPbbn+LCPf1PFI0oiODWZdc8LWmeOZPLH0tNCwTLHAdy5wr+gfWsvEiZ0GglDocpJuhaI3CxN9qV/unEcCNu21QPcD4ZR4WFB+Ohc2Y2SISlyGXpIUhvpvsVofXErEx+COr59NcIImQq+DkZliz8dxeMVa+RK6rU9ftfZybom278EbobcPqKqVtGDPYUtvPtUay409NS8pbZ2Tb3mo7dtMJNJM5N8lLueYscoUPpAOJonwkU1c/28P1cUyFEqLUIVBvvAgMBAAECggEAAzhGeT7bm6KLE1VcZjUZ1otQFX9LRzrIdq5BMNMGBPDlJWFWrb3z2OFGQLW8D4vHOPGzM6vDwrjnZIZVVQL1YrBO6D1uvygciD2XmfQpPmVdqXZsQezvSEfo0YpM+FsGue66SiTkEtzAl6OdjmQaannEeZJgR16uaACCKmapmLbHZC7rU7Jg0zN9cK1lrHpfQHA62/A2Du0PkMce0WCCec/xzpSeE2RCrkBbawFvioqdHIr7DMD8E2Dks4y7byCL4rfQOiwGj1t90v9rZE7087f5PMfDISoXR6h3uptaKOUZE/IjjJZ7R0VAPaUQV4JV7LDRidbS/7ZrpOsoCjTx8QKBgQDaOTdkVrH8yki7EucHGcdpN7yh3UiftbYsb+A1Ltk4N2k0hN/YEMY+T1xwNZYNZKUGs6vj7WoGpmsXLmv8lJ6g4LTJv2ZkJxrBtBSVptsOESKeKpytH4KDLER/v+UGNGQgoRYiex2ZJPdNqf36Y83TVL3XGDyFCynlp/avVFg4ewKBgQCo29FZpuDP3QyFY9iF81+uUcoORP9ckrdQK3dLQrsTGLyYpF1TLEwpJphxGdn1jaTYgq2ZjGLy6bXZKr4eQm67D3sQqmNRVcTx70KbF5pfUqm02e3aMvdHkhpxxDb6kbj/6nb+uvMxkvzRxIvQhN+gynD9pQPz25C5G662L3JCHQKBgBSw9CWZasrzSy4hvrIXqaa6WDJozugFzL4t5W5lgMcpydnm92p/PKzPzY3+JroiXtth0dyMuE7lHKZYCnkqhlfutnWipky8wHa5YnJGHeMZkNS6aZyorjFN0zjhsw+BjDTj0Tt3vPi2XpSIl8JUEEIHePCaJbLfuhtCb8t0DeFdAoGAdSxcQI7pb7RfbOyVIpZ4bbl5sZ81aGKy6+HNnR8IO78tl+pujDmv+9o5bDpZIekRW+CKYIOnYhLFSpY7aToGJq4eYrkpxBX1XUD/AipFyCHPe5zCTWiSaUq8KPfUgtm9+3AS9L575YmvAE0YDDE4ERaJg6kPbheNfLX17ZxM+A0CgYEAnBUfNprZKjKthyFka/m1fbkqFEB0lNf6aJVtEbM9AvOfLN+PlfkqBUFQ4+8eHCJI8I7pvyjjPsUZXDKUYCqmXW+iIIvTyboOidIoepvr62x5sP0vBhn57NIMjly6OHzkVKOrx4I7qPzTaUJIa6dBZ2jTH2YrX0yPOg/tqh8C63o=";

    /**
     * 生成秘钥对（KEY已经指定）
     *
     * @return 密钥对（0是公钥，1是私钥）
     */
    public static String[] generateKeyPair() {
        String[] keyPairStr = new String[2];
        try {
            // KeyPairGenerator对象用于生成非对称加密的公钥和私钥，选用RSA非对称加密算法
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            // 密钥初始化为2048位,设置key值
            keyGen.initialize(2048, new SecureRandom(KEY.getBytes()));
            // 生产密钥对
            KeyPair keyPair = keyGen.generateKeyPair();
            // 编码后得到公钥字符串
            PublicKey publicKey = keyPair.getPublic();
            keyPairStr[0] = Base64.encodeBase64String(publicKey.getEncoded());
            // 编码后得到私钥字符串
            PrivateKey privateKey = keyPair.getPrivate();
            keyPairStr[1] = Base64.encodeBase64String(privateKey.getEncoded());
            return keyPairStr;
        } catch (NoSuchAlgorithmException e) {
            log.error("生成密钥对失败：{}", e.getMessage());
        }
        return keyPairStr;
    }

    /**
     * 加密数据
     *
     * @param data 明文
     * @return 加密后的字符串
     */
    public static String encrypt(String data) {
        try {
            // 1.编码公钥字符串
            byte[] pubKeyB = Base64.decodeBase64(PUBLIC_KEY);
            // 2.根据编码后的公钥字符串创建公钥材料
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKeyB);
            // 3.密钥工厂使用RSA算法
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            // 4.密钥工厂使用公钥材料生成公钥对象
            RSAPublicKey pubKeyR = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
            // 5.Cipher对象用于加密或解密数据，选用RSA算法加密或解密
            Cipher cipher = Cipher.getInstance("RSA");
            // 6.Cipher对象选用ENCRYPT_MODE加密模式，并使用公钥对象加密
            cipher.init(Cipher.ENCRYPT_MODE, pubKeyR);
            // 7.Cipher对象生成加密数据
            byte[] encData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            // 8.编码得到加密数据的字符串
            return Base64.encodeBase64String(encData);
        } catch (NoSuchAlgorithmException
                | NoSuchPaddingException
                | IllegalBlockSizeException
                | InvalidKeySpecException
                | BadPaddingException
                | InvalidKeyException e) {
            log.error("加密发生错误：{}", e.getMessage());
        }
        return null;
    }

    /**
     * 解密数据
     *
     * @param data 密文
     * @return 解密后的字符串
     */
    public static String decrypt(String data) {
        try {
            // 1.编码私钥字符串
            byte[] priKeyB = Base64.decodeBase64(PRIVATE_KEY);
            // 2.根据编码后的私钥字符串创建私钥材料
            PKCS8EncodedKeySpec pkCs8KeySpec = new PKCS8EncodedKeySpec(priKeyB);
            // 3.密钥工厂使用RSA算法
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            // 4.密钥工厂使用私钥材料生成私钥对象
            RSAPrivateKey priKeyR = (RSAPrivateKey) keyFactory.generatePrivate(pkCs8KeySpec);
            // 5.Cipher对象用于加密或解密数据，选用RSA算法加密或解密
            Cipher cipher = Cipher.getInstance("RSA");
            // 6.Cipher对象选用PRIVATE_KEY加密模式，并使用私钥对象加密
            cipher.init(Cipher.PRIVATE_KEY, priKeyR);
            // 7.Cipher对象解析加密数据
            byte[] bytes = Base64.decodeBase64(data);
            byte[] decData = cipher.doFinal(bytes);
            // 8.得到解密后数据的字符串
            return new String(decData, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException
                | NoSuchPaddingException
                | IllegalBlockSizeException
                | InvalidKeySpecException
                | BadPaddingException
                | InvalidKeyException e) {
            log.error("解密发生错误：{}", e.getMessage());
        }
        return null;
    }

    private JwtUtil() {}
}
