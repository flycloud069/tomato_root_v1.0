package com.root.sevice.Imlp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.root.common.SysfileCommon;
import com.root.dto.SysBaseServiceDTO;
import com.root.entity.outside.SysFilesModel;
import com.root.sevice.SysBaseServiceService;
import com.root.sevice.SysFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysFilesServiceImpl implements SysFilesService {
    @Autowired
    SysBaseServiceService sysBaseServiceService;

    public String getid(String SysFilesVale) {
        SysFilesModel sysFilesModelList = sysBaseServiceService.getBaseServiceMap(SysFilesModel.class, new SysBaseServiceDTO("GetOneSysFiles", new HashMap<String, String>() {
            {
                put("sys_files_value", SysFilesVale);
            }     }));
        return sysFilesModelList.getSys_files_id();
    }


    public String getPath(String SysFilesVale) {
        SysFilesModel sysFilesModelList = sysBaseServiceService.getBaseServiceMap(SysFilesModel.class, new SysBaseServiceDTO("GetOneSysFiles", new HashMap<String, String>() {
            {
                put("sys_files_value", SysFilesVale);
            }
        }));
        return SysfileCommon.rootPath()+getParantSysFiles(sysFilesModelList.getSys_files_id());
    }

    private String getParantSysFiles(String sys_files_id) {

        SysFilesModel sysFilesModel = sysBaseServiceService.getBaseServiceMap(SysFilesModel.class, new SysBaseServiceDTO("GetParantSysFiles", new HashMap<String, String>() {
            {
                put("sys_files_id", sys_files_id);
            }
        }));
        if (StrUtil.equals(sysFilesModel.getSys_files_pid(),"")) {
            return "\\" + sysFilesModel.getSys_files_value();
        } else {

            return getParantSysFiles(sysFilesModel.getSys_files_pid()) + "\\" + sysFilesModel.getSys_files_value() ;
        }

    }

}
