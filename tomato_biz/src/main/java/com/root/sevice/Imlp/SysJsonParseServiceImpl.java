package com.root.sevice.Imlp;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.root.dto.JsonParseDTO;
import com.root.dto.SysBaseServiceDTO;
import com.root.entity.outside.SysJsonParseModel;
import com.root.sevice.SysBaseServiceService;
import com.root.sevice.SysJsonParseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysJsonParseServiceImpl implements SysJsonParseService {
    @Autowired
    SysBaseServiceService sysBaseServiceService;

    public List<Map> jsonParse(String jsonStr, String sys_json_parse_service_id) {
        List<SysJsonParseModel> sysJsonParseModels = sysBaseServiceService.getBaseServiceList(SysJsonParseModel.class, new SysBaseServiceDTO("SelSysJsonParse", new HashMap<String, String>() {
            {
                put("sys_json_parse_service_id", sys_json_parse_service_id);
            }
        }));
        List<JsonParseDTO> jsonParseDTOS = sysJsonParseModels.stream().map(sysJsonParseModel -> {
            return new JsonParseDTO(sysJsonParseModel);
        }).collect(Collectors.toList());
        List<List<JsonParseDTO>> jsonParseDataDTOS = jsonParse(jsonStr, jsonParseDTOS);
        return jsonParseDataDTOS.stream().map(jsonParseDTOS1 -> {
            Map map =new HashMap();
             jsonParseDTOS1.stream().forEach(jsonParseDTO -> new HashMap<String, String>() {{
                map.put(jsonParseDTO.getTargetFieldName(), jsonParseDTO.getTargetValue());
            }});
             return  map;
        }).collect(Collectors.toList());
    }

    public static List<List<JsonParseDTO>> jsonParse(String jsonStr, List<JsonParseDTO> jsonParseDTOS) {
        Map<String, Object> map = JSONUtil.parseObj(jsonStr);
        List<Map> dataList = (List<Map>) (map.get("root"));
        List<List<JsonParseDTO>> jsonParseDataDTOS = new ArrayList<List<JsonParseDTO>>();
        for (Map dataMap : dataList) {
            List<JsonParseDTO> jsonParseDTOCopyList = new ArrayList<JsonParseDTO>();
            for (JsonParseDTO jsonParseDTO : jsonParseDTOS) {
                JsonParseDTO jsonParseDTOCopy = new JsonParseDTO();
                BeanUtils.copyProperties(jsonParseDTO, jsonParseDTOCopy);
                if (StrUtil.equals(jsonParseDTO.getTargetFieldType(), "列表")) {
                    jsonParseDTOCopy.setSonList(jsonParse("{ \"root\":" + Convert.toStr(dataMap.get(jsonParseDTO.getFieldName())) + "}", jsonParseDTO.getSonParseS()));
                    jsonParseDTOCopyList.add(jsonParseDTOCopy);
                    continue;
                }
                jsonParseDTOCopy.setFieldValue(Convert.toStr(dataMap.get(jsonParseDTO.getFieldName())));
                jsonParseDTOCopy.setTargetValue(jsonParseDTOCopy.getFieldValue());
                jsonParseDTOCopyList.add(jsonParseDTOCopy);
            }
            jsonParseDataDTOS.add(jsonParseDTOCopyList);
        }
        return jsonParseDataDTOS;
    }
}
