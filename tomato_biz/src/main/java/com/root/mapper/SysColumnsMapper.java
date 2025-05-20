package com.root.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.root.entity.SysColumnsEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SysColumnsMapper extends BaseMapper<SysColumnsEntity> {
    List<SysColumnsEntity> list();
}
