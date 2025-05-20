package com.root.memory;

import cn.hutool.core.util.StrUtil;
import com.root.dto.LoginDto;
import com.root.dto.ScheduledDTO;
import com.root.dto.SysBaseServiceDTO;
import com.root.entity.outside.SysExtractServiceModel;
import com.root.sevice.SysBaseServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class ScheduledMemory {
    @Autowired
    SysBaseServiceService sysBaseServiceService;
    /**
     * 计划类集合
     */
    private static CopyOnWriteArrayList<ScheduledDTO> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

    public static Boolean startScheduled(LoginDto loginDto, String cron, String sys_extracts_service_id, String inputString) {
        ScheduledDTO scheduledDTO = new ScheduledDTO(loginDto, cron, sys_extracts_service_id, inputString);
        copyOnWriteArrayList.add(scheduledDTO);
        return true;
    }

    public static Boolean cancel(String sys_extracts_service_id) {
        copyOnWriteArrayList.stream().filter(scheduledDTO -> StrUtil.equals(scheduledDTO.getSys_extracts_service_id(), sys_extracts_service_id)).forEach(
                scheduledDTO -> {scheduledDTO.cancel();
                copyOnWriteArrayList.remove(scheduledDTO);}
        );
        return true;
    }

    public static  List<Map<String,String>> getScheduledList() {
        List<Map<String,String>> sys_extracts_service_idsMap = copyOnWriteArrayList.stream().map(scheduledDTO -> new HashMap<String,String>(){{put("sys_extracts_service_id",scheduledDTO.getSys_extracts_service_id());}}).collect(Collectors.toList());
        return sys_extracts_service_idsMap;
    }

}
