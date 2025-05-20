package com.root.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.root.dto.CellDTO;
import lombok.Data;

@Data
@TableName("sys_report_column")
@Table(name = "sys_report_column")
public class SysReportColumnEntity {
    @IsKey
    @Column(name="sys_report_id",type= MySqlTypeConstant.CHAR,comment = "系统报表id" ,length = 36,isNull = false)
    private String sysReportId;
    @IsKey
    @Column(name="sys_report_column_pid",type= MySqlTypeConstant.CHAR,comment = "系统报表列父id" ,length = 36,isNull = false)
    private String sysReportColumnPid;
    @Column(name="sys_report_column_id",type= MySqlTypeConstant.CHAR,comment = "系统报表列id" ,length = 36,isNull = false)
    private String sysReportColumnId;
    @Column(name="sys_report_column_index",type= MySqlTypeConstant.INT,comment = "系统报表列序号" ,isNull = false)
    private int sysReportColumnIndex;
    @IsKey
    @Column(name="sys_report_column_name",type= MySqlTypeConstant.VARCHAR,comment = "系统报表列名称" ,length = 255,isNull = false)
    private String sysReportColumnName;
    @Column(name="sys_report_column_value",type= MySqlTypeConstant.VARCHAR,comment = "系统报表列名称" ,length = 255,isNull = true)
    private String sysReportColumnValue;
    @Column(name="sys_report_column_height",type= MySqlTypeConstant.INT,comment = "系统报表列高度" ,isNull = false)
    private int sysReportColumnHeight;
    @Column(name="sys_report_column_width",type= MySqlTypeConstant.INT,comment = "系统报表列宽度"  ,isNull = false)
    private int sysReportColumnWidth;
    @Column(name="sys_report_column_horizontaltype",type= MySqlTypeConstant.CHAR,comment = "系统报表水平对齐" ,length = 1,isNull = false)
    private String sysReportColumnHorizontaltype;
    @Column(name="sys_report_column_verticaltype",type= MySqlTypeConstant.CHAR ,comment = "系统报表垂直对齐"  ,length = 1,isNull = false)
    private String sysReportColumnVerticaltype;




    public CellDTO SysReportColumnEntityToCellDTO(int index,int colunm) {
        CellDTO cellDTO =new CellDTO();
        cellDTO.setW(this.sysReportColumnWidth);
        cellDTO.setG(this.sysReportColumnHeight);
        cellDTO.setN(this.sysReportColumnName);
        cellDTO.setHt(this.sysReportColumnHorizontaltype);
        cellDTO.setVt(this.sysReportColumnVerticaltype);
        cellDTO.setId(this.sysReportColumnId);
        cellDTO.setPid(this.sysReportColumnPid);
        cellDTO.setC(colunm);
        cellDTO.setR(index);
        cellDTO.setV(this.sysReportColumnValue);
        return  cellDTO;
    }

}