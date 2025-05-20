package com.root.websocket.controller;

import com.root.dto.LoginDto;
import com.root.dto.ReturnMessage;
import com.root.dto.SysExtractsServiceDTO;
import com.root.memory.LoginTokenMemory;
import com.root.sevice.SysExtractsService;
import com.root.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/SysExtractsService")
public class SysExtractsServiceController {
    @Autowired
    SysExtractsService sysExtractsService;

    @RequestMapping(value = "/runSysExtractsService", method = RequestMethod.POST)
    public ReturnMessage runSysExtractsService(@RequestBody SysExtractsServiceDTO sysExtractsServiceDTO) {
        return ResponseUtil.success(sysExtractsService.runSysExtractsService(sysExtractsServiceDTO.getSys_extracts_service_id(),sysExtractsServiceDTO.getInputString()));
    }
    @RequestMapping(value = "/startScheduled", method = RequestMethod.POST)
    public ReturnMessage startScheduled(HttpServletRequest httpServletRequest, @RequestBody SysExtractsServiceDTO sysExtractsServiceDTO) {
        LoginDto loginDto = LoginTokenMemory.getUserIdByToken(httpServletRequest.getHeader("access-token"));
        return ResponseUtil.success(sysExtractsService.startScheduled(loginDto,sysExtractsServiceDTO.getSys_extracts_service_cron(),sysExtractsServiceDTO.getSys_extracts_service_id(),sysExtractsServiceDTO.getInputString()));
    }
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public ReturnMessage cancel(@RequestBody SysExtractsServiceDTO sysExtractsServiceDTO) {
        return ResponseUtil.success(sysExtractsService.cancel(sysExtractsServiceDTO.getSys_extracts_service_id()));
    }
    @RequestMapping(value = "/getScheduledList", method = RequestMethod.POST)
    public ReturnMessage getScheduledList() {
        return ResponseUtil.success(sysExtractsService.getScheduledList());
    }

}
