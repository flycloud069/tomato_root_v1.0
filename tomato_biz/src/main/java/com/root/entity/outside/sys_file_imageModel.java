package com.root.entity.outside;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class sys_file_imageModel {
    /**
     * 系统image文件id
     */
    private String sys_file_image_id;

    /**
     * 系统image文件名称
     */
    private String sys_file_image_name;
    /**
     * 系统文件夹id
     */
    private String sys_files_id;
    /**
     * 系统image文件大小
     */
    private String sys_file_image_size;
    /**
     * 系统image文件宽度
     */
    private String sys_file_image_width;
    /**
     * 系统image文件长度
     */
    private String sys_file_image_height;


}