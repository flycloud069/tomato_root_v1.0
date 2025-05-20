package com.root.util;

import cn.hutool.core.date.DateTime;


public class DateUtil2 {

    public static Boolean isData(String Data) {
        try {
            DateTime date = cn.hutool.core.date.DateUtil.parse(Data);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
