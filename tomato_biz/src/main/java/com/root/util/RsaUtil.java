package com.root.util;




import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.URLUtil;
import com.root.config.SysConfig;
import com.root.sevice.SysBaseServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaUtil {
    private static String publicKey1 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAun7wnUJkpeemG6LdKeGGM6jCCQAEasm7sqnfnwfEkovW08gfy9Oi5xaIyjdyZwWqg7OGeijfxf/LTyBReLulN09mTg7PtaPAM3gYt7y/F+PFdn3pM5uFiY4f+QkmRRv1Km/HtNLbuUEXRzUuEyCts32nFlwUsROaJemNYufhHK4GXvPG24+J6aNrxXZpUEdFOvGuee5kb2CqeurWNbpGn9RfJ3bXrkjj/QeARum/ZxTTp012AodJtQQA7SetUpQqIZYbHi3rbkHXB1lIu+RRxy1ajg1A9hIwKUdzdYn90XOGy7aBWROSM/m9Wb0Vp30OxZbcD6ox49dJ3bOS6bCiTQIDAQAB";
    private static String privateKey1 = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDDYPiX67F2c3s0aX29CRaqp5zrGQIMwXkcMttwpONd7xKtDPU/TWs1IY+AOtR4RO/T+BbcNcp8Av3A0Nem3DjGnfdmn8hqilepRAHoN2hECvL9/jmwekt1ZQadOdrZlDAAaGqUP/M0/OMIHKx8Ctan+wTqeFOvw4ZRdDYAHhEJ/X65hvFPBPfTssg/A9BGdP3zRdwYHsPj7a9UBzaoXuTplI9+2NTmjSclPGnecPEnVEMDr9Jbp6rr3pJFV4GICu0fqvECdzZdcOXlgS4IwtojOJrfVXpUrZ8AE+NEQlcaL/3nsRNfnTRez8UfHVZIfmk2LpQTVsDRtQpoITqHyvOJAgMBAAECggEBAKFpOJdSCd+hEjU/vbhxjL9aECKOG4psQGEShwYtwwV0oIpE/UvZ6usPGd3HPIdL9JK7EC/HZMxFMFyebLsWjvVPxfeLo+iY+EkHvUealtI39X3vDLwGnNaUqv0zseE1Mt7zZLGrj4LENdAa2Ej2L1bB49LNq922e/5JMt+v90W3LKQeFwzXqKpPvbZf6vVThrZrX2LSo57cZePTUL6l/XSgungAhk9L9D51VtI954rDp6FP1TQXjp9lyJF/K6N3HQe5LwdRPvYbEzVYd95p6HWpwHrdfBaVSnPkX2fujhLW50WYBu6Zncs6IzRqnLsLxSqiR1BYbgVDFffBnGvyQIECgYEA6R+cvZbFmdZ7r1ETd0VxUd4Jr82iBl4/61uimW8wIjsNvGdZQeRd0BdJR9Cm0SRo/+EjVRrcqxYm8DA9L170+veDE54SKHIONuqBAjt5tzPz/PbqKyrNitXA4h5jjJYKLwpKUIqeG0OC5yOJ7uRdBjbV6WnTEyunv14oeCav2BECgYEA1o0ozYij9SaDqUzkiPYNUoMGHvTat2C7fOxJrfTq+en0bJvhClEJRBBjAZBC51mYhXGUK5GYOqUHZzDt+9k7ZB3/nVnNXywZt1VPqme/i74NDgoX/lWXM9pduz1hEVQxtpQPgjyhuSv7U7GglxYX8FAEYunIEzPqu8UZBON4G/kCgYBIrst+rNBk+2NKsNuB10vSe4jHoDRBCgehyMs0TV9TfNf2gr5oddvt8iSt2Fb6ZAC8rqQGd76PNPPk9RVOfKuix6K9bg45P/5AjvK0EHkNboxGtXEjDlBRVf2yQklFXs5EBlBPNji/RVg92m255sc+FcCx6oWdt4Gwf5nchCtVEQKBgGFGqyNVOpFiHvvZwxxeAvhQM6xBdBEEqL0hHN7kVs1sx8eefVACIeNcUqdY0xy1wLNwJbADWTPtsweUZbJ0cbsY5tZe6wClrBs1cONgGravOpgM94f3IpD+BtVoJOSIeenAA7AW8XRVfqQZuygFemEYxoRaXr5mIZ105b0TrVdxAoGAXvyP6n6JV7Dp4oNi9Rb3yRvX0S3mVbe/q1roGM+tk7Vq149mKhgiIeCSucDe8M5K45Sg2f0cvuUt7p+KUiCwEnifM9GC6WbkwwS17/reyTUadXyyeay6NhrCwO0Pl7v1uYmxUqdaQ/PWdAgFV1Al1zn+ltbHQeJR28XcGG+ByxU=";
    private static String publicKey2 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAw2D4l+uxdnN7NGl9vQkWqqec6xkCDMF5HDLbcKTjXe8SrQz1P01rNSGPgDrUeETv0/gW3DXKfAL9wNDXptw4xp33Zp/IaopXqUQB6DdoRAry/f45sHpLdWUGnTna2ZQwAGhqlD/zNPzjCBysfArWp/sE6nhTr8OGUXQ2AB4RCf1+uYbxTwT307LIPwPQRnT980XcGB7D4+2vVAc2qF7k6ZSPftjU5o0nJTxp3nDxJ1RDA6/SW6eq696SRVeBiArtH6rxAnc2XXDl5YEuCMLaIzia31V6VK2fABPjREJXGi/957ETX500Xs/FHx1WSH5pNi6UE1bA0bUKaCE6h8rziQIDAQAB";
    private static String privateKey2 = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC6fvCdQmSl56Ybot0p4YYzqMIJAARqybuyqd+fB8SSi9bTyB/L06LnFojKN3JnBaqDs4Z6KN/F/8tPIFF4u6U3T2ZODs+1o8AzeBi3vL8X48V2fekzm4WJjh/5CSZFG/Uqb8e00tu5QRdHNS4TIK2zfacWXBSxE5ol6Y1i5+EcrgZe88bbj4npo2vFdmlQR0U68a557mRvYKp66tY1ukaf1F8ndteuSOP9B4BG6b9nFNOnTXYCh0m1BADtJ61SlCohlhseLetuQdcHWUi75FHHLVqODUD2EjApR3N1if3Rc4bLtoFZE5Iz+b1ZvRWnfQ7FltwPqjHj10nds5LpsKJNAgMBAAECggEATCK0kk7fjgdy/cdQaN+kDQTxKgFyzDHNSdQl8eOW8YjbTBxgNrMQxyyg7YUTVFGw2hF5bfev20JhOs1j1VktytXEW0uaqnnOZnS6CpCoptUa8RPYF3R/YLe//8gncoFGZmu56O1IPGoBWdWxGKf3odZDV1KCNARfouilCuLFORJx+xeOllJhGQxe70Ibq3BEk+0JtPvBhycgKmyvhCd05QjImXTHq761nRFqBBXDjMBO0qMFDaB9teHIaAioChfeJwWIXFNrHhatGezj7+EV6DzTygQm7+pEmDCujbip+da91AGDZYPnmuXqHFYWSyqTXlzhSmnETgbZO/ftftJsNQKBgQD/cXhw3NLpz98zatXZMgcDWZm3YiXRP+088dsvEyUIoFwJIlPKp6JN5bihPMKko8+2hy61WFypfkgnXhQEqNNyjvXA8YiqsH4pLsiafJ2J6uIrmng0coCYE4MwdG+NNa42yij3WIVrpUeH8J4XlZ2zlkh5PZobodeBTndu2QMTqwKBgQC65v+3a7N0dxACxmnrR52J4UBpW350NrAWfzAlvCA2NtXZvp/bz6h1T+n2jcRTKbqPKTsMjef10V88Rt+u4BUgVlIDS6iUE4vTuw58bV3mV5CQHAwRyRIolMiDNCrQa7FWS3vhtuyeCelq7HuAm5chJ1Ln06aPkRbcFKJ6ocqp5wKBgAbIMHZupnrmtfpbf8oAdgc0vcwMowxnMhlIIpWhYH5WI5tqeecLhFelHb3KKzDL2Jv0At11tyAY5jmFWeq1wMdt3EB/KS2++dqGD3VqkZQz1EVsGHzr/VYMTpNexAdvwWxa+bN3ejNddAS78raVllZKSypM5GaPaXVZa/pZcKd5AoGBALgsUOgp1Rh7CufxJQaX94Ga2aH4as+R5EsG4icNDqDdwDVlRpmQM2wGZ4JxiHh6sj/IXUutulndC3+WR21HmwnWhdKBh4kDCdKGjWvHSbRlrSqUuL/950rd8gh4K4hd1s42a8isPNYu5jha+AhvSal92pBfW841JtWP79vv+okZAoGAbb14Z7ToLyeyQRj13ZtNJuobXArxmXoUEYc0oLxUzMZeYviT//3a7nKupIbQaljQIeRIkUQW2vaOSZapweNXvZsIshu49/NPG/w2HZuB7KWYTt3DkVM7LAhZ/ofciyNYnmJd8tprloAUDxKdvicL98TCFtC47pLLBs8LB5ij//E=";

    @Autowired
    SysBaseServiceService sysBaseServiceService;

    public static String data = "hello world";
    // 最大的加密明文长度
    public static final int MAX_ENCRYPT_BLOCK = 245;

    // 最大的解密密文长度
    public static final int MAX_DECRYPT_BLOCK = 256;

    /**
     * 签名算法.
     */
    private static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
    /**
     * 加密算法RSA.
     */
    private static final String KEY_ALGORITHM = "RSA";

    public static void getKeys() {
        try {

            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048); // 初始化密钥生成器，并指定密钥长度为 2048 位
            KeyPair keyPair = keyGen.generateKeyPair(); // 生成密钥对\

            System.out.println("这个是私钥：\n");
            System.out.println("私钥：" + base64Encode(keyPair.getPrivate().getEncoded()));

            System.out.println("这个是公钥：\n");
            System.out.println("公钥：" + base64Encode(keyPair.getPublic().getEncoded()));



        } catch (Exception e) {

        }

    }

    public static String base64Encode(byte[] src) {
        return Base64.getEncoder().encodeToString(src);
    }

    public static byte[] base64Decode(String s) {
        return Base64.getDecoder().decode(s);
    }

    /**
     * 生成密钥对
     *
     * @param keyLength
     * @return
     */
    public static KeyPair genKeyPair(int keyLength) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 公钥加密
     *
     * @param s
     * @param publicKey
     * @return
     */
    public static String encrypt(String s) {
        try {
            s = URLUtil.encode(s);
            System.out.println(s);
            String publicKey= SysConfig.getconfig("selfPublic");
            byte[] content = s.getBytes();
            byte[] encoded = base64Decode(publicKey);
            RSAPublicKey rsaPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(encoded));
            Cipher cipher = Cipher.getInstance("RSA");// java默认"RSA"="RSA/ECB/PKCS1Padding"
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);

            //分段加密
            int inputLen = content.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            //对数据分段解密
            while (inputLen - offSet > 0){
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK){
                    cache = cipher.doFinal(content, offSet,MAX_ENCRYPT_BLOCK);
                }else {
                    cache = cipher.doFinal(content,offSet,inputLen - offSet);
                }
                out.write(cache,0,cache.length);
                i++;
                offSet = i*MAX_ENCRYPT_BLOCK;
            }
            byte[] decryptedData = out.toByteArray();
            out.close();

            return base64Encode(decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 私钥解密
     *
     * @param s
     * @param privateKey
     * @return
     */
    public static String decrypt(String s) {
        try {
             String privateKey=SysConfig.getconfig("otherPrivate");
            byte[] content = base64Decode(s);
            byte[] encoded = base64Decode(privateKey);
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(encoded));
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
            //分段解密
            byte[] enBytes = new byte[0];
            for (int i = 0; i < content.length; i += MAX_DECRYPT_BLOCK){
                //注意要使用2的倍数，否则会出现加密后的内容再解密时为乱码
                byte[] doFinal = cipher.doFinal(subarray(content, i,MAX_DECRYPT_BLOCK));
                enBytes = addBytes(enBytes, doFinal);
            }

            return new String(enBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * RSA签名.
     *
     * @param content    待签名数据(base64)
     * @param privateKey 商户私钥
     * @return 签名值
     */
    public static String sign(String content) {
        try {
            content= base64Encode(content.getBytes());
            String privateKey=SysConfig.getconfig("selfPrivate");
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(loadPrivateKeyByStr(privateKey));
            signature.update(base64Decode(content));
            byte[] signed = signature.sign();
            return base64Encode(signed);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    /**
     * 从字符串中加载公钥.
     *
     * @param publicKeyStr 公钥数据字符串
     * @return
     */
    private static RSAPublicKey loadPublicKeyByStr(String publicKeyStr) throws Exception {
        try {
            byte[] buffer = base64Decode(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("公钥异常");
        }
    }
    /**
     * 获取 私钥.
     *
     * @param privateKeyStr 私钥数据字符串
     * @throws Exception 加载私钥时产生的异常
     */
    private static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr) throws Exception {
        try {
            byte[] buffer = base64Decode(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("私钥异常");
        }
    }

    /**
     * RSA验签名检查.
     *
     * @param content   待签名数据(base64)
     * @param sign      签名值
     * @param publicKey 分配给开发商公钥
     * @return 布尔值
     */
    public static boolean doCheck(String content, String sign) {
        try {
            content= base64Encode(content.getBytes());
            String publicKey =SysConfig.getconfig("otherPublic");;
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(loadPublicKeyByStr(publicKey));
            signature.update(base64Decode(content));
            return signature.verify(base64Decode(sign));
        } catch (Exception e) {

        }
        return false;
    }



 public  static  byte[] subarray(byte[] bytes ,int srcPos,  int length){
     byte[] bytes1 = new byte[256];
     if (bytes.length<srcPos+length){
         System.arraycopy(bytes, srcPos, bytes1, 0, bytes.length-srcPos);
     }else {
         System.arraycopy(bytes, srcPos, bytes1, 0, length);
     }
     return bytes1;
 }
    public static byte[] addBytes(byte[] data1, byte[] data2) {
if (data2.length==0){
    return data1;
}
        byte[] data3 = new byte[data1.length + data2.length];
        System.arraycopy(data1, 0, data3, 0, data1.length);
        System.arraycopy(data2, 0, data3, data1.length, data2.length);
        return data3;

    }

    public static void main(String[] args) {
        String input = "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";
//        String input = "HF/FSACXxhaZZOlhDJynoLXmqFEbB6473+M4Pyv0659kdvLflrsE3VYPVfazqYVTwWIwE3JQGLjG+U0qHqahml+hQNmoZbJd0yXJ70faHLm41X4lJ3TJX8nDc77INqyfIUzlCF2xe73VQASnxhn4ykAw6aX31x5H0leKiTmMYGakjoiT9CYDKfr7LdzXDgUALqdS8SR/ZgIJjHrKBHDsel2Z8IE8arbPK5NHb4bsuZT6BQy+FikJoQ7dBLFbiqtwy/7k5H2/iRDWMzHvidaUcIjBqF4Hjl/sDVFOeXI/OwjtVou4HQKq1UcF/ZlYJW8wPxUTmldkX8rPFzob9UPmXw==";
        String output = "hellowordhellowordhellowordhellowordhellowordhellowohellowordhellowordhellowordhellowordhellowordhellowordhellowordhhellowordhellowordhellowordhellowordhellowordhellowordhellowordhhellowordhellowordhellowordhellowordhellowordhellowordhellowordhhellowordhellowordhellowordhellowordhellowordhellowordhellowordhhellowordhellowordhellowordhellowordhellowordhellowordhellowordhhellowordhellowordhellowordhellowordhellowordhellowordhellowordhhellowordhellowordhellowordhellowordhellowordhellowordhellowordhhellowordhellowordhellowordhellowordhellowordhellowordhellowordhhellowordhellowordhellowordhellowordhellowordhellowordhellowordhrdhellowordhellowordhellowordhellowordhellowordhellowordhellowordhelloword";
       String s = encrypt(output);
        System.out.println(s);
        String s1 = decrypt(input);
        System.out.println(s1);
        String s3 ="hellowordhellowordhellowordhellowordhellowordhellowohellowordhellowordhellowordhellowordhellowordhellowordhellowordhhellowordhellowordhellowordhellowordhellowordhellowordhellowordhhellowordhellowordhellowordhellowordhellowordhellowordhellowordhhellowordhellowordhellowordhellowordhellowordhellowordhellowordhhellowordhellowordhellowordhellowordhellowordhellowordhellowordhhellowordhellowordhellowordhellowordhellowordhellowordhellowordhhellowordhellowordhellowordhellowordhellowordhellowordhellowordhhellowordhellowordhellowordhellowordhellowordhellowordhellowordhhellowordhellowordhellowordhellowordhellowordhellowordhellowordhrdhellowordhellowordhellowordhellowordhellowordhellowordhellowordhelloword";
        String s2 =sign(s3);
        System.out.println(s2);
        Boolean b =doCheck(s3,s2);
        System.out.println(b);
 }
}

