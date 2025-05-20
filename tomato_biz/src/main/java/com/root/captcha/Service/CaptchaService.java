package com.root.captcha.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.root.entity.CaptchaEntity;

/**
 * @author ：fuyunxiang
 * @date ：Created in 2022年12月14日,0014 17:39
 */

public interface CaptchaService  extends IService<CaptchaEntity> {
    /**
     * 验证码效验
     *
     * @param uuid uuid
     * @param code code
     * @return 校验结果
     */
    boolean validate(String uuid, String code);

    /**
     * 保存图片验证码和uuid关系
     *
     * @param uuid uuid
     * @param code code
     */
    boolean saveCaptcha(String uuid, String code);
}
