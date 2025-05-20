package com.root.enums;

public enum ResponseStatus {
    SUCCESS(200, "操作成功！"),
    ERROR(500, "操作失败！"),
    UNAUTHORIZED(500, "尚未登录！"),
    FORBIDDEN(500, "您没有操作权限！"),
    NOT_FOUND(500, "资源不存在！"),
    LOGIN_ERROR(500, "账号或密码错误！"),
    USER_EXIST(500, "已存在的用户！"),
    INVALID_AUTHCODE(500, "手机验证码无效！"),
    INVALID_TOKEN(500, "无效的TOKEN，您没有操作权限！"),
    INVALID_ACCESS(500, "无效的请求，该请求已过期！"),
    DELETE_ERROR(500, "删除失败！");

    private Integer code;
    private String msg;

    private ResponseStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResponseStatus getResponseStatus(String message) {
        ResponseStatus[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ResponseStatus ut = var1[var3];
            if (ut.getMsg() == message) {
                return ut;
            }
        }

        return null;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
