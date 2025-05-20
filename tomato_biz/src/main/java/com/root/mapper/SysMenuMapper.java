package com.root.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.root.dto.LoginDto;
import com.root.dto.SysFunctionDTO;
import com.root.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.awt.*;
import java.util.List;
import java.util.Map;

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {
    List getMenu( @Param("loginDto") LoginDto loginDto);
    int saveSysRoleFunction(@Param("sysFunctionDTOList")List<SysFunctionDTO> sysFunctionDTOList);
    int deleteSysRoleFunction(@Param("sysFunctionDTOList")List<SysFunctionDTO> sysFunctionDTOList);
}
