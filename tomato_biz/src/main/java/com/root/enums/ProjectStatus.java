package com.root.enums;

/**
 * 项目状态枚举
 */
public enum ProjectStatus {
    PLANNED("planned", "计划中"),
    ACTIVE("active", "进行中"),
    COMPLETED("completed", "已完成"),
    ARCHIVED("archived", "已归档");

    private final String code;
    private final String desc;

    ProjectStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ProjectStatus fromCode(String code) {
        for (ProjectStatus status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("无效的项目状态: " + code);
    }
}
