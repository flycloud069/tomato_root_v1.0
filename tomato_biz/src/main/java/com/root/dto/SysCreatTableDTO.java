package com.root.dto;

import lombok.Data;

@Data
public class SysCreatTableDTO {
    private  String table_schema;
    private  String table_name;


    public SysCreatTableDTO  getSysCreatTableDTO(String table_schema, String table_name){
     this.table_schema=table_schema;
     this.table_name=table_name;
     return this;
 }
}
