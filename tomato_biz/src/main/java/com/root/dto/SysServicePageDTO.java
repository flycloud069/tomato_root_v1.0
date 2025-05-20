package com.root.dto;

import cn.hutool.json.JSONObject;
import lombok.Data;

@Data
public class SysServicePageDTO {
    /**
     * 系统服务键值
     */
    private String sys_service_value;
    /**
     * 目前页数
     */
    private int newPageNumber;
    /**
     * 目前页面大小
     */
    private int newPageSize;
    /**
     * 排序字段
     */
    private String sortName;
    /**
     * 排序方式
     */
    private  Boolean sortType;
    /**
     * 过滤字段filter
     */
    private String filterName;
    /**
     * 过滤上限
     */
    private String filterUp;
    /**
     * 过滤下限
     */
    private String filterDown;

    /**
     * 查找字段
     */
    private String selectText;
    /**
     * Json字符串
     */
    private JSONObject jsonObject;

    /**
     * 服务类型
     */
    private String serviceType;

    public    SysServiceDto   getSysServiceDTO (){
       return  new SysServiceDto().getSysServiceDto(this.sys_service_value,this.jsonObject);
   }
    public    SysBaseServiceDTO   getSysBaseServiceDTO (){
        return  new SysBaseServiceDTO().getSysBaseServiceDTO(this.sys_service_value,this.jsonObject);
    }

}
