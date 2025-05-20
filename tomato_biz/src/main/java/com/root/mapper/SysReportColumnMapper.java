package com.root.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.root.entity.SysReportColumnEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SysReportColumnMapper extends BaseMapper<SysReportColumnEntity> {
    List<SysReportColumnEntity> list();

}
