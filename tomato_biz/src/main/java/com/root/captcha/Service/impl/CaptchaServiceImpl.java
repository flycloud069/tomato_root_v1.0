package com.root.captcha.Service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.root.captcha.Service.CaptchaService;
import com.root.entity.CaptchaEntity;
import com.root.mapper.CaptchaMapper;
import com.root.util.ResponseUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author ：fuyunxiang
 * @date ：Created in 2022年12月14日,0014 17:39
 */
@Service
public class CaptchaServiceImpl  extends ServiceImpl<CaptchaMapper, CaptchaEntity> implements CaptchaService {
    /**
     * 验证码效验
     *
     * @param uuid uuid
     * @param code code
     * @return 校验结果
     */
 public    boolean validate(String uuid, String code){
     CaptchaEntity captchaEntity = this.getOne(new QueryWrapper<CaptchaEntity>().eq("uuid", uuid));
     if (captchaEntity == null) {
         return false;
     }

     //删除验证码
     this.removeById(uuid);
boolean b=captchaEntity.getCode().equalsIgnoreCase(code);
boolean a= captchaEntity.getExpireTime().getTime() >= System.currentTimeMillis();
     System.out.println(b);
     System.out.println(a);
     System.out.println(captchaEntity.getExpireTime().getTime());
     System.out.println(System.currentTimeMillis());

     if (captchaEntity.getCode().equalsIgnoreCase(code) && captchaEntity.getExpireTime().getTime() >= System.currentTimeMillis()) {
         return true;
     }

     return false;
 };

    /**
     * 保存图片验证码和uuid关系
     *
     * @param uuid uuid
     * @param code code
     */
   public boolean saveCaptcha(String uuid, String code) {
       if (StrUtil.isBlank(uuid)) {
           ResponseUtil.fail("uuid不能为空");
       }

       CaptchaEntity captchaEntity = new CaptchaEntity();
       captchaEntity.setUuid(uuid);
       captchaEntity.setCode(code);
       //5分钟后过期
       captchaEntity.setExpireTime(DateUtil.offsetMinute(new Date(), 5));
       return saveOrUpdate(captchaEntity);

   };
}
