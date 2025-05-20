package com.root.websocket.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class RandomUtil {
    private static final String STR_HEAD = "ZY";
	//根据规则生成订单号

    /**
     * ZY+生成一个16位的唯一串
     * @return
     */
    public static String getOrderIdByUUId(String sign) {
            //最大支持1-9个集群机器部署
            int machineId = 9;
            int hashCodeV = UUID.randomUUID().toString().hashCode();
            //有可能是负数
            if(hashCodeV < 0) {
                hashCodeV = - hashCodeV;
            }
            // 0 代表前面补充0
            // 4 代表长度为4
            // d 代表参数为正数型
            return sign+machineId + String.format("%015d", hashCodeV);
    }
	
	
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
//			System.out.println(getOrderIdByUUId());
		}
//        System.out.println(getOrderNo());
	}


    /**
     * Created by cc
     * 生成订单号
     * uuid: db30cc85-3387-4845-87bc-dc390b55b7ea 前8位 + 201508171108900(yyMMddHHMMSS)
     * 即： db30cc85201508171108900
     */

//        public static  String getOrderNo(){
//            String orderNo = "" ;
//            UUID uuid = UUID.randomUUID();
//            String trandNo = String.valueOf((Math.random() * 9 + 1) * 1000000);
//            String sdf = new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date());
//            orderNo = uuid.toString().substring(0, 8);
//            orderNo = orderNo + sdf ;
//            return orderNo ;
//        }

        //生成19随机单号 纯数字
        public static String getOrderNo(){
            String orderNo = "" ;
            String tranNo = String.valueOf((Math.random() * 9 + 1) * 1000000);
            String sdf = new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date());
            orderNo = tranNo.substring(0, 4);
            orderNo = orderNo + sdf ;
            return orderNo ;
        }

}

