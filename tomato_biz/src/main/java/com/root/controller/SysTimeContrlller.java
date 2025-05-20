package com.root.controller;

import cn.hutool.core.date.DateUtil;
import com.root.dto.ReturnMessage;
import com.root.dto.TimeDTO;
import com.root.util.ResponseUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SysTime")
public class SysTimeContrlller {
    @RequestMapping(value = "/getNow", method = RequestMethod.POST)
    public ReturnMessage getNow() {
        TimeDTO timeDTO=new TimeDTO();
        timeDTO.setNowData(DateUtil.now());
        return ResponseUtil.success(timeDTO);
    }
}
