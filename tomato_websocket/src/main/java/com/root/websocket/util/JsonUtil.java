package com.root.websocket.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.root.dto.JsonParseDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    public static void main(String[] args) {
        String jsonStr = "{\"id\":\"1\"}";
        String jsontSr1 = "{\"root\":[{\"data\":[{\"id\":\"1\"},{\"id\":\"2\"}]}]}";
        String jsonStr2 = "[{\"id\":\"123\"}]";

//        Map<String, Object> map = JSONUtil.parseObj(jsonStr);
//        Map<String, Object> map2 = JSONUtil.parseObj(jsonStr2);

        JsonParseDTO jsonParseDTO = new JsonParseDTO("id", "255", "targetId", "字符串");
//        List<List<JsonParseDTO>> jsonParseDTO1 = jsonParse(jsonStr, new ArrayList<JsonParseDTO>(Arrays.asList(jsonParseDTO)));
        JsonParseDTO jsonParseDTO2 = new JsonParseDTO("data", "", "targetdata", "列表", new ArrayList<JsonParseDTO>(Arrays.asList(jsonParseDTO)));
        List<List<JsonParseDTO>> jsonParseDTO3 = jsonParse(jsontSr1, new ArrayList<JsonParseDTO>(Arrays.asList(jsonParseDTO2)));
        System.out.println(jsonParseDTO3.toString());
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
                jsonParseDTOCopy.setTargetValue(jsonParseDTO.getFieldValue());
                jsonParseDTOCopyList.add(jsonParseDTOCopy);
            }
            jsonParseDataDTOS.add(jsonParseDTOCopyList);
        }
        return jsonParseDataDTOS;
    }
}
