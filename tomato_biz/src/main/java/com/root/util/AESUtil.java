package com.root.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class AESUtil {

    // AES密钥算法
    private static final String KEY_ALGORITHM = "AES";

    // 加密/解密算法/工作模式/填充方式
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    private static final String CIPHER_ALGORITHM2 = "AES/ECB/PKCS5Padding";

    // 生成密钥
    public static String generateAESKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(128, new SecureRandom()); // 192 and 256 bits may not be available
        SecretKey secretKey = keyGenerator.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    // AES加密
    public static String encryptAES(String data, String key) throws Exception {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM2);
        SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.getDecoder().decode(key), KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        System.out.println(data.getBytes("UTF-8"));
        byte[] encrypted = cipher.doFinal(data.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // AES解密
    public static String decryptAES(String data, String key) {
        byte[] decrypted;
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.getDecoder().decode(key), KEY_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            decrypted = cipher.doFinal(Base64.getDecoder().decode(data));
        } catch (Exception e) {
            return "解析失败";
        }
        return new String(decrypted);
    }

    public static void main(String[] args) throws Exception {
        String key = generateAESKey();
        String originalText = "Hello World!";
        String encryptedText = encryptAES(originalText, key);
        String decryptedText = decryptAES(encryptedText, key);

        System.out.println("Original Text: " + originalText);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}