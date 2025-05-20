package com.root.sevice.Imlp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.root.dto.SysServiceDto;
import com.root.entity.SysServiceEntity;
import com.root.entity.SysServiceParamEntity;
import com.root.mapper.SysServiceMapper;
import com.root.mapper.SysServiceParamMapper;
import com.root.sevice.SysServiceService;
import com.root.util.ResponseUtil;
import com.root.util.SqlStringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class SysServiceServiceImpl extends ServiceImpl<SysServiceMapper, SysServiceEntity> implements SysServiceService {
    @Resource
    SysServiceMapper sysServiceMapper;
    @Resource
    SysServiceParamMapper sysServiceParamMapper;

    /*调用系统服务返回list */
    public String execSql(SysServiceDto sysServiceDto) {
        List<SysServiceEntity> list = sysServiceMapper.selSysServiceByVlue(sysServiceDto.getSys_service_value());
        List<SysServiceParamEntity> sysServiceParamEntityList = sysServiceParamMapper.SelSysServiceParamBySysServiceId(list.get(0).getSysServiceId());
        String sql = list.get(0).getSysServiceSql();
        JSONObject jsonObject1 = sysServiceDto.getJsonObject();

        for (SysServiceParamEntity sysServiceParamEntity : sysServiceParamEntityList) {
            String value = jsonObject1.getStr(sysServiceParamEntity.getSysServiceParamValue());
            value = StrUtil.isBlank(value) ? "" : value;
            System.out.println(sysServiceParamEntity.getSysServiceParamValue());
            sql = StrUtil.replace(sql, "#{" + sysServiceParamEntity.getSysServiceParamValue() + "}", SqlStringUtil.escapeString(value) );
        }

        return sql;
    }



    public List<Map<String,String>> esecSysServiceListMap(SysServiceDto sysServiceDto){
       return  (List<Map<String,String>>) esecSysServiceList(sysServiceDto);
    };
    /*调用系统服务返回ist */
    public List esecSysServiceList(SysServiceDto sysServiceDto) {
        Map map = esecSysService(sysServiceDto);
        return (List) map.get("T1");
    }

    /*调用系统服务返回List对象 */
    public <T> List<T> esecSysServiceModelList(Class<T> clazz, SysServiceDto sysServiceDto) {
        List<T> ts = new ArrayList<T>();
        Map map = esecSysService(sysServiceDto);
        if (((List) map.get("T1")) instanceof List) {
            ((List) map.get("T1")).stream().forEach(o -> ts.add(BeanUtil.toBean(o, clazz)));
        }
        return ts;
    }

    /*调用系统服务返回对象 */
    public <T> T esecSysServiceModel(Class<T> clazz, SysServiceDto sysServiceDto) {
        Map map = esecSysService(sysServiceDto);
        return BeanUtil.toBean(((List) map.get("T1")).get(0), clazz);
    }

    /*调用系统服务返回Map */
    public Map esecSysService(SysServiceDto sysServiceDto) {
        String sql = execSql(sysServiceDto);
        System.out.println(sql);
        String sqls[] = sql.split(";");
        Map map = new HashMap();
        int i = 0;
        for (String esecsql : sqls) {
            List list = new ArrayList();
            try {
                list = sysServiceMapper.getSysService(esecsql);

                if (!ObjectUtil.isEmpty(list)) {
                    i++;
                    map.put("T" + i, list);
                }
            } catch (Exception e) {
                break;
            }

        }
        return map;
    }

    /*调用系统服务返回Map */
    public Map esecSysService(String sql) {
        System.out.println(sql);
        String sqls[] = sql.split(";");
        Map map = new HashMap();
        int i = 0;
        for (String esecsql : sqls) {
            List list = new ArrayList();
            try {
                list = sysServiceMapper.getSysService(esecsql);

                if (!ObjectUtil.isEmpty(list)) {
                    i++;
                    map.put("T" + i, list);
                }
            } catch (Exception e) {
                break;
            }

        }
        return map;
    }
    /*调用系统服务返回Map */
    public Map esecSysServiceNoSemicolon(String sql) {
        System.out.println(sql);
        String sqls[] = sql.split(";;");
        Map map = new HashMap();
        int i = 0;
        for (String esecsql : sqls) {
            List list = new ArrayList();
            try {
                list = sysServiceMapper.getSysService(esecsql);

                if (!ObjectUtil.isEmpty(list)) {
                    i++;
                    map.put("T" + i, list);
                }
            } catch (Exception e) {
                break;
            }

        }
        return map;
    }
    public List esecSysServiceList(String sql) {
        System.out.println(sql);
        String sqls[] = sql.split(";");
        List list = new ArrayList();
        for (String esecsql : sqls) {
            try {
                list.add(sysServiceMapper.getSysService(esecsql));
            } catch (Exception e) {
                break;
            }
        }
        return list;
    }
}
