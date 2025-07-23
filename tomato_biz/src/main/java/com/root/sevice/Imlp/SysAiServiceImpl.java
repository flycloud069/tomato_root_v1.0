package com.root.sevice.Imlp;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.root.config.SysConfig;
import com.root.dto.SysAiServiceDTO;
import com.root.dto.SysBaseServiceDTO;
import com.root.dto.SysServiceDto;
import com.root.entity.outside.*;
import com.root.sevice.SysAiService;
import com.root.sevice.SysBaseServiceService;
import com.root.sevice.SysServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SysAiServiceImpl implements SysAiService {
    @Autowired
    SysServiceService sysServiceService;
    @Autowired
    SysBaseServiceService sysBaseServiceService;

    public List<Map<String, String>> execAi(SysAiServiceDTO sysAiServiceDTO) {
        Map<String, String> sysAiServiceImportParam = (Map<String, String>) sysAiServiceDTO.getSys_ai_service_import_param();
        sys_ai_serviceModel sys_ai_serviceModel = sysBaseServiceService.getBaseServiceMap(sys_ai_serviceModel.class, new SysBaseServiceDTO("GetOneSysAiService", new HashMap<String, String>() {
            {
                put("sys_ai_service_value", sysAiServiceDTO.getSys_ai_service_value());
            }
        }));
        List<sys_ai_import_serviceModel> sys_ai_import_serviceModels = sysBaseServiceService.getBaseServiceList(sys_ai_import_serviceModel.class, new SysBaseServiceDTO("SelSysAiImportById", new HashMap<String, String>() {
            {
                put("sys_ai_service_id", sys_ai_serviceModel.getSys_ai_service_id());
            }
        }));
        List<sys_ai_outport_serviceModel> sys_ai_outport_serviceModels = sysBaseServiceService.getBaseServiceList(sys_ai_outport_serviceModel.class, new SysBaseServiceDTO("SelSysAiOutportById", new HashMap<String, String>() {
            {
                put("sys_ai_service_id", sys_ai_serviceModel.getSys_ai_service_id());
            }
        }));
        String callWord = sys_ai_serviceModel.getSys_ai_callword();
        for (sys_ai_import_serviceModel sys_ai_import_serviceModel : sys_ai_import_serviceModels) {
            callWord=callWord.replace("${" + sys_ai_import_serviceModel.getSys_ai_import_service_value() + "}", sysAiServiceImportParam.get(sys_ai_import_serviceModel.getSys_ai_import_service_value()));
        }
        String responseMessage = ExecPost(callWord,sys_ai_serviceModel.getSys_ai_temperature(),sys_ai_serviceModel.getSys_ai_top_k(),sys_ai_serviceModel.getSys_ai_max_tokens());
        List<Map<String, String>> responseMessageListMap = ConvertTableToListMap(responseMessage);
        List<Map<String, String>> returnMessageListMap=new ArrayList<Map<String, String>>();
        for (Map<String, String> responseMessageMap : responseMessageListMap) {
            Map<String, String> returnMessageMap = new HashMap<>();
            for (sys_ai_outport_serviceModel sys_ai_outport_serviceModel : sys_ai_outport_serviceModels) {
                returnMessageMap.put(sys_ai_outport_serviceModel.getSys_ai_outport_service_value(), StrUtil.toString(responseMessageMap.get(sys_ai_outport_serviceModel.getSys_ai_outport_service_xh())));
            }
            returnMessageListMap.add(returnMessageMap);
        }
        return returnMessageListMap;
    }
    ;
    private List<Map<String, String>> ConvertTableToListMap(String responseMessage) {
        Pattern pattern = Pattern.compile("\\|.*\\|", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(responseMessage);
        int i=1;
        List<Map<String, String>> listMap=new ArrayList<>();
        while (matcher.find()) {
            Pattern pattern2 = Pattern.compile("(?<=\\|)([^\\|\\n\\r]+)(?=\\|)", Pattern.CASE_INSENSITIVE);
            Matcher matcher2 = pattern2.matcher(matcher.group());
            if (i++>2) {
                Map<String,String> map=new HashMap<>();
                int j=0;
                while (matcher2.find()) {
                    map.put(StrUtil.toString(j++),matcher2.group());
                }
                listMap.add(map);
            }
        }
        return listMap;
    }

    private String ExecPost(String imput,String temperature,String top_k,String max_tokens) {
        String responseMessage = "";
        try {
            URL url = new URL(SysConfig.getconfig("AiHttpUrl"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // 设置请求头，如Content-Type
            conn.setRequestProperty("Content-Type", "application/json");
            Map<String,String> mapInput = new HashMap<>();
            mapInput.put("messages",imput);
            mapInput.put("temperature",temperature);
            mapInput.put("top_k",top_k);
            mapInput.put("max_tokens",max_tokens);

            // 写入请求体
            String input =  JSONUtil.toJsonStr(mapInput);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] inputBytes = input.getBytes();
                os.write(inputBytes);
            }

            // 获取响应码
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            // 读取响应内容
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine).append("\n");
                }

                // 打印结果
                System.out.println(response.toString());
                responseMessage=response.toString();
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseMessage;

    }
}
