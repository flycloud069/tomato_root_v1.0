package com.root.websocket.controller;

import com.root.dto.ReturnMessage;
import com.root.sevice.SysUpdateServer;
import com.root.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SysUpdate")
public class SysUpdateController {
    @Autowired
    SysUpdateServer sysUpdateServer;
    @RequestMapping(value = "/OutPutUpdateString", method = RequestMethod.POST)
    public ReturnMessage OutPutUpdateString() {
        return ResponseUtil.success(sysUpdateServer.OutPutUpdateString());
    }
}
