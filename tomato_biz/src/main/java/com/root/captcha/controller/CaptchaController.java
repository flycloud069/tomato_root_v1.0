package com.root.captcha.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.root.captcha.Service.CaptchaService;
import com.root.exceptions.GlobalException;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

//import com.google.code.kaptcha.Producer;
/**
 * @author ：fuyunxiang
 * @date ：Created in 2022年12月14日,0014 17:36
 */
@RestController
@RequestMapping("/sys/captcha")
@Api(value = "/sys/captcha", tags = "图片验证码")
@Log4j2
public class CaptchaController {

    @Autowired
    private CaptchaService captchaService;

    /**
     * 图片验证码
     * @param response
     * @param uuid
     */
    @GetMapping("/captcha.jpg")
    public void getCaptchaCode(HttpServletResponse response, String uuid) {

        // 生成文字验证码
//        String code = producer.createText();

        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(150, 40, 5, 2);
        //图形验证码写出，可以写出到文件，也可以写出到流
//        captcha.write(response.getOutputStream());

        //获取验证码中的文字内容
        String code = captcha.getCode();

        // 保存code和uuid的关系,结果是boolean
        boolean flg = false;
        try{
            flg = captchaService.saveCaptcha(uuid,code);
        }catch (DuplicateKeyException e){
            throw new GlobalException(-1,"主键重复");
        }


        if(!flg){
            throw new GlobalException(-1,"验证码获取失败");
        }

        try {
            // 获取图片验证码
            BufferedImage image = captcha.getImage();
            response.setHeader("Cache-Control", "no-store, no-cache");
            response.setContentType("image/jpeg");
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(image, "jpg", out);
            IOUtils.closeQuietly(out);
        } catch (Exception e) {
            throw new GlobalException(-1,"图片生成失败,重试");
        }
    }
}
