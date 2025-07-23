package com.root.websocket.controller;
import com.root.dto.ReturnMessage;
import com.root.dto.SysBaseServiceDTO;
import com.root.sevice.SysBaseServiceService;
import com.root.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/SysBaseService") // 定义请求路径为/SysBaseService
public class SysBaseServiceController {
    @Autowired
    SysBaseServiceService sysBaseServiceService; // 注入SysBaseServiceService
    @RequestMapping(value = "/getBaseService", method = RequestMethod.POST) // 定义请求路径为/getBaseService，请求方法为POST
    public ReturnMessage getBaseService(@RequestBody SysBaseServiceDTO sysBaseServiceDTO) { // 接收SysBaseServiceDTO参数
        return ResponseUtil.success(sysBaseServiceService.getBaseService(sysBaseServiceDTO)); // 调用sysBaseServiceService的getBaseService方法，返回结果
    }
}
