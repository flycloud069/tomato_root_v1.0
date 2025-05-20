package com.root.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.root.entity.SysServiceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface SysServiceMapper extends BaseMapper<SysServiceEntity> {
    List<SysServiceEntity> list();

    List<SysServiceEntity> selSysServiceByVlue(@Param("sys_service_value") String sys_service_value);

    List getSysService(@Param("sql") String sql);

    int insertSysService(@Param("sys_service_name") String sysServiceName, @Param("sys_service_value") String sysServiceValue, @Param("sys_service_sql") String sysServiceSql);
}
