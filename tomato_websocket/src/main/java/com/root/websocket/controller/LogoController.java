package com.root.websocket.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.root.dto.LoginDto;
import com.root.dto.ReturnMessage;
import com.root.dto.UserDto;
import com.root.entity.SysUserEntity;
import com.root.mapper.SysUserMapper;
import com.root.memory.LoginTokenMemory;
import com.root.util.ResponseUtil;
import com.root.util.SqlResultUtil;
import com.root.util.TomatoHttpUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LogoController {
    @Resource
    SysUserMapper sysUserMapper;

    /**
     *  单点登录实现， 同一个ip 共用 token,不同ip 登录排斥
     * @param httpServletRequest
     * @param userDto
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ReturnMessage login(HttpServletRequest httpServletRequest ,@RequestBody UserDto userDto) {
        String s = userDto.getPassword() + "@" + userDto.getUsername();
        Map map = new HashMap();

       String ip= TomatoHttpUtil.getIpAddress(httpServletRequest);

       List<SysUserEntity> sysUserEntityList = sysUserMapper.login(userDto.getUsername(), userDto.getPassword());
        if (!ObjectUtil.isEmpty(sysUserEntityList)) {

            LoginDto loginDto =new LoginDto(sysUserEntityList.get(0).getSysUserId(),ip);
          String  token = LoginTokenMemory.setLoginToken(loginDto);
            map.put("token",token );
            return ResponseUtil.success(map);
        } else {

            return ResponseUtil.fail();
        }

    }

    /**
     *  登出

     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ReturnMessage logout() {

          return ResponseUtil.success();
    }
}
