package com.root.websocket.util;


class TomatoException extends Exception {
    public TomatoException() {
        super();
    }

    public TomatoException(String str) {
        super(str);
    }
}

public class SplitUtil {
    public static void split(String s) {
        String lines[] = s.split("\\r?\\n");
        for (String line : lines) {
            String values[] = line.split("\\s+");
/**
 * 捕获自定义的异常
 */
            try {
                /**
                 * 开始捕获异常
                 */
                try {
                    /**
                     * 计算值
                     */

                    int b = Integer.parseInt(values[0]) + Integer.parseInt(values[1]);
                    System.out.println(b);
                    /**
                     * catch  NumberFormatException  捕获数值转换的异常，并处理，捕获结束后继续下一次循环
                     */

                } catch (NumberFormatException e) {
                    System.out.println("有非数据字符，不可相加！");
                    throw new TomatoException();
                }
            } catch (TomatoException e) {
                System.out.println("第二次捕获异常，捕获第一次捕获后抛出的自定义异常");
            }

        }
    }

    public static void main(String[] args) {
        String s = "8888 ab85\n" +
                "8888 100\n" +
                "5900 900";
        split(s);
    }
}
