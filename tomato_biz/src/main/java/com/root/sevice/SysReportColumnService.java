package com.root.sevice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.root.dto.SheetDTO;
import com.root.entity.SysReportColumnEntity;

public interface SysReportColumnService extends IService<SysReportColumnEntity> {

    public  Boolean SaveReportColumn(SheetDTO sheetDTO);
    public SheetDTO OutReportcolumn(String sysReportId) ;
}
