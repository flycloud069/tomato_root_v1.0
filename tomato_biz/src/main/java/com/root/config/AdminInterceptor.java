package com.root.config;

import cn.hutool.core.util.StrUtil;
import com.root.memory.LoginTokenMemory;
import com.root.util.TomatoHttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.handler.HandlerMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Objects;


@Configuration
public class AdminInterceptor implements HandlerInterceptor {
    /**
     * Redis的API
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {

        // 从 http 请求头中取出签名
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        String token = httpServletRequest.getHeader("access-token");

         String Gettoken = httpServletRequest.getParameter("access-token");
//        String decKey = AesEncodeUtil.decrypt(sign);
        String decKey = null;

        String ip=TomatoHttpUtil.getIpAddress(httpServletRequest);
//        if (Objects.nonNull(decKey)) {
//        System.out.println("鉴权");
        if (StrUtil.equals(httpServletRequest.getHeader("swagger"), "true") || LoginTokenMemory.verifyLoginToken(token,ip)) {
            return true;
        } else {
            try (PrintWriter out = httpServletResponse.getWriter()) {
                final String message = "接口鉴权失败，请在前端系统添加鉴权值";
                String responseJson = "{\"message\":\"" +  message +  "\",\"success\": false,\"code\": 403}";
                out.print(responseJson);

            } catch (IOException e) {

            }
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }

}
