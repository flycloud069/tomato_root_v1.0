package com.root.dto;


import lombok.Data;

import java.util.List;

@Data
public class SysServiceDeleteDTO {

    /**
     * 系统服务id
     */
    private String sysServiceId;

    /**
     * 系统服务参数键值
     */
    private List<String> sysServiceParamValues;

}
