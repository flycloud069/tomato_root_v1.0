package com.root.sevice;

import com.root.dto.LoginDto;
import com.root.entity.outside.SysExtractServiceModel;
import com.root.entity.outside.sys_extracts_serviceModel;

import java.util.List;

public interface SysExtractsService {
    public Boolean runSysExtractsService(String sys_extracts_service_id, String inputString);

    public  Boolean startScheduled(LoginDto loginDto, String cron, String sys_extracts_service_id, String inputString);

    public  Boolean cancel(String sys_extracts_service_id);

    public List<sys_extracts_serviceModel> getScheduledList();
}
