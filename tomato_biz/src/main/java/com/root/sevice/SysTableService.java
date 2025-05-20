package com.root.sevice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.root.dto.SysCreatTableDTO;
import com.root.entity.SysTableEntity;

public interface SysTableService  extends IService<SysTableEntity> {
    public String creatSQL(SysCreatTableDTO sysCreatTableDTO);
    public String creatTableSQL(SysCreatTableDTO sysCreatTableDTO);
    public String getTableModelString(String table_schema, String table_name);
}
