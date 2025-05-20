package com.root.sevice;

import java.util.List;
import java.util.Map;

public interface SysJsonParseService {
    List<Map> jsonParse(String jsonStr, String  sys_json_parse_service_id);
}
