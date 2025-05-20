package com.root.sevice.Imlp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.root.dto.SysBaseServiceDTO;
import com.root.entity.outside.SysColumnStorageModel;
import com.root.entity.outside.SysJsonParseModel;
import com.root.entity.outside.SysJsonParseRelateSysTableModel;
import com.root.sevice.SysBaseServiceService;
import com.root.sevice.SysJsonParseService;
import com.root.sevice.SysServiceService;
import com.root.sevice.SysStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysStorageServiceImpl implements SysStorageService {
    @Autowired
    SysBaseServiceService sysBaseServiceService;
    @Autowired
    SysServiceService sysServiceService;
    @Autowired
    SysJsonParseService sysJsonParseService;

    public Boolean putTable(String sys_json_parse_relate_sys_storage_service_id, String putJson) {
        SysJsonParseRelateSysTableModel sysJsonParseRelateSysTableModel = sysBaseServiceService.getBaseServiceMap(SysJsonParseRelateSysTableModel.class, new SysBaseServiceDTO("selOneSysJsonParseRelateSysTable", new HashMap<String, String>() {
            {
                put("sys_json_parse_relate_sys_storage_service_id", sys_json_parse_relate_sys_storage_service_id);
            }
        }));
        String tableName = sysJsonParseRelateSysTableModel.getTable_name();

        List<SysColumnStorageModel> sysColumnStorageModels = sysBaseServiceService.getBaseServiceList(SysColumnStorageModel.class, new SysBaseServiceDTO("GetSysTableByTableName", new HashMap<String, String>() {
            {
                put("table_name", tableName);
            }
        }));
        List<Map> list = sysJsonParseService.jsonParse(putJson,sysJsonParseRelateSysTableModel.getSys_json_parse_service_id());
        list.stream().forEach(map -> {
            String getKeySql = "select count(*) cs  from  " + tableName + " where ";
            getKeySql += sysColumnStorageModels.stream()
                    .filter(sysColumnStorageModel -> StrUtil.equals(sysColumnStorageModel.getColumn_kye(), "PRI"))
                    .map(sysColumnStorageModel -> sysColumnStorageModel.getColumn_name())
                    .reduce("", (KeySql, column_name) -> KeySql + column_name + " = " + map.get(column_name) + " and");
            getKeySql = getKeySql.substring(0, getKeySql.length() - 3);
            Map map1 = sysServiceService.esecSysService(getKeySql);
            if (StrUtil.equals(Convert.toStr(((Map)((List)map1.get("T1")).get(0)).get("cs")), "0")) {
                String inputSql = "insert into " + tableName + "(";
                inputSql += sysColumnStorageModels.stream().map(sysColumnStorageModel -> sysColumnStorageModel.getColumn_name()).reduce("", (insertSql, column_name) -> insertSql + column_name+",");
                inputSql = inputSql.substring(0, inputSql.length() - 1);
                inputSql += ") value (";
                inputSql += sysColumnStorageModels.stream().map(sysColumnStorageModel -> sysColumnStorageModel.getColumn_name()).reduce("", (insertSql, column_name) -> insertSql +"'"+ (BeanUtil.isEmpty(map.get(column_name))?"":map.get(column_name))+"',");
                inputSql = inputSql.substring(0, inputSql.length() - 1);
                inputSql += ")";
                sysServiceService.esecSysService(inputSql);
            } else {
                String updateSql = "update " + tableName + " set ";
                updateSql += sysColumnStorageModels.stream()
                        .filter(sysColumnStorageModel -> !StrUtil.equals(sysColumnStorageModel.getColumn_kye(), "PRI"))
                        .map(sysColumnStorageModel -> sysColumnStorageModel.getColumn_name()).reduce("", (KeySql, column_name) -> KeySql + column_name + " = '" +(BeanUtil.isEmpty(map.get(column_name))?"":map.get(column_name)) + "',");
                updateSql = updateSql.substring(0, updateSql.length() - 1);
                updateSql+="where ";
                updateSql += sysColumnStorageModels.stream()
                        .filter(sysColumnStorageModel -> StrUtil.equals(sysColumnStorageModel.getColumn_kye(), "PRI"))
                        .map(sysColumnStorageModel -> sysColumnStorageModel.getColumn_name()).reduce("", (KeySql, column_name) -> KeySql + column_name + " = '" +(BeanUtil.isEmpty(map.get(column_name))?"":map.get(column_name)) + "'and");
                updateSql = updateSql.substring(0, updateSql.length() - 3);

                sysServiceService.esecSysService(updateSql);
            }
        });

        return true;
    }

    public static Boolean putJsonRelateTable(String sys_json_parse_service_id, String putJson) {
        return true;
    }


}
