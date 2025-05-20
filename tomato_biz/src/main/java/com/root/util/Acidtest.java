package com.root.util;

import java.io.*;
import java.util.Scanner;


public class Acidtest {

    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);
        while (sca.hasNext()) {
            int n = sca.nextInt();
            Date[] s = new Date[n];
            for (int i = 0; i < s.length; i++) {
                s[i] = new Date(sca.next(), sca.nextInt(), sca.next());
                /**添加人员信息*/
                file(String.valueOf(s[i]));
                /**下面添加了换行*/
                file("\r\n");
            }
            /**控制台打印和追加，同样的内容，这里不用换行*/
            String str = "阳性人名单:";
            System.out.print(str);
            file(str);
            for (int i = 0; i < s.length; i++) {
                if (s[i].consequence.equals("阳性")) {
                    /**追加和控制台同样的内容*/
                    str = s[i].name + " ";
                    System.out.print(str);
                    file(str);
                }
            }
        }
    }
/**这个函数独立解决文件追加问题，入参设为 字符串 就行*/
    public static void file(String s) {
        try {
            File file = new File("D:\\ooooo.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fos = new FileWriter(file, true);
            try {
                /**FileWriter的write方法就能很好的实现追加*/
                fos.write(s);
                fos.flush();
                /**这里为了捕获文件流追加异常*/
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } finally {
                /** 输入输出流需要用 finally关闭*/
                fos.close();
            }
            /**这里为了捕获创建文件异常*/
        } catch (IOException e2) {
            e2.printStackTrace();
        }

    }
}

class Date {
    public String name;//姓名
    public int date;//采样日期
    public String consequence;//检测结果

    //构造器
    public Date(String name, int date, String consequence) {
        this.name = name;
        this.date = date;
        this.consequence = consequence;
    }

    //打印信息
    @Override
    public String toString() {
        return name + " " + date + " " + consequence;
    }
}
