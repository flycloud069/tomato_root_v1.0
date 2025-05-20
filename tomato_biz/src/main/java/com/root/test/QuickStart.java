package com.root.test;

import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.RSAPublicKeyConfig;
import com.wechat.pay.java.service.payments.nativepay.NativePayService;
import com.wechat.pay.java.service.payments.nativepay.model.Amount;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayRequest;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse;

/** Native 支付下单为例 */
public class QuickStart {
    /** 商户号 */
    public static String merchantId = "1695184520";
    /** 商户API私钥路径 */
    public static String privateKeyPath = "D:/apiclient_key.pem";
    /** 商户API公钥路径 */
    public static String publicKeyPath = "D:/pub_key.pem";
    public static String publicKeyId = "PUB_KEY_ID_0116951845202024121500389500000802";


    /** 商户证书序列号 */
    public static String merchantSerialNumber = "7CACA68D76782E8097CF6ADBF292A305D27525E1";
    /** 商户APIV3密钥 */
    public static String apiV3key = "92420115MADBJB8N3Q92420115MADBJB";
    public static void main(String[] args) {
        // 使用自动更新平台证书的RSA配置
        // 建议将 config 作为单例或全局静态对象，避免重复的下载浪费系统资源
//        Config config =
//                new RSAAutoCertificateConfig.Builder()
//                        .merchantId(merchantId)
//                        .privateKeyFromPath(privateKeyPath)
//                        .merchantSerialNumber(merchantSerialNumber)
//                        .apiV3Key(apiV3key)
//                        .build();
        // 可以根据实际情况使用publicKeyFromPath或publicKey加载公钥
        Config config =
                new RSAPublicKeyConfig.Builder()
                        .merchantId(merchantId)
                        .privateKeyFromPath(privateKeyPath)
                        .publicKeyFromPath(publicKeyPath)
                        .publicKeyId(publicKeyId)
                        .merchantSerialNumber(merchantSerialNumber)
                        .apiV3Key(apiV3key)
                        .build();
        // 构建service
        NativePayService service = new NativePayService.Builder().config(config).build();
        // request.setXxx(val)设置所需参数，具体参数可见Request定义
        PrepayRequest request = new PrepayRequest();
        Amount amount = new Amount();
        amount.setTotal(100);
        request.setAmount(amount);
        request.setAppid("wxea7dbb484f1b2fce");
        request.setMchid("1695184520");
        request.setDescription("测试商品标题");
        request.setNotifyUrl("https://notify_url");
        request.setOutTradeNo("out_trade_no_001");
        // 调用下单方法，得到应答
        PrepayResponse response = service.prepay(request);
        // 使用微信扫描 code_url 对应的二维码，即可体验Native支付
        System.out.println(response.getCodeUrl());
    }
}