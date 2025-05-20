package com.root.sevice.Imlp;

import com.root.dto.SysBaseServiceDTO;
import com.root.entity.outside.SysExtractServiceModel;
import com.root.sevice.SysBaseServiceService;
import com.root.sevice.SysDynamicMethodService;
import com.root.sevice.SysExtractService;
import com.root.sevice.SysStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SysExtractServiceImpl implements SysExtractService {
    @Autowired
    SysBaseServiceService sysBaseServiceService;
    @Autowired
    SysDynamicMethodService sysDynamicMethodService;
    @Autowired
    SysStorageService sysStorageService;

    public Boolean ExtractData(String sys_extract_service_id,String inputString){
        SysExtractServiceModel sysExtractServiceModel = sysBaseServiceService.getBaseServiceMap(SysExtractServiceModel.class, new SysBaseServiceDTO("getOneSysExtractService", new HashMap<String, String>() {
            {
                put("sys_extract_service_id", sys_extract_service_id);
            }
        }));
        String outPutString = sysDynamicMethodService.execDynamicMethod(sysExtractServiceModel.getSys_dynamic_method_id(),inputString);
        return sysStorageService.putTable(sysExtractServiceModel.getSys_json_parse_relate_sys_storage_service_id(),outPutString);
    }
}
