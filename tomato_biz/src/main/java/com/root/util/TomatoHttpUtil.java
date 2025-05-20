package com.root.util;

import javax.servlet.http.HttpServletRequest;

public class TomatoHttpUtil {
    /**
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
     *
     * @param request
     * @return
     * @throws
     */
    public final static String getIpAddress(HttpServletRequest request) {
        String ip = "";
        try {
// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
            ip = request.getHeader("X-Forwarded-For");
            if (ip != null && ip.length() > 0 && !"unKnown".equalsIgnoreCase(ip)) {
// 多次反向代理后会有多个ip值，第一个ip才是真实ip
                int index = ip.indexOf(",");
                if (index != -1) {
                    return ip.substring(0, index);
                } else {
                    return ip;
                }
            }
            ip = request.getHeader("X-Real-IP");
            if (ip != null && ip.length() > 0 && !"unKnown".equalsIgnoreCase(ip)) {
                return ip;
            }
            ip = request.getRemoteAddr();
        } catch (Exception e) {
// TODO: handle exception
        }
        return ip;
    }
    /**
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
     *
     * @param request
     * @return
     * @throws
     */
    public final static String getIp32Address(HttpServletRequest request) {
       String ip= getIpAddress(request);
       String ip32= getIP32(ip);
    return ip32;
    }
    public static String getIP32(String ip) {

        String[] arr = ip.split("\\.");

        String rs = "";

        for (String str : arr) {
            String s = Integer.toBinaryString(Integer.parseInt(str));

            if (s.length() < 8) {
                int diff = 8 - s.length();

                for (int i = 0; i < diff; i++) {
                    s = "0" + s;
                }
            }
            rs += s;
        }

        System.out.println(rs);
return rs;
    }

    /**
     * 获取请求token;
     *
     * @param request
     * @return
     * @throws
     */
    public final static String getToken(HttpServletRequest request) {
        String token = request.getHeader("access-token");

        return token;
    }
}
