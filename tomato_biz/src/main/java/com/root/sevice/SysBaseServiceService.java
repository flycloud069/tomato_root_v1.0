package com.root.sevice;

import com.root.dto.SysBaseServiceDTO;

import java.util.List;
import java.util.Map;

public interface SysBaseServiceService {
    public Map getBaseService(SysBaseServiceDTO sysBaseServiceDTO);
    public List<Map<String,String>> getBaseServiceNotT1(SysBaseServiceDTO sysBaseServiceDTO);
    public <T> T  getBaseServiceMap(Class<T> tClass,SysBaseServiceDTO sysBaseServiceDTO) ;
    public <T> List<T>  getBaseServiceList(Class<T> tClass,SysBaseServiceDTO sysBaseServiceDTO) ;
}
