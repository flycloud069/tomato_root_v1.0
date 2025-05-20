package com.root.util;

import cn.hutool.core.util.StrUtil;
import com.root.dto.ReturnMessage;

import java.util.Map;

public class SqlResultUtil {
    public static Boolean SqlResult(Map map){
       String result = (String) map.get("result");
       if (StrUtil.equals(result,"ok")){
          return true;
       }else {
       return     false;
       }

    }
}
