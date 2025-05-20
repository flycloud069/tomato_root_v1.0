//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//  欢迎来吃屎
//
package com.root.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description 数据处理类
 * @Authror GONG.SHE.NG
 * @Date 2020/9/10 19:33
 */
public class DataWorkingUtil {

    public static void listToMap(Map<String, List<Object>> infoMap, String key, Object obj){
        List<Object> sysScheduleInfoEntities = infoMap.get(key);
        if(sysScheduleInfoEntities == null){
            sysScheduleInfoEntities = new ArrayList<>();
            infoMap.put(key,sysScheduleInfoEntities);
        }
        sysScheduleInfoEntities.add(obj);
    }

    public static List getList(JSONObject jsonObject) {
        if (jsonObject == null || jsonObject.size() == 0) {
            return new ArrayList<>();
        }

        String str = jsonObject.getStr("LIST");
        if(StrUtil.isBlank(str)){
            return new ArrayList<>();
        }

        JSONObject list = JSONUtil.parseObj(str);
        Object item = list.getObj("ITEM");
        JSONArray jsonArray = new JSONArray();
        if(item instanceof JSONObject){
            jsonArray.add(item);
        }else if(item instanceof JSONArray){
            jsonArray = (JSONArray) item;
        }
        return jsonArray;
    }
}

    