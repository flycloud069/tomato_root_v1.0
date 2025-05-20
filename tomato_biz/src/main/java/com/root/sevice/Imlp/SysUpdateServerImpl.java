package com.root.sevice.Imlp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.root.dto.SysBaseServiceDTO;
import com.root.dto.SysCreatTableDTO;
import com.root.dto.SysServiceDto;
import com.root.entity.outside.SysColumnStorageModel;
import com.root.entity.outside.SysDynamicMethodModel;
import com.root.sevice.SysBaseServiceService;
import com.root.sevice.SysServiceService;
import com.root.sevice.SysTableService;
import com.root.sevice.SysUpdateServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class SysUpdateServerImpl implements SysUpdateServer {
    @Autowired
    SysBaseServiceService sysBaseServiceService;
    @Autowired
    SysTableService sysTableService;

    @Autowired
    SysServiceService sysServiceService;
    public String OutPutUpdateString() {
        List<String> tableNames = new ArrayList<String>() {{
            add("sys_menu_vue");
            add("sys_menu");
            add("sys_table");
            add("sys_columns");
            add("sys_base_service");
            add("sys_base_output_service");
            add("sys_base_import_service");
            add("sys_ai_service");
            add("sys_ai_outport_service");
            add("sys_ai_import_service");
            add("sys_service");
            add("sys_service_param");
            add("sys_page");
            add("sys_page_column");
            add("sys_page_component");
            add("sys_page_form");
        }};
        String sql = "";
        for (String tableName : tableNames) {
            sql +="drop table  if  exists  "+tableName+";\n";
            sql +=sysTableService.creatTableSQL(new SysCreatTableDTO().getSysCreatTableDTO("tomato",tableName));
            List<Map<String,String>> mapList = sysServiceService.esecSysServiceListMap(
                    new SysServiceDto().getSysServiceDto(
                            "getTableData",
                            new JSONObject(
                                    new HashMap<String, String>() {{
                                        put("table_name", tableName);
                                    }})));

            for (Map<String,String> map:mapList) {
            List<SysColumnStorageModel> sysColumnStorageModels = sysBaseServiceService.getBaseServiceList(SysColumnStorageModel.class, new SysBaseServiceDTO("getSysColumn", new HashMap<String, String>() {
                {
                    put("table_name", tableName);
                }
            }));
            sql += "insert into "+tableName+"(";
            for (SysColumnStorageModel sysColumnStorageModel : sysColumnStorageModels) {
                sql += sysColumnStorageModel.getColumn_name() + ",";
            }
        //去掉最后一个字符
            sql=sql.substring(0, sql.length() - 1);

            sql+=")  \nselect ";
                for (SysColumnStorageModel sysColumnStorageModel : sysColumnStorageModels) {
                    sql +=  (BeanUtil.isEmpty(map.get(sysColumnStorageModel.getColumn_name())) ? "NULL" : "'"+StrUtil.toString(map.get(sysColumnStorageModel.getColumn_name())).replace("'","\\'").replace("true","1").replace("false","0")+"'") + ",";
                }
                //去掉最后一个字符
                sql=sql.substring(0, sql.length() - 1);
                sql+=";\n";
            }
        }
        return sql;
    }
    ;

    public void inPutUpdateString(String sql) {
        sysServiceService.esecSysService(sql);
    }

    ;

}
