package com.root.websocket.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.root.dto.ReturnMessage;
import com.root.dto.SysColumnsDTO;
import com.root.dto.SysCreatTableDTO;
import com.root.dto.SysServiceDto;
import com.root.entity.SysColumnsEntity;
import com.root.entity.SysTableEntity;
import com.root.mapper.SysServiceMapper;
import com.root.mapper.SysTableMapper;
import com.root.sevice.SysColumnsService;
import com.root.sevice.SysServiceService;
import com.root.sevice.SysTableService;
import com.root.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/SysTable")
public class SysTableController {


    @Autowired
    SysServiceService sysServiceService;
    @Autowired
    SysTableService sysTableService;
    @Autowired
    SysColumnsService sysColumnsService;
    @Resource
    SysServiceMapper sysServcieMapper;

    @RequestMapping(value = "/getTableModelString", method = RequestMethod.POST)
    public ReturnMessage getTableModelString(@RequestBody SysCreatTableDTO sysCreatTableDTO) {
        return ResponseUtil.success(sysTableService.getTableModelString(sysCreatTableDTO.getTable_schema(), sysCreatTableDTO.getTable_name()));
    }
    @RequestMapping(value = "/synchronous", method = RequestMethod.POST)
    public ReturnMessage synchronous() {
        List mysqlTableList = sysServiceService.esecSysServiceList(new SysServiceDto().getSysServiceDto("GetMysqlTable", new JSONObject()));
        List<SysTableEntity> sysTableEntityList = sysTableService.list();
        for (Object mysqlTableObject : mysqlTableList) {
            Map tableCollation = (Map) mysqlTableObject;
            SysTableEntity mysqlTableEntity = new SysTableEntity(tableCollation

            );

            if (!sysTableEntityList.contains(mysqlTableEntity)) {
                sysTableService.saveOrUpdate(mysqlTableEntity);
            }

        }
        synchronous2();
        return ResponseUtil.success();
    }


    public void synchronous2() {
        List<SysTableEntity> sysTableEntityList = sysTableService.list();
        for (SysTableEntity sysTableEntity : sysTableEntityList) {
            List mysqlColumnsList = sysServiceService.esecSysServiceList(
                    new SysServiceDto().getSysServiceDto(
                            "getMysqlColumns",
                            new JSONObject(
                                    new HashMap<String, String>() {{
                                        put("TABLE_SCHEMA", sysTableEntity.getTableSchema());
                                        put("TABLE_NAME", sysTableEntity.getTableName());
                                    }})));
            List<SysColumnsEntity> sysColumnsEntityList = sysColumnsService.list();
            //如果存在没有列的表，跳出
            if (ObjectUtil.isEmpty(mysqlColumnsList)) {
                continue;
            }
            for (Object mysqlColumnsObject : mysqlColumnsList) {

                SysColumnsEntity sysColumnsEntity = new SysColumnsEntity(mysqlColumnsObject);
                if (!sysColumnsEntityList.contains(sysColumnsEntity)) {
                    QueryWrapper<SysColumnsEntity> query = new QueryWrapper();
                    query.lambda().eq(SysColumnsEntity::getTableSchema, sysColumnsEntity.getTableSchema());
                    query.lambda().eq(SysColumnsEntity::getTableName, sysColumnsEntity.getTableName());
                    query.lambda().eq(SysColumnsEntity::getColumnName, sysColumnsEntity.getColumnName());
                    if (0 < sysColumnsService.count(query)) {
                        sysColumnsService.update(sysColumnsEntity, query);
                    } else {
                        sysColumnsService.save(sysColumnsEntity);
                    }
                    ;
                }


            }


        }

    }

    @RequestMapping(value = "/saveBatch", method = RequestMethod.POST)
    public ReturnMessage saveBatch(@RequestBody List<SysColumnsDTO> sysColumnsDTOList) {

        SysColumnsDTO sysColumnsDTOOne = sysColumnsDTOList.get(0);
        QueryWrapper<SysColumnsEntity> delQuery = new QueryWrapper();
        delQuery.lambda().eq(SysColumnsEntity::getTableSchema, sysColumnsDTOOne.getTable_schema());
        delQuery.lambda().eq(SysColumnsEntity::getTableName, sysColumnsDTOOne.getTable_name());
        sysColumnsService.remove(delQuery);
        for (SysColumnsDTO sysColumnsDTO : sysColumnsDTOList) {
            if (!StrUtil.equals(sysColumnsDTO.getData_type(), "decimal")) {
                sysColumnsDTO.setNumeric_precision(null);
                sysColumnsDTO.setNumeric_scale(null);
            }
            SysColumnsEntity sysColumnsEntity = new SysColumnsEntity(sysColumnsDTO);


            sysColumnsService.save(sysColumnsEntity);

        }
        return ResponseUtil.success();

    }

    @RequestMapping(value = "/creatTable", method = RequestMethod.POST)
    public ReturnMessage creatTable(@RequestBody SysCreatTableDTO sysCreatTableDTO) {
        String creatSQL = sysTableService.creatSQL(sysCreatTableDTO);
        System.out.println(creatSQL);
        List list = sysServcieMapper.getSysService(creatSQL);
        return ResponseUtil.success(list);
    }
}
