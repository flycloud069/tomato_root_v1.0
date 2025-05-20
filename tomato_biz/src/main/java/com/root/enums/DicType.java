package com.root.enums;

/**
 * @Description A
 * @Authror GONG.SHE.NG
 * @Date 2020/11/13 14:56
 */
@Deprecated
public enum DicType {
    DIC_TYPE(0, "字典类型"),
    HOS(1, "医院"),
    TITLE(2, "职称"),
    VISIT_TYPE(0, "出诊类型");


    private int code;
    private String name;


    DicType(int code, String naem) {
        this.code = code;
        this.name = naem;
    }
}
