package com.root.config;

import cn.hutool.core.bean.BeanUtil;
import com.root.dto.SysBaseServiceDTO;
import com.root.entity.outside.SysConfigModel;
import com.root.entity.outside.SysUserModel;
import com.root.sevice.SysBaseServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
@Configuration
public class SysConfig {

   private static ConcurrentHashMap<String,String>  configMap = new ConcurrentHashMap<>();
    @Autowired
    SysBaseServiceService sysBaseServiceService;
    @Bean
    public void init(){
        List<SysConfigModel> sysConfigModelList= sysBaseServiceService.getBaseServiceList(SysConfigModel.class, new SysBaseServiceDTO("SelOneSysConfig", BeanUtil.beanToMap(new HashMap<>())));
        sysConfigModelList.stream().forEach(sysConfigModel -> configMap.put(sysConfigModel.getSys_config_value(),sysConfigModel.getSys_config_value_value()));
    }
   public static String getconfig(String value){
      return configMap.get(value);
   }
}
