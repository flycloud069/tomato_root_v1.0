package com.root.sevice.Imlp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.root.dto.SysBaseServiceDTO;
import com.root.dto.SysServiceDto;
import com.root.entity.outside.SysBaseImportServiceModel;
import com.root.entity.outside.SysBaseOutputServiceModel;
import com.root.entity.outside.SysBaseServiceModel;
import com.root.mapper.SysServiceMapper;
import com.root.sevice.SysBaseServiceService;
import com.root.sevice.SysServiceService;
import com.root.util.SqlStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SysBaseServiceServiceImpl  implements SysBaseServiceService {
    @Autowired
    SysServiceService sysServiceService;

    public <T> T  getBaseServiceMap(Class<T> tClass,SysBaseServiceDTO sysBaseServiceDTO) {
        Map map =getBaseService(sysBaseServiceDTO);
        return  BeanUtil.toBean(((List) map.get("T1")).get(0),tClass);
    }

    public <T> List<T>  getBaseServiceList(Class<T> tClass,SysBaseServiceDTO sysBaseServiceDTO) {
        List<T> ts = new ArrayList<T>();
        Map map =getBaseService(sysBaseServiceDTO);
        if (((List) map.get("T1")) instanceof List) {
            ((List) map.get("T1")).stream().forEach(o -> ts.add(BeanUtil.toBean(o, tClass)));
        }
        return  ts;
    }

    public List<Map<String,String>> getBaseServiceNotT1(SysBaseServiceDTO sysBaseServiceDTO){
        return (List<Map<String,String>>) getBaseService(sysBaseServiceDTO).get("T1");
    };

    public Map getBaseService(SysBaseServiceDTO sysBaseServiceDTO) {
        SysBaseServiceModel sysBaseServiceModel = sysServiceService.esecSysServiceModel(SysBaseServiceModel.class, new SysServiceDto().getSysServiceDto("getOneSysBaseService",  new JSONObject().put("sys_base_service_value", sysBaseServiceDTO.getSys_base_service_value())));

        List<SysBaseImportServiceModel> sysBaseImportServiceModelList = sysServiceService.esecSysServiceModelList(SysBaseImportServiceModel.class, new SysServiceDto().getSysServiceDto("getSysBaseImportService", new JSONObject().put("sys_base_service_id", sysBaseServiceModel.getSys_base_service_id())));

        List<SysBaseOutputServiceModel> sysBaseOutputServiceModelList = sysServiceService.esecSysServiceModelList(SysBaseOutputServiceModel.class, new SysServiceDto().getSysServiceDto("GetSysBaseOutputService", new JSONObject().put("sys_base_service_id", sysBaseServiceModel.getSys_base_service_id())));
        String creatSql = "";
        switch (sysBaseServiceModel.getSys_base_service_type()) {
            case "新增":
                if (sysBaseServiceDTO.getSys_base_service_import_param() instanceof List) {
                    for (Map sysBaseServiceImportParam : ((List<Map<String, String>>) sysBaseServiceDTO.getSys_base_service_import_param())) {
                        creatSql += getAddCreatSql(sysBaseServiceImportParam, sysBaseServiceModel, sysBaseImportServiceModelList, sysBaseOutputServiceModelList);
                    }
                } else {
                    creatSql += getAddCreatSql(((Map<String, String>) sysBaseServiceDTO.getSys_base_service_import_param()), sysBaseServiceModel, sysBaseImportServiceModelList, sysBaseOutputServiceModelList);
                }
                break;
            case "删除":
                if (sysBaseServiceDTO.getSys_base_service_import_param() instanceof List) {
                    for (Map sysBaseServiceImportParam : ((List<Map<String, String>>) sysBaseServiceDTO.getSys_base_service_import_param())) {
                        creatSql += getDelCreatSql( sysBaseServiceImportParam, sysBaseServiceModel, sysBaseImportServiceModelList, sysBaseOutputServiceModelList);
                    }
                } else {
                    creatSql += getDelCreatSql( ((Map<String, String>) sysBaseServiceDTO.getSys_base_service_import_param()), sysBaseServiceModel, sysBaseImportServiceModelList, sysBaseOutputServiceModelList);
                }
                break;
            case "修改":
                if (sysBaseServiceDTO.getSys_base_service_import_param() instanceof List) {
                    for (Map sysBaseServiceImportParam : ((List<Map<String, String>>) sysBaseServiceDTO.getSys_base_service_import_param())) {
                        creatSql += getUpdateCreatSql( sysBaseServiceImportParam, sysBaseServiceModel, sysBaseImportServiceModelList, sysBaseOutputServiceModelList);
                    }
                } else {
                    creatSql += getUpdateCreatSql( ((Map<String, String>) sysBaseServiceDTO.getSys_base_service_import_param()), sysBaseServiceModel, sysBaseImportServiceModelList, sysBaseOutputServiceModelList);
                }
                break;
            case "查询":
                if (sysBaseServiceDTO.getSys_base_service_import_param() instanceof List) {
                    for (Map sysBaseServiceImportParam : ((List<Map<String, String>>) sysBaseServiceDTO.getSys_base_service_import_param())) {
                        creatSql += getSelectCreatSql( sysBaseServiceImportParam, sysBaseServiceModel, sysBaseImportServiceModelList, sysBaseOutputServiceModelList);
                    }
                } else {
                    creatSql += getSelectCreatSql( ((Map<String, String>) sysBaseServiceDTO.getSys_base_service_import_param()), sysBaseServiceModel, sysBaseImportServiceModelList, sysBaseOutputServiceModelList);
                }
                break;
        }

        return sysServiceService.esecSysServiceNoSemicolon(creatSql);
    }

    public String getAddCreatSql( Map<String, String> sysBaseServiceImportParam, SysBaseServiceModel sysBaseServiceModel, List<SysBaseImportServiceModel> sysBaseImportServiceModelList, List<SysBaseOutputServiceModel> sysBaseOutputServiceModel) {
        String creatSql = "\n" +
                "INSERT INTO  " + sysBaseServiceModel.getTable_name() + "  (";
        for (SysBaseImportServiceModel sysBaseImportServiceModel : sysBaseImportServiceModelList) {
            creatSql += sysBaseImportServiceModel.getColumn_name() + ",";
        }
        creatSql = creatSql.substring(0, creatSql.length() - 1);
        creatSql += ")\n" +
                "value (";
            for (SysBaseImportServiceModel sysBaseImportServiceModel : sysBaseImportServiceModelList) {

                creatSql +=  (BeanUtil.isEmpty(sysBaseServiceImportParam.get(sysBaseImportServiceModel.getColumn_name()))?"NULL ":("\""+SqlStringUtil.escapeString(StrUtil.toString(sysBaseServiceImportParam.get(sysBaseImportServiceModel.getColumn_name()))))+"\"") + ",";
            }
            creatSql = creatSql.substring(0, creatSql.length() - 1);



        creatSql += ");;" + "";
        return creatSql;

    }

    public String getDelCreatSql( Map<String, String> sysBaseServiceImportParam, SysBaseServiceModel sysBaseServiceModel, List<SysBaseImportServiceModel> sysBaseImportServiceModelList, List<SysBaseOutputServiceModel> sysBaseOutputServiceModel) {
        String creatSql = "\n" +
                "DELETE  from " + sysBaseServiceModel.getTable_name() + "  WHERE ";

        for (SysBaseImportServiceModel sysBaseImportServiceModel : sysBaseImportServiceModelList) {
            creatSql += "  "+sysBaseImportServiceModel.getColumn_name() + " = \"" + sysBaseServiceImportParam.get(sysBaseImportServiceModel.getColumn_name()) + "\" and";
        }
        creatSql = creatSql.substring(0, creatSql.length() - 3);
        creatSql += ";;" + "";
        return creatSql;
    }


    public String getUpdateCreatSql( Map<String, String> sysBaseServiceImportParam, SysBaseServiceModel sysBaseServiceModel, List<SysBaseImportServiceModel> sysBaseImportServiceModelList, List<SysBaseOutputServiceModel> sysBaseOutputServiceModelList) {
        String creatSql = "UPDATE " + sysBaseServiceModel.getTable_name() + " SET ";

        for (SysBaseOutputServiceModel sysBaseOutputServiceModel : sysBaseOutputServiceModelList) {
            creatSql += sysBaseOutputServiceModel.getColumn_name() + " = " +(BeanUtil.isEmpty(sysBaseServiceImportParam.get(sysBaseOutputServiceModel.getColumn_name()))?"NULL ":("\""+SqlStringUtil.escapeString(StrUtil.toString(sysBaseServiceImportParam.get(sysBaseOutputServiceModel.getColumn_name())))+"\"")) + ",";
        }
        creatSql = creatSql.substring(0, creatSql.length() - 1);
        creatSql += "WHERE ";

        for (SysBaseImportServiceModel sysBaseImportServiceModel : sysBaseImportServiceModelList) {
            creatSql += "  "+sysBaseImportServiceModel.getColumn_name() + " = " +  (BeanUtil.isEmpty(sysBaseServiceImportParam.get(sysBaseImportServiceModel.getColumn_name()))?"NULL ":("\""+SqlStringUtil.escapeString(sysBaseServiceImportParam.get(sysBaseImportServiceModel.getColumn_name())))+"\"") + " and";
        }
        creatSql = creatSql.substring(0, creatSql.length() - 3);
        creatSql += ";;" + "";
        return creatSql;
    }

    public String getSelectCreatSql( Map<String, String> sysBaseServiceImportParam, SysBaseServiceModel sysBaseServiceModel, List<SysBaseImportServiceModel> sysBaseImportServiceModelList, List<SysBaseOutputServiceModel> sysBaseOutputServiceModelList) {
        String creatSql = "SELECT ";
        for (SysBaseOutputServiceModel sysBaseOutputServiceModel : sysBaseOutputServiceModelList) {
            creatSql += sysBaseOutputServiceModel.getColumn_name() + ",";
        }
        creatSql = creatSql.substring(0, creatSql.length() - 1);
        creatSql += " from " + sysBaseServiceModel.getTable_name() ;
        if (sysBaseImportServiceModelList.size()>0) {
            creatSql+=" where ";
            for (SysBaseImportServiceModel sysBaseImportServiceModel : sysBaseImportServiceModelList) {
                creatSql += "  "+ sysBaseImportServiceModel.getColumn_name() + " = \"" + sysBaseServiceImportParam.get(sysBaseImportServiceModel.getColumn_name()) + "\" and";
            }
            creatSql = creatSql.substring(0, creatSql.length() - 3);
        }
        creatSql += ";;" + "";
        return creatSql;
    }
    public  static  void  test(){

    }




}
