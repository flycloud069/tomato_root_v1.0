package com.root.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.root.dto.InsertSysServiceParamDTO;
import com.root.dto.SysServiceDeleteDTO;
import com.root.dto.SysServiceParamDto;
import com.root.entity.SysServiceParamEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysServiceParamMapper extends BaseMapper<SysServiceParamEntity> {
 List<SysServiceParamEntity> SelSysServiceParamBySysServiceId(@Param("sys_service_id")String sys_service_id);
 int insertSelSysServiceParam(@Param("insertSysServiceParamDTO")InsertSysServiceParamDTO insertSysServiceParamDTO);
 int deleteSelSysServiceParam(@Param("sysServiceValue")String sysServiceValue);
}
