package com.root.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SysFileUploadDTO {
    private String file;
    private String sys_files_vale;
    private String fileName;
    private String fileType;
}
