package com.root.websocket.controller;

import com.root.dto.ReturnMessage;
import com.root.dto.SheetDTO;
import com.root.sevice.SysReportColumnService;
import com.root.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/SysReport")
public class SysReportController {
    @Autowired
    SysReportColumnService sysReportColumnService;
    @RequestMapping(value = "/out", method = RequestMethod.POST)
    public ReturnMessage out(@RequestBody SheetDTO sheetDTO) {
        return ResponseUtil.success(sysReportColumnService.OutReportcolumn(sheetDTO.getSysReportId()));
    }
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ReturnMessage get( @RequestBody  SheetDTO sheetDTO) {
        sysReportColumnService.SaveReportColumn(sheetDTO);
        return ResponseUtil.success();
    }

}
