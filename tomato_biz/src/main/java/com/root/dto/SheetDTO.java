package com.root.dto;

import lombok.Data;

import java.util.List;
@Data
public class SheetDTO {
    /**单元格*/
    private  List<CellDTO> cellDTOS;
    /**报表id*/
    private  String sysReportId;

    public SheetDTO getSheetDTO(List<CellDTO> cellDTOS,String sysReportId){
        this.cellDTOS=cellDTOS;
        this.sysReportId=sysReportId;
        return  this;
    }
}
