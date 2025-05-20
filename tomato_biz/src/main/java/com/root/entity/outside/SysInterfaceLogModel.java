package com.root.entity.outside;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SysInterfaceLogModel {
    /**	系统接口日志id	*/	private	String	sys_interface_log_id	;
    /**	系统接口日志数据	*/	private	String	sys_interface_log_data	;
    /**	系统接口日志接口类型	*/	private	String	sys_interface_log_type	;
    /**	系统接口日志记录人	*/	private	String	sys_interface_log_name	;
    /**	系统接口日志记录token	*/	private	String	sys_interface_log_login_token	;
    /**	系统接口日志记录ip	*/	private	String	sys_interface_log_ip	;
    /**	系统接口日志记录url	*/	private	String	sys_interface_log_url	;
    /**	系统接口日志调用方法	*/	private	String	sys_interface_log_method	;
    /**	系统接口日志接口内容	*/	private	String	sys_interface_log_context	;
    /**	系统接口日志记录时间	*/	private	String	sys_interface_log_date	;

}
