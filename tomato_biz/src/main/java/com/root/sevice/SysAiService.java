package com.root.sevice;

import com.root.dto.SysAiServiceDTO;

import java.util.List;
import java.util.Map;

public interface   SysAiService {
    public List<Map<String,String>> execAi(SysAiServiceDTO sysAiServiceDTO);
}
