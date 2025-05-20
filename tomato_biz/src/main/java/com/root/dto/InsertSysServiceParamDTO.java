package com.root.dto;

import lombok.Data;

import java.util.List;
@Data
public class InsertSysServiceParamDTO {

    /**
     * 系统服务id
     */
    String  sysServiceValue ;

    /**
     * 系统服务参数对象集合
     */
    List<SysServiceParamDto> sysServiceParamDtos;

}
