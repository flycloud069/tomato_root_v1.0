package com.root.websocket.controller;

import com.root.dto.JsonParseInPutDTO;
import com.root.dto.ReturnMessage;
import com.root.dto.SysDynamicMethodDTO;
import com.root.sevice.SysDynamicMethodService;
import com.root.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SysDynamicMethod")
public class SysDynamicMethodController {
    @Autowired
    SysDynamicMethodService sysDynamicMethodService;

    @RequestMapping(value = "/execDynamicMethod", method = RequestMethod.POST)
    public ReturnMessage execDynamicMethod(@RequestBody SysDynamicMethodDTO sysDynamicMethodDTO) {
        return ResponseUtil.success(sysDynamicMethodService.execDynamicMethod(sysDynamicMethodDTO.getSys_dynamic_method_id(),sysDynamicMethodDTO.getInputString()));
    }
    }
