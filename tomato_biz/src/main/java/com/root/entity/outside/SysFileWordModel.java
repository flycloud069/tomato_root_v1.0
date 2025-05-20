package com.root.entity.outside;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SysFileWordModel {
    /**
     * 文件夹id
     */
    private String sys_files_id;
    /**
     * word文件名称
     */
    private String sys_file_word_name;
    /**
     * 系统word文件id
     */
    private String sys_file_word_id;
    /**
     * 系统word文件大小
     */
    private String sys_file_word_size;

    /**
     * 系统word文件页数
     */
    private String  sys_file_word_page;
}
