package com.root.dto;

import cn.hutool.core.convert.Convert;
import com.root.entity.SysReportColumnEntity;
import lombok.Data;

import java.util.List;

@Data
public class CellDTO {
    /**行数*/
    private   int r;
    /**列数*/
    private  int c;
    /**值*/
    private  String v;
    /**值*/
    private  String n;
    /**高度*/
    private  int g;
    /**宽度*/
    private  int w;
    /**垂直对齐方式*/
    private  String vt="0";
    /**水平对齐方式*/
    private  String ht="0";
    /**所占行数*/
    private  int rs=1;
    /**所占列数*/
    private  int cs=1 ;
   /**id*/
    private  String id;
    /**pid*/
    private  String pid="";

     public  SysReportColumnEntity cellDTOToSysReportColumnEntity(String sysReportId)
     {
        SysReportColumnEntity sysReportColumnEntity= new  SysReportColumnEntity();
        sysReportColumnEntity.setSysReportColumnValue(this.v);
        sysReportColumnEntity.setSysReportColumnHeight(this.g);
        sysReportColumnEntity.setSysReportColumnWidth(this.w);
        sysReportColumnEntity.setSysReportColumnHorizontaltype(this.ht);
        sysReportColumnEntity.setSysReportColumnVerticaltype(this.vt);
        sysReportColumnEntity.setSysReportColumnId(this.id);
        sysReportColumnEntity.setSysReportColumnPid(this.pid);
        sysReportColumnEntity.setSysReportId(sysReportId);
        sysReportColumnEntity.setSysReportColumnIndex(this.c);
        sysReportColumnEntity.setSysReportColumnName(this.n);
      return sysReportColumnEntity;
     }
     @Override
     public boolean equals(Object obj) {
         if (obj instanceof CellDTO) {
             CellDTO cellDTO = (CellDTO) obj;
             return this.c == cellDTO.getC()&&this.r == cellDTO.getR();
         }
         else {
             return false;
         }
     }
}
