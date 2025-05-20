package com.root.websocket.controller;

import com.root.dto.ReturnMessage;
import com.root.dto.SysAiServiceDTO;
import com.root.sevice.SysAiService;
import com.root.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SysAiService")
public class SysAiServiceController {
    @Autowired
    SysAiService sysAiService;
    @RequestMapping(value = "/execAi", method = RequestMethod.POST)
    public ReturnMessage execAi(@RequestBody SysAiServiceDTO sysAiServiceDTO) {
        return ResponseUtil.success(sysAiService.execAi(sysAiServiceDTO));
    }
}
