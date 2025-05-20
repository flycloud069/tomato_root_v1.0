package com.root.enums;

import lombok.Getter;

/**
 * 自定义错误
 *
 * @author gaoj
 */
@Getter
public enum ServiceEnum {

    SYSTEM_ERROR(100, "系统内部错误"),

    DUPLICATE_DELETE_ERROR(101, "重复操作"),

    NOT_ERROR(102, "对象不存在"),

    REQUEST_OTHER_SYSTEM_ERROR(103, "调用第三方失败"),

    ANALYSIS_JSON_ERROR(104, "JSON解析失败"),

    UPLOAD_ERROR(105, "上传失败"),

    DOWNLOAD_ERROR(106, "下载失败"),

    MISSING_PARAMETERS(107,"缺少必要参数{}"),

    //患者建档相关异常
    INFORMATION_ERROR(10001, "患者信息不一致"),

    NOT_CARD_ERROR(10002, "卡号不存在"),

    PATIENT_NOT_EXIT_ERROR(10003,"患者信息不存在"),

    PATIENT_EXIT_ERROR(10004,"患者已建档"),

    //患者建档相关异常
    LOCK_EXIT_ERROR(20001, "存在未支付订单"),

    //不在业务时间内
    NOT_LOCK_ERROR(20003, "不符合挂号规则"),

    //订单超时取消
    ORDER_TIME_OUT_ERROR(20003, "订单超时已取消"),

    //支付异常
    CREATE_ORDER_ERROR(30001, "订单创建失败"),

    //支付异常
    ORDER_NOT_EXIT_ERROR(30002, "订单不存在"),

    //业务未开通
    TRAN_TYPE_EXIT_ERROR(30003, "业务不存在"),


    //业务未开通
    NOT_IN_SING_UP_TIME_ERROR(3000, "不在可签到时间"),


//
//    LOGIN_ID_ERROR(10021, "loginId重复"),
//
//    PWD1_ERROR(10022, "修改密码与原密码相同"),
//
//    PWD2_ERROR(10023, "用户名或密码错误"),
//
//    PWD3_ERROR(10024, "密码修改失败"),
//
//    USER_ERROR(10025, "用户不存在"),
//
//    DOC_ERROR(10026, "医生信息不存在或不对应"),
//
//    REG1_ERROR(10027, "排班已过期或即将过期"),
//
//    REG2_ERROR(10028, "号已挂完"),
//
//    SCHEDULE_ERROR(10029, "剩余数不足"),
//
//    SCHEDULE2_ERROR(10030, "排班详情不存在"),
//
//    SCHEDULE3_ERROR(10031, "排班详情对应排班信息不存在"),
//
//    SCHEDULE4_ERROR(10032, "排班时段重叠"),
//
//    INTERFACE_ERROR(10033, "无该接口权限"),
//
//    LOGIN2_ERROR(10034, "登录过期"),
//
//    STATUS_ERROR(10035, "该状态不能签到"),
//
//    STATUS2_ERROR(10036, "未签到不能叫号"),
//
//    STATUS3_ERROR(10039, "未叫号不能就诊"),
//
//    STATUS4_ERROR(10040, "未叫号不能过号"),
//
//    STATUS5_ERROR(10041, "未就诊不能结束"),
//
//    REG3_ERROR(10037, "挂号记录不存在"),
//
//    ROLE_ERROR(10038, "角色不存在"),
//
//    NO_TYPE_ERROR(10039, "号别不能为空"),
//
//    VISIT_DATE_TIME_ERROR(10040, "请在{}后签到"),
//
//    VISIT_DATE_TIME2_ERROR(10041, "已过最后签到时间{}"),
//
//    UPDATE_KEY_ERROR(10042, "无权限修改"),
//
//    BUS_TYPE1_ERROR(10043, "提交了不存在的业务类别"),
//
//    BUS_TYPE2_ERROR(10044, "业务类别不能为空"),
//
//    REQUIRED_ERROR(10045, "必填字段为空"),
//
//    SCHEDULE5_ERROR(10055, "排班不存在"),
//
//    TEMPLATE_ERROR(10055, "模板信息不存在"),
//
//    SCHEDULE_STATUS_ERROR(10056, "排班状态为非启用状态"),
//
//    REG_RULE_ERROR(10057, "挂号规则不唯一"),
//
//    REG_RULE2_ERROR(10058, "挂号规则不合法"),
//
//    REG_RULE3_ERROR(10059, "{}每天最多只能挂{}号"),
//
//    REG_RULE4_ERROR(10059, "{}{}最多只能挂{}号"),
//
//    REG_RULE5_ERROR(100591, "{}{}每天最多只能挂{}号"),
//
//    REG_RULE6_ERROR(100591, "{}{}{}最多只能挂{}号"),
//
//    AUDIT_STATUS_ERROR(10060, "输入审核状态异常"),
//
//    USER_AGENT_ERROR(10061, "请设置请求头User-Agent"),
//
//    CHAIR_ERROR(10062, "该机器ip【{ip}】未绑定诊椅"),
//
//    CHAIR2_ERROR(10063, "该诊椅无对应的诊室"),
//
//    CARD_NO_VISIT_CARD_ERROR(10064, "证件号和就诊卡不能同时为空"),
//
//    SOURCE_ERROR(10065, "id:{},name:{},token:{}渠道为开通或未授权"),
//
//    SOURCE1_ERROR(10066, "渠道信息字段{},{},{}不能为空"),
//
//    SOURCE2_ERROR(10067, "渠道为开通或未授权"),
//
//    SYS_ESA_ERROR(10068, "初始化密钥出现异常"),
//
//    SYS_ESA2_ERROR(10069, "ESA加密异常"),
//
//    SYS_ESA3_ERROR(10069, "ESA解密异常"),
//
//    REG_TIME_ERROR(10070, "已过{}最后挂号时间{}"),
//
//    CHAIR3_ERROR(10071, "诊室不能为空"),
//
//    CANCEL_ERROR(10072, "{}不能取消"),
//
//    REG4_ERROR(10073, "{}排班请在{}前挂号"),
//
//    SCHEDULE2_STATUS_ERROR(10074, "排班未审核"),
//
//    STOP_STATUS_ERROR(10075, "不是停诊排班不能删除"),
//
//    DISHONESTY_ERROR(10076, "90天内爽约三次,不能线上挂号"),
//
//    DATE_ERROR(10077, "日期格式不对"),
//
//    NO2_TYPE_ERROR(10078, "号别不存在"),
//
    UNIVERSAL_ERROR(999999, "{}"),

    INPUT_PARAMS_ERROR(888888,"入参异常");

    private int code;
    private String msg;


    ServiceEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}













































