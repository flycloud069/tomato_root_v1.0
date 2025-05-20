package com.root.memory;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.root.dto.LoginDto;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class LoginTokenMemory {
    /**
     * 保存登录的token信息，用于验证token是否有效
     */
    private static ConcurrentHashMap<String, LoginDto> concurrentHashMap = new ConcurrentHashMap<>();
    /**
     * 保存登录的用户登录信息，用于识别用户登录信息是否有效
     */
    private static CopyOnWriteArrayList<LoginDto> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

    /**
     * 验证token是否有效
     *
     * @param token
     * @return
     */
    public static boolean verifyLoginToken(String token,String ip) {
        if (StrUtil.isBlank(token)) {
            return false;
        } else {
            if (concurrentHashMap.containsKey(token)){
                if (StrUtil.equals(getUserIdByToken(token).getLogin_ip(),ip)){
                    return true;
                };
            };
            return false;
        }
    }
    /**
     * 获取用户登录信息
     *
     * @param token
     * @return
     */
    public static LoginDto getUserIdByToken(String token) {
      return   concurrentHashMap.get(token);
    }
    /**
     * 保存登录记录
     * 1，同一个ip，同一个用户登录返回同一个token
     * 2，不同ip，同一个用户，销毁旧token，并返回新token
     * 3，新用户 ，返回新token
     *
     * @param loginDto
     * @return
     */
    public static String setLoginToken(LoginDto loginDto) {

        for (LoginDto loginDto1 : copyOnWriteArrayList) {
            if (loginDto1.equlLogin(loginDto) == 1) {
                return loginDto1.getToken();
            } else if (loginDto1.equlLogin(loginDto) == 2) {
                copyOnWriteArrayList.remove(loginDto1);
                concurrentHashMap.remove(loginDto1.getToken());
                break;
            }
        }
        String token = RandomUtil.randomUUID();
        loginDto.setToken(token);
        concurrentHashMap.put(loginDto.getToken(), loginDto);
        copyOnWriteArrayList.add(loginDto);
        return token;

    }

    /**
     * 定期清理登录数据，框架定时程序定时处理
     */
    public static void clearOverdue() {

        for (LoginDto loginDto : copyOnWriteArrayList) {
            Date login_datatime = loginDto.getLogin_datatime();
            if (DateUtil.between(login_datatime, DateUtil.date(), DateUnit.HOUR) > 3) {
                copyOnWriteArrayList.remove(loginDto);
                concurrentHashMap.remove(loginDto.getToken());
            }
        }

    }
}
