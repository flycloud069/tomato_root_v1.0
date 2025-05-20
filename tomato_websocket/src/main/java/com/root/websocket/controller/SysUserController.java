package com.root.websocket.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.root.dto.*;
import com.root.entity.outside.SysBaseServiceModel;
import com.root.entity.outside.SysUserModel;
import com.root.entity.outside.sys_ai_serviceModel;
import com.root.mapper.SysUserMapper;
import com.root.memory.LoginTokenMemory;
import com.root.sevice.SysBaseServiceService;
import com.root.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/SysUser")
public class SysUserController {
    @Resource
    SysUserMapper sysUserMapper;
    @Autowired
    SysBaseServiceService sysBaseServiceService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ReturnMessage list() {
        List list=sysUserMapper.list();
        return ResponseUtil.success(list);
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ReturnMessage getUser(HttpServletRequest httpServletRequest ) {
        String token = httpServletRequest.getHeader("access-token");
        LoginDto loginDto = LoginTokenMemory.getUserIdByToken(token);
        if (loginDto==null){
            return ResponseUtil.fail();
        }
        SysUserModel sysUserModel = sysBaseServiceService.getBaseServiceMap(SysUserModel.class, new SysBaseServiceDTO("getUserInfo", new HashMap<String, String>() {
            {
                put("sys_user_id", loginDto.getSys_user_id());
            }
        }));


      JSONObject jsonObject= JSONUtil.parseObj("{\n" +
              "\t\"id\": \"4291d7da9005377ec9aec4a71ea837f\",\n" +
              "\t\"name\": \""+sysUserModel.getName()+"\",\n" +
              "\t\"username\": \""+sysUserModel.getUsername()+"\",\n" +
              "\t\"password\": \"\",\n" +
              "\t\"tel\": \""+sysUserModel.getTel()+"\",\n" +
              "\t\"email\": \""+sysUserModel.getEmail()+"\",\n" +
              "\t\"personal_profile\": \""+sysUserModel.getPersonal_profile()+"\",\n" +
              "\t\"system_style\": \""+sysUserModel.getSystem_style()+"\",\n" +
              "\t\"basic_color\": \""+sysUserModel.getBasic_color()+"\",\n" +
              "\t\"avatar\": \"/avatar2.jpg\",\n" +
              "\t\"status\": 1,\n" +
              "\t\"telephone\": \"\",\n" +
              "\t\"lastLoginIp\": \"27.154.74.117\",\n" +
              "\t\"lastLoginTime\": 1534837621348,\n" +
              "\t\"creatorId\": \"admin\",\n" +
              "\t\"createTime\": 1497160610259,\n" +
              "\t\"merchantCode\": \"TLif2btpzg079h15bk\",\n" +
              "\t\"deleted\": 0,\n" +
              "\t\"roleId\": \"admin\",\n" +
              "\t\"role\": {\n" +
              "\t\t\"id\": \"admin\",\n" +
              "\t\t\"name\": \"管理员\",\n" +
              "\t\t\"describe\": \"拥有所有权限\",\n" +
              "\t\t\"status\": 1,\n" +
              "\t\t\"creatorId\": \"system\",\n" +
              "\t\t\"createTime\": 1497160610259,\n" +
              "\t\t\"deleted\": 0,\n" +
              "\t\t\"permissions\": [{\n" +
              "\t\t\t\"roleId\": \"admin\",\n" +
              "\t\t\t\"permissionId\": \"dashboard\",\n" +
              "\t\t\t\"permissionName\": \"仪表盘\",\n" +
              "\t\t\t\"actions\": \"[{\\\"action\\\":\\\"add\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"新增\\\"},{\\\"action\\\":\\\"query\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"查询\\\"},{\\\"action\\\":\\\"get\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"详情\\\"},{\\\"action\\\":\\\"update\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"修改\\\"},{\\\"action\\\":\\\"delete\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"删除\\\"}]\",\n" +
              "\t\t\t\"actionEntitySet\": [{\n" +
              "\t\t\t\t\"action\": \"add\",\n" +
              "\t\t\t\t\"describe\": \"新增\",\n" +
              "\t\t\t\t\"defaultCheck\": false\n" +
              "\t\t\t}, {\n" +
              "\t\t\t\t\"action\": \"query\",\n" +
              "\t\t\t\t\"describe\": \"查询\",\n" +
              "\t\t\t\t\"defaultCheck\": false\n" +
              "\t\t\t}, {\n" +
              "\t\t\t\t\"action\": \"get\",\n" +
              "\t\t\t\t\"describe\": \"详情\",\n" +
              "\t\t\t\t\"defaultCheck\": false\n" +
              "\t\t\t}, {\n" +
              "\t\t\t\t\"action\": \"update\",\n" +
              "\t\t\t\t\"describe\": \"修改\",\n" +
              "\t\t\t\t\"defaultCheck\": false\n" +
              "\t\t\t}, {\n" +
              "\t\t\t\t\"action\": \"delete\",\n" +
              "\t\t\t\t\"describe\": \"删除\",\n" +
              "\t\t\t\t\"defaultCheck\": false\n" +
              "\t\t\t}]\n" +
              "\t\t}]\n" +
              "\t}\n" +
              "}");
        return ResponseUtil.success(jsonObject);
    }
}
