package com.root.entity.outside;

import lombok.Data;

@Data
public class SysFilesModel {
    /**
     * 文件夹上级id
     */
    private  String sys_files_pid ;

    /**
     * 文件id
     */
    private  String sys_files_id ;

    /**
     * 文件键值
     */
    private  String sys_files_value ;

    /**
     * 文件夹名称
     */
    private  String sys_files_name ;
}
