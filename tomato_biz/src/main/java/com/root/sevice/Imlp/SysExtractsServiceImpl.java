package com.root.sevice.Imlp;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.root.dto.LoginDto;
import com.root.dto.ScheduledDTO;
import com.root.dto.SysBaseServiceDTO;
import com.root.entity.outside.SysExtractServiceModel;
import com.root.entity.outside.sys_extracts_serviceModel;
import com.root.entity.outside.sys_extracts_service_relat_sys_extract_serviceModel;
import com.root.memory.ScheduledMemory;
import com.root.sevice.SysBaseServiceService;
import com.root.sevice.SysDynamicMethodService;
import com.root.sevice.SysExtractService;
import com.root.sevice.SysExtractsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysExtractsServiceImpl implements SysExtractsService {
    @Autowired
    SysBaseServiceService sysBaseServiceService;
    @Autowired
    SysDynamicMethodService sysDynamicMethodService;
    @Autowired
    SysExtractService sysExtractService;

    public Boolean runSysExtractsService(String sys_extracts_service_id, String inputString) {
        sys_extracts_serviceModel sys_extracts_serviceModel = sysBaseServiceService.getBaseServiceMap(sys_extracts_serviceModel.class, new SysBaseServiceDTO("GetOneSysExtractServices", new HashMap<String, String>() {
            {
                put("sys_extracts_service_id", sys_extracts_service_id);
            }
        }));
        List<sys_extracts_service_relat_sys_extract_serviceModel> sys_extracts_service_relat_sys_extract_serviceModels = sysBaseServiceService.getBaseServiceList(sys_extracts_service_relat_sys_extract_serviceModel.class, new SysBaseServiceDTO("Selsys_extracts_service_relat_sys_extract_service", new HashMap<String, String>() {
            {
                put("sys_extracts_service_id", sys_extracts_service_id);
            }
        }));
        String s = sysDynamicMethodService.execDynamicMethod(sys_extracts_serviceModel.getSys_dynamic_method_id(), inputString);
        List list = JSONUtil.parseArray(s);
        list.stream().forEach(id ->
                sys_extracts_service_relat_sys_extract_serviceModels.stream()
                        .forEach(sys_extracts_service_relat_sys_extract_serviceModel ->
                                sysExtractService.ExtractData(sys_extracts_service_relat_sys_extract_serviceModel.getSys_extract_service_id(), (String) id)
                        ));
        return true;
    }


    public  Boolean startScheduled(LoginDto loginDto, String cron, String sys_extracts_service_id, String inputString) {
       return ScheduledMemory.startScheduled(loginDto,cron,sys_extracts_service_id,inputString);
    }

    public  Boolean cancel(String sys_extracts_service_id) {
        return ScheduledMemory.cancel(sys_extracts_service_id);
    }

    public  List<sys_extracts_serviceModel> getScheduledList() {
        List<sys_extracts_serviceModel> sysExtractServiceModels = sysBaseServiceService.getBaseServiceList(sys_extracts_serviceModel.class, new SysBaseServiceDTO("GetOneSysExtractServices", ScheduledMemory.getScheduledList()));
        return sysExtractServiceModels;

    }
}
