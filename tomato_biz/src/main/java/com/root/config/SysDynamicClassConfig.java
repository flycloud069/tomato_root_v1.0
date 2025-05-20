package com.root.config;

import com.root.dto.SysBaseServiceDTO;
import com.root.entity.outside.SysDynamicMethodModel;
import com.root.memory.SysClassMemory;
import com.root.sevice.SysBaseServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;

@Configuration
public class SysDynamicClassConfig {
    @Autowired
    SysBaseServiceService sysBaseServiceService;
    @Bean
    public void initSysDynamicClass() {
        List<SysDynamicMethodModel> sysDynamicMethodModels = sysBaseServiceService.getBaseServiceList(SysDynamicMethodModel.class, new SysBaseServiceDTO("getAllsysDynamicMethodId", new HashMap<String, String>() {{}}));
        SysClassMemory.initSysClass(sysDynamicMethodModels);
    }

}
