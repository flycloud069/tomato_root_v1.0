package com.root.util;

public class test {
    public static void yXB() {
/**
 * 神经内科
 */
        double a = 2990.36, b = 93.6231884057971, c = 85.6827712738074, d = 15.12, e = 99.67;

        int g = 0;

        double aBG = 2467.9277, bBG = 0.8500, cBG = 0.8500, dBG = 17.6752;

/**
 * 计算过程
 */
        double aZB = (aBG * 5 / 100), bZB = (bBG * 5 / 100), cZB = (cBG * 5 / 100), dZB = (dBG * 5 / 100);


        double aJG = ((int) ((a - aBG) / aZB)) * 0.3, bJG = ((int) ((bBG - b) / bZB)) * 0.15,
                cJG = ((int) (cBG - c) / cZB) * 0.15, dJG = ((int) (d - dBG) / dZB) * 0.4,
                eJG = ((int) (95 - (e * 100))) * 0.1, fJG = g * 0.5;

        double aMF = 3, bMF = 1, cMF = 1, dMF = 4, eMF = 1, fMF = 0.5;

        if (aJG > aMF) {
            aJG = aMF;
        } else if (aJG < 0) {
            aJG = 0;
        }
        if (bJG > bMF) {
            bJG = bMF;
        } else if (bJG < 0) {
            bJG = 0;
        }
        if (cJG > cMF) {
            cJG = cMF;
        } else if (cJG < 0) {
            cJG = 0;
        }
        if (dJG > dMF) {
            dJG = dMF;
        } else if (dJG < 0) {
            dJG = 0;
        }
        if (eJG > eMF) {
            eJG = eMF;
        } else if (eJG < 0) {
            eJG = 0;
        }
        if (fJG > fMF) {
            fJG = fMF;
        } else if (fJG < 0) {
            fJG = 0;
        }


        double ZZJG = aJG + bJG + cJG + dJG + eJG - fJG;
        System.out.println("aJG:    " + aJG);
        System.out.println("bJG:    " + bJG);
        System.out.println("cJG:    " + cJG);
        System.out.println("dJG:    " + dJG);
        System.out.println("eJG:    " + eJG);
        System.out.println("fJG:    " + fJG);
        System.out.println("ZZKF:    " + ZZJG);

        double ZZDF = 10 - ZZJG;
        if (ZZDF > 10) {
            ZZDF = 10;
        }

        System.out.println("ZZDF:    " + ZZDF);
        System.out.println((5.94 * 6) / 10);

    }
    public static void SB() {
        /**
         *首义呼吸内科
         */
//        double arry[]={/*五十万以上设备分值*/2,/*现场检查的分数*/85,/*年化回收率*/0.4338,/*百万医疗收支比*/0.09,/*不良事件上报数*/0,/*2023年度指标*/0.0650};

        double arry[]={/*五十万以上设备分值*/0,/*现场检查的分数*/100,/*年化回收率*/0,/*百万医疗收支比*/0.11,/*不良事件上报数*/0,/*2023年度指标*/0.10149833908766};

        double a = arry[1], b = arry[2], d = arry[3];
        int c = (int)arry[4];
        double e=arry[0];

        double dBG = arry[5];


        /**
         * 计算过程
         */

        double dBZ = dBG * 10 / 100;

        int XS =4;
        if (e==0){
            XS=6;
        }
        double aJG = (100 - a) * 4 / 100, bJG = 0,
                cJG = 1- c * 1,
                dJG = ((int) ((d - dBG) / dBZ)) * 0.3;
        if (b >= 0.5) {
            bJG = 0;
        } else if (0.5 > b && b >= 0.2) {
            bJG = 0.6;
        } else if (b < 0.2) {
            bJG = 1;
        }

        double aMF = 4, bMF = 2, cMF = 1, dMF = 3;

        if (aJG > aMF) {
            aJG = aMF;
        } else if (aJG < 0) {
            aJG = 0;
        }


        if (bJG > bMF) {
            bJG = bMF;
        } else if (aJG < 0) {
            bJG = 0;
        }

        if (cJG > cMF) {
            cJG = cMF;
        } else if (aJG < 0) {
            cJG = 0;
        }

        if (dJG > dMF) {
            dJG = dMF;
        } else if (dJG < 0) {
            dJG = 0;
        }
        double ZZJG = aJG + bJG + cJG + dJG;
if (e==0){
     ZZJG = aJG  + cJG + dJG;
}

        System.out.println("aJG:    " + aJG);
        System.out.println(" 五十万 设备收益：bJG:    " + bJG);
        System.out.println("cJG:    " + cJG);
        System.out.println("dJG:    " + dJG);
        System.out.println("ZZKF:    " + ZZJG);

        double ZZDF = 10 - ZZJG;
        if (ZZDF > 10) {
            ZZDF = 10;
        }

        System.out.println("ZZDF:    " + ZZDF);

    }
    public static void main(String[] args) {
        yXB();
        System.out.println("***************************************************");
        SB();
    }
}
