package com.root.sevice;

import com.root.dto.DownDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
//提供系统文件类的操作接口类
public interface SysFileService {
    public String Save(MultipartFile multipartFile, String sys_files_value);
    public void Down(HttpServletResponse httpServletResponse, DownDTO downDTO) ;
}
