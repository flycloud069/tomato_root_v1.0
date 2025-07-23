package com.root.sevice;

import cn.hutool.json.JSONObject;
import com.root.dto.SysServiceDto;

import javax.servlet.http.HttpServletResponse;

public interface SysModelExportService {
    public void wordModelExport(HttpServletResponse httpServletResponse, String fileName, String sys_files_value, JSONObject jsonObject);
    public void pptModelExport(HttpServletResponse httpServletResponse, String fileName, String sys_files_value, JSONObject jsonObject);

}
