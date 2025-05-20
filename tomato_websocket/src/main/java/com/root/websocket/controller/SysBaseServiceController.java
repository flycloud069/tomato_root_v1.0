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
@RequestMapping("/SysBaseService")
public class SysBaseServiceController {
    @Autowired
    SysBaseServiceService sysBaseServiceService;
    @RequestMapping(value = "/getBaseService", method = RequestMethod.POST)
    public ReturnMessage getBaseService(@RequestBody SysBaseServiceDTO sysBaseServiceDTO) {
        return ResponseUtil.success(sysBaseServiceService.getBaseService(sysBaseServiceDTO));
    }
}
