package com.root.entity.outside;

import lombok.Data;

@Data
public class SysConfigModel {
    /**	系统配置id	*/	private	String	sys_config_id	;
    /**	系统配置值	*/	private	String	sys_config_value	;
    /**	系统配置值描述	*/	private	String	sys_config_value_name	;
    /**	系统配置名称	*/	private	String	sys_config_value_value	;
}
