package com.root.websocket.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.root.dto.ReturnMessage;
import com.root.dto.SysExportDTO;
import com.root.dto.SysServiceDto;
import com.root.sevice.SysModelExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/SysExport")
public class SysExportController {
    @Autowired
    SysModelExportService sysModelExportService;
    @RequestMapping(value = "/wordModelExport", method = RequestMethod.POST)
    public void wordModelExport(HttpServletResponse httpServletResponse,@RequestBody SysExportDTO sysExportDTO) {
        sysModelExportService.wordModelExport(httpServletResponse,sysExportDTO.getFileName(),sysExportDTO.getSys_files_value(), JSONUtil.parseObj(sysExportDTO.getJsonObjectStr()));
    }

    }
