//package com.root.websocket.service.impl;
//
//import cn.hutool.core.bean.BeanUtil;
//import cn.hutool.core.convert.Convert;
//import cn.hutool.core.util.StrUtil;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.root.captcha.Service.CaptchaService;
//import com.root.dto.ReturnMessage;
//import com.root.entity.UserEntity;
//import com.root.exceptions.GlobalException;
//import com.root.mapper.UserMapper;
//import com.root.util.ResponseUtil;
//import com.root.websocket.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import static com.root.websocket.util.SHA256Util.getSHA256StrJava;
//
///**
// * @author ：fuyunxiang
// * @date ：Created in 2022年7月25日,0025 16:42
// */
//@Service
//public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
//
//    @Autowired
//    CaptchaService captchaService;
//
//    public ReturnMessage creatUser(String name, String password, String mailNo, String uuid, String code) {
//        if (!captchaService.validate(uuid, code)) {
//            throw new GlobalException(-1, "错误");
//        }
//        if (name.length() > 5) {
//            throw new GlobalException(-1, "姓名大于5");
//        }
//        if (password.length()<5){
//            throw new GlobalException(-1, "密码小于5");
//        }
//
//        if (password.length()<10){
//            throw new GlobalException(-1, "密码大于10");
//        }
//
//
//        QueryWrapper<UserEntity> query1 = new QueryWrapper();
//        query1.lambda().eq(UserEntity::getName, name);
////        query.lambda().eq(UserEntity::getPassword,password);
//        UserEntity userEntity1 = this.getOne(query1);
//        if (!BeanUtil.isEmpty(userEntity1)) {
//            throw new GlobalException(-1, "重复建档");
//        }
//        UserEntity userEntity = new UserEntity();
//        userEntity.setName(name);
//        userEntity.setPassword(getSHA256StrJava(password));
//        userEntity.setMailNo(mailNo);
//        userEntity.setTicTacToeRade("0");
//        if (this.save(userEntity)) {
//
//            throw new GlobalException(-1, "报错失败");
//        }
//        return ResponseUtil.success(userEntity);
//    }
//
//    ;
//
//    public ReturnMessage getUser(String name, String password, String uuid, String code) {
////        if (!captchaService.validate(uuid, code)) {
////            throw new GlobalException(-1, "验证码错误");
////        }
//
//        QueryWrapper<UserEntity> query = new QueryWrapper();
//        query.lambda().eq(UserEntity::getName, name);
////        query.lambda().eq(PatientEntity::getEmpiId, TsxController.hosId);
//        UserEntity userEntity = this.getOne(query);
//        if (BeanUtil.isEmpty(userEntity)){
//            throw new GlobalException(-1,"用户未注册");
//        }
//        if (!StrUtil.equals(userEntity.getPassword(), getSHA256StrJava(password))) {
//            ResponseUtil.fail("密码不正确");
//        }
//
//        return ResponseUtil.success(userEntity);
//    }
//
//    ;
//
//    public ReturnMessage updateUser(String name, String code) {
//        QueryWrapper<UserEntity> query = new QueryWrapper();
//        query.lambda().eq(UserEntity::getName, name);
////        query.lambda().eq(PatientEntity::getEmpiId, TsxController.hosId);
//        UserEntity userEntity = this.getOne(query);
//        int i, j, total;
//        i = Convert.toInt(code);
//        j = Convert.toInt(userEntity.getTicTacToeRade());
//        total = i + j;
//        userEntity.setTicTacToeRade(Convert.toStr(total));
//        if (this.updateById(userEntity)) {
//            ResponseUtil.fail();
//
//        }
//        return ResponseUtil.success();
//
//    }
//
//    ;
//
//    public String changeCode(String id, String code) {
//        UserEntity userEntity = this.getById(id);
//        String oldCode = userEntity.getTicTacToeRade();
//        int nowCode = Convert.toInt(code) + Convert.toInt(oldCode);
//        userEntity.setTicTacToeRade(Convert.toStr(nowCode));
//        this.updateById(userEntity);
//        return Convert.toStr(nowCode);
//    }
//
//    ;
//
//}
