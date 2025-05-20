package com.root.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.root.entity.SysColumnsEntity;
import com.root.entity.SysTableEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SysTableMapper  extends BaseMapper<SysTableEntity> {
    List<SysTableEntity> list();
}
