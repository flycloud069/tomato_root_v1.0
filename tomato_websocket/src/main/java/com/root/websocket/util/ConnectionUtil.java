package com.root.websocket.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;

/**
 * @author ：fuyunxiang
 * @date ：Created in 2022年8月24日,0024 16:12
 */

public class ConnectionUtil {

//    private static double w1 = 0;
//    private static double w2 = 0;
//    private static double w3 = 0;
//    private static double b = 0;


    public static int net(int get, double w1, double w2, double w3, double b) {
        double sum1 = 0;
        double sum2 = 0;
        double sum = 0;
//        for (String s : get) {
//            sum = sum + Convert.toInt(s);
        sum1 = get * w1 + b;
        sum2 = get * w2 + b;
//        }
        sum = (sum1 + sum2) * w3 + b;
        sum = sigmoid(sum);
        sum = (sum * 10) % 9;
        return (int) sum + 1;


    }


    public static double sigmoid(double src) {
        return 1.0 / (1 + Math.exp(-src));
    }

    public static void main(String[] args) {
        String[] get = {"1", "2", "0", "0", "0", "0", "0", "1","0"};
        int sum = 0;
        int j = 0;
        for (int i = 0; i < 9; i++) {
            if (StrUtil.equals(get[i], "1")) {
                sum = i + sum;
                j++;
            } else if (StrUtil.equals(get[i], "2")) {


                sum = i * 100 + sum;
                j++;
            }

        }
        sum = j * 10000 + sum;

        System.out.println(net(sum, 0, 0, 0, 2));
    }
}
