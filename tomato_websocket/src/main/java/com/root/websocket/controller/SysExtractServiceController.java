package com.root.websocket.controller;

import com.root.dto.JsonParseInPutDTO;
import com.root.dto.ReturnMessage;
import com.root.dto.SysExtractServiceDTO;
import com.root.sevice.SysExtractService;
import com.root.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SysExtractService")
public class SysExtractServiceController {
    @Autowired
    SysExtractService sysExtractService;

    @RequestMapping(value = "/ExtractData", method = RequestMethod.POST)
    public ReturnMessage ExtractData(@RequestBody SysExtractServiceDTO sysExtractServiceDTO) {
        return ResponseUtil.success(sysExtractService.ExtractData(sysExtractServiceDTO.getSys_extract_service_id(),sysExtractServiceDTO.getInputString()));
    }
}
