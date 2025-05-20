package com.root.sevice;

import com.root.dto.DownDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//提供word文件类的文件操作
public interface SysWordFileService {
    public String Save(MultipartFile multipartFile, String sys_files_value);
    public void Down(HttpServletResponse httpServletResponse, DownDTO downDTO) ;
}
