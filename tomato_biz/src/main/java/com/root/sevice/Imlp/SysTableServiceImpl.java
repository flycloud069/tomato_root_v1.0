package com.root.sevice.Imlp;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.root.dto.SysCreatTableDTO;
import com.root.dto.SysServiceDto;
import com.root.entity.SysColumnsEntity;
import com.root.entity.SysTableEntity;
import com.root.mapper.SysTableMapper;
import com.root.sevice.SysColumnsService;
import com.root.sevice.SysServiceService;
import com.root.sevice.SysTableService;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysTableServiceImpl extends ServiceImpl<SysTableMapper, SysTableEntity> implements SysTableService {

    @Autowired
    SysColumnsService sysColumnsService;
    @Autowired
    SysServiceService sysServiceService;

    public String creatSQL(SysCreatTableDTO sysCreatTableDTO) {
        List mysqlTableList = sysServiceService.esecSysServiceList(
                new SysServiceDto().getSysServiceDto(
                        "getMysqlColumns",
                        new JSONObject(
                                new HashMap<String, String>() {{
                                    put("TABLE_SCHEMA", sysCreatTableDTO.getTable_schema());
                                    put("TABLE_NAME", sysCreatTableDTO.getTable_name());
                                }})));
        QueryWrapper<SysTableEntity> SysTablequery = new QueryWrapper();
        SysTablequery.lambda().eq(SysTableEntity::getTableSchema, sysCreatTableDTO.getTable_schema());
        SysTablequery.lambda().eq(SysTableEntity::getTableName, sysCreatTableDTO.getTable_name());
        SysTableEntity sysTableEntity = this.getOne(SysTablequery);
        List mysqlColumnsList = sysServiceService.esecSysServiceList(
                new SysServiceDto().getSysServiceDto(
                        "getMysqlColumns",
                        new JSONObject(
                                new HashMap<String, String>() {{
                                    put("TABLE_SCHEMA", sysCreatTableDTO.getTable_schema());
                                    put("TABLE_NAME", sysCreatTableDTO.getTable_name());
                                }})));
        QueryWrapper<SysColumnsEntity> SysColumnsquery = new QueryWrapper();
        SysColumnsquery.lambda().eq(SysColumnsEntity::getTableSchema, sysCreatTableDTO.getTable_schema());
        SysColumnsquery.lambda().eq(SysColumnsEntity::getTableName, sysCreatTableDTO.getTable_name());
        List<SysColumnsEntity> sysColumnsEntityList = sysColumnsService.list(SysColumnsquery);
        String creatSql = "";
        List<SysColumnsEntity> mysqlColumnsEntityList = new ArrayList();
        if (!ObjectUtil.isEmpty(mysqlColumnsList)) {
            for (Object mysqlColumnsObject : mysqlColumnsList) {
                mysqlColumnsEntityList.add(new SysColumnsEntity(mysqlColumnsObject));
            }
        }
        if (ObjectUtil.isEmpty(mysqlTableList)) {
            creatSql = creatTableSQL(sysCreatTableDTO);
        } else if (mysqlColumnsEntityList.containsAll(sysColumnsEntityList)) {
            return "";
        } else {
            creatSql += "alter table `" + sysTableEntity.getTableName() + "`";
            for (SysColumnsEntity sysColumnsEntity : sysColumnsEntityList) {
                if (!mysqlColumnsEntityList.contains(sysColumnsEntity)) {
                    creatSql += "add column";
                    creatSql += "";
                    creatSql += columSpell(sysColumnsEntity);
                }
            }
            creatSql = creatSql.substring(0, creatSql.length() - 1);
        }
        return creatSql;
    }

    public String creatTableSQL(SysCreatTableDTO sysCreatTableDTO) {
        QueryWrapper<SysTableEntity> SysTablequery = new QueryWrapper();
        SysTablequery.lambda().eq(SysTableEntity::getTableSchema, sysCreatTableDTO.getTable_schema());
        SysTablequery.lambda().eq(SysTableEntity::getTableName, sysCreatTableDTO.getTable_name());
        SysTableEntity sysTableEntity = this.getOne(SysTablequery);
        QueryWrapper<SysColumnsEntity> SysColumnsquery = new QueryWrapper();
        SysColumnsquery.lambda().eq(SysColumnsEntity::getTableSchema, sysCreatTableDTO.getTable_schema());
        SysColumnsquery.lambda().eq(SysColumnsEntity::getTableName, sysCreatTableDTO.getTable_name());
        List<SysColumnsEntity> sysColumnsEntityList = sysColumnsService.list(SysColumnsquery);
        String creatSql = "";
        creatSql += "CREATE  table if not exists `" + sysTableEntity.getTableName() + "` (\n" + "";
        for (SysColumnsEntity sysColumnsEntity : sysColumnsEntityList) {
            creatSql += columSpell(sysColumnsEntity);
        }
        if (sysColumnsEntityList.stream().anyMatch(sysColumnsEntity -> StrUtil.equals(sysColumnsEntity.getColumnKye(), "PRI"))) {
            creatSql += "PRIMARY KEY (";
            for (SysColumnsEntity sysColumnsEntity : sysColumnsEntityList.stream().filter(sysColumnsEntity -> StrUtil.equals(sysColumnsEntity.getColumnKye(), "PRI")).sorted(Comparator.comparing(SysColumnsEntity::getOrdinalPosition)).collect(Collectors.toList())) {
                creatSql += "`" + sysColumnsEntity.getColumnName() + "`,";
            }
            ;
            creatSql = creatSql.substring(0, creatSql.length() - 1);
            creatSql += "),";
        }
        creatSql = creatSql.substring(0, creatSql.length() - 1);
        creatSql += " ) \ncomment= '" + sysTableEntity.getTableComment() + "'" + " ENGINE=" + sysTableEntity.getEngine() + " DEFAULT CHARSET=utf8 COLLATE=utf8_bin ; ";
        return creatSql;
    }

    ;

    private String columSpell(SysColumnsEntity sysColumnsEntity) {
        String creatSql = " `" + sysColumnsEntity.getColumnName() + "`   ";
        if (StrUtil.equals(sysColumnsEntity.getDataType(), "decimal")) {
            creatSql += " " + sysColumnsEntity.getDataType() + "   " + " (" + sysColumnsEntity.getNumericPrecision() + "," + sysColumnsEntity.getNumericScale() + ") ";

        }else if (StrUtil.equals(sysColumnsEntity.getDataType(), "mediumtext")|| StrUtil.equals(sysColumnsEntity.getDataType(), "datetime")||StrUtil.equals(sysColumnsEntity.getDataType(), "int")||StrUtil.equals(sysColumnsEntity.getDataType(), "text")||StrUtil.equals(sysColumnsEntity.getDataType(), "int")||StrUtil.equals(sysColumnsEntity.getDataType(), "tinyint")||StrUtil.equals(sysColumnsEntity.getDataType(), "bigint")){
            creatSql += " " + sysColumnsEntity.getDataType() + "   " ;

        }else {
            creatSql += " " + sysColumnsEntity.getDataType() + "   " + " (" + sysColumnsEntity.getCharacterMaximumLength() + ") ";

        }
        if (StrUtil.equals(sysColumnsEntity.getDataType(), "varchar")) {
            creatSql += " " + "CHARACTER SET utf8 COLLATE utf8_bin  ";
        }

        if (!StrUtil.equals(sysColumnsEntity.getIsNullable(), "YES")) {
            creatSql += " not null";
        } else {
            creatSql += " null ";
        }


        creatSql += "  comment   '" + sysColumnsEntity.getColumnComment() + "'   ";
        creatSql += "\n," + "";
        return creatSql;
    }

    public String getTableModelString(String table_schema, String table_name) {
        QueryWrapper<SysTableEntity> SysTablequery = new QueryWrapper();
        SysTablequery.lambda().eq(SysTableEntity::getTableSchema, table_schema);
        SysTablequery.lambda().eq(SysTableEntity::getTableName, table_name);
        SysTableEntity sysTableEntity = this.getOne(SysTablequery);
        QueryWrapper<SysColumnsEntity> SysColumnsquery = new QueryWrapper();
        SysColumnsquery.lambda().eq(SysColumnsEntity::getTableSchema, table_schema);
        SysColumnsquery.lambda().eq(SysColumnsEntity::getTableName, table_name);
        List<SysColumnsEntity> sysColumnsEntityList = sysColumnsService.list(SysColumnsquery);
        String modelString = "package com.root.entity.outside;\n" +
                "import lombok.Data;\n" +
                "\n" +
                "@Data\n" +
                "public class " + sysTableEntity.getTableName() + "Model {\n";
        modelString += sysColumnsEntityList.stream().map(sysColumnsEntity -> "    /**\n" +
                "     * " + sysColumnsEntity.getColumnComment() + "\n" +
                "     */\n" +
                "    private String " + sysColumnsEntity.getColumnName() + ";\n").reduce("", (insertSql, text) -> insertSql + text);
        modelString += "\n}";
        return modelString;


    }
}
