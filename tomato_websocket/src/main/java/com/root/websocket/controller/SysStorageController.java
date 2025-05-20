package com.root.websocket.controller;

import com.root.dto.ReturnMessage;
import com.root.dto.SysServiceDto;
import com.root.dto.SysStorageDTO;
import com.root.sevice.Imlp.SysStorageServiceImpl;
import com.root.sevice.SysStorageService;
import com.root.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SysStorage")
public class SysStorageController {
    @Autowired
    SysStorageService sysStorageService;
    @RequestMapping(value = "/putTable", method = RequestMethod.POST)
    public ReturnMessage putTable(@RequestBody SysStorageDTO sysStorageDTO) {
       return ResponseUtil.success(sysStorageService.putTable(sysStorageDTO.getSys_json_parse_relate_sys_storage_service_id(),sysStorageDTO.getPutJson()));
    }
}
