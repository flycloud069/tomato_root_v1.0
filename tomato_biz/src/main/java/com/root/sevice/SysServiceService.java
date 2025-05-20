package com.root.sevice;

import com.baomidou.mybatisplus.extension.service.IService;

import com.root.dto.SysServiceDto;
import com.root.entity.SysServiceEntity;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

public interface SysServiceService extends IService<SysServiceEntity> {
    /*拼接sql语句*/
    public String execSql(SysServiceDto sysServiceDto);

    /*调用系统服务返回ist */
    public List esecSysServiceList(SysServiceDto sysServiceDto);
    public List<Map<String,String>> esecSysServiceListMap(SysServiceDto sysServiceDto);

    /*调用系统服务返回Map */
    public Map esecSysService(SysServiceDto sysServiceDto);

    /*批量调用系统服务返回Map*/
    public Map esecSysService(String sql) ;
    public Map esecSysServiceNoSemicolon(String sql) ;
    /*批量调用系统服务返回Map*/
    public List esecSysServiceList (String sql) ;

    /*调用系统服务返回List对象 */
    public <T> List<T>  esecSysServiceModelList( Class<T> clazz , SysServiceDto sysServiceDto);

    /*调用系统服务返回对象 */
    public <T> T esecSysServiceModel(Class<T> clazz , SysServiceDto sysServiceDto) ;
}
