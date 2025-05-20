package com.root.common;

import java.io.File;

public class SysfileCommon {
    public static  void  creatMkdir(File file) {
        File fileParent = file.getParentFile();
        if (!fileParent.getParentFile().exists()) {
            creatMkdir(fileParent);
        }
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
    }
    public static  String rootPath(){
        return System.getProperty("user.dir").toString();
    }

}
