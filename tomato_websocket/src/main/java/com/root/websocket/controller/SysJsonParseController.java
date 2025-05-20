package com.root.websocket.controller;

import com.root.dto.JsonParseInPutDTO;
import com.root.dto.ReturnMessage;
import com.root.dto.SysFunctionDTO;
import com.root.sevice.SysJsonParseService;
import com.root.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/SysJsonParse")
public class SysJsonParseController {
    @Autowired
    SysJsonParseService sysJsonParseService;

    @RequestMapping(value = "/jsonParse", method = RequestMethod.POST)
    public ReturnMessage jsonParse(@RequestBody JsonParseInPutDTO jsonParseInPutDTO) {
        return ResponseUtil.success(sysJsonParseService.jsonParse(jsonParseInPutDTO.getSys_json_parse_service_test_context(), jsonParseInPutDTO.getSys_json_parse_service_id()));
    }
}
