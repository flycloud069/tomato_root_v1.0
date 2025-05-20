package com.root.config;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.root.dto.LoginDto;
import com.root.dto.SysBaseServiceDTO;
import com.root.entity.outside.SysInterfaceLogModel;
import com.root.entity.outside.SysUserModel;
import com.root.memory.LoginTokenMemory;
import com.root.sevice.SysBaseServiceService;
import com.root.util.LogHttpServletRequestWrapper;
import com.root.util.ResponseWrapper;
import com.root.util.RsaUtil;
import com.root.util.AESUtil;
import com.root.util.TomatoHttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


/**
 * @Classname MyFilter
 * @Description 过滤器
 * @Date 2022/3/6 21:47
 * @Created ben
 */
@Component
@WebFilter(filterName = "MyFilter",
        /**
         * 通配符（*）表示对所有的web资源进行拦截
        */
        urlPatterns = {"*", "/SysService/*", "/login*"}
)
public class MyFilter implements Filter {
    @Autowired
    SysBaseServiceService sysBaseServiceService;

    /**
     * 过滤器初始化
     * explain:在容器中创建当前过滤器的时候自动调用
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("初始化过滤器!");
    }

    /**
     * 过滤器过滤操作
     * explain:过滤的具体操作
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws  IOException, ServletException {

        UUID uuid = UUID.randomUUID();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String method = request.getMethod();
        String url = request.getRequestURI();
        String context = "";
        String date = "";
        String type = "请求接口入参";
        String token = "";
        String ip = "";
        String user = "";
        System.out.println("请求地址:" + request.getRequestURI());
        System.out.println(request.getMethod());
        System.out.println(StrUtil.equals(request.getHeader("swagger"), "true") ? "swagger请求" : "普通请求");
        if (StrUtil.equals(request.getMethod(), "POST") && !StrUtil.equals(request.getHeader("swagger"), "true")) {
            LogHttpServletRequestWrapper wrapper1 = new LogHttpServletRequestWrapper((HttpServletRequest) servletRequest);
            context = wrapper1.getData();
            token = request.getHeader("access-token");
            if (!BeanUtil.isEmpty(token)) {
                LoginDto loginDto = LoginTokenMemory.getUserIdByToken(token);
                if (!BeanUtil.isEmpty(loginDto)) {
                    user = loginDto.getSys_user_id();
                    SysUserModel selSyseserName =new SysUserModel();
                    selSyseserName.setSys_user_id(user);
                    SysUserModel sysUserModel= sysBaseServiceService.getBaseServiceMap(SysUserModel.class, new SysBaseServiceDTO("selSyseserName", BeanUtil.beanToMap(selSyseserName)));
                    user=sysUserModel.getUsername();
                }
            }
            ip = TomatoHttpUtil.getIp32Address(request);
            System.out.println(wrapper1.getData());
            if (!StrUtil.equals(Convert.toStr(JSONUtil.parseObj(wrapper1.getData()).get("data")), "false") && !StrUtil.equals(Convert.toStr(JSONUtil.parseObj(wrapper1.getData()).get("sign")), "false")) {
                String aes = (String) JSONUtil.parseObj(wrapper1.getData()).get("aes");
                aes = RsaUtil.decrypt(aes);

                String data = (String) JSONUtil.parseObj(wrapper1.getData()).get("data");
                data = AESUtil.decryptAES(data,aes);

                String signStr = (String) JSONUtil.parseObj(wrapper1.getData()).get("sign");
                Boolean sing = RsaUtil.doCheck(data, signStr);
                if (sing) {
                    wrapper1.setData(data);
                }
            }
            date = wrapper1.getData();
            System.out.println(wrapper1.getData());
            //响应处理  包装响应对象 res 并缓存响应数据
            ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) servletResponse);
            SysInterfaceLogModel sysInterfaceLogModel = new SysInterfaceLogModel(uuid.toString(), date, type, user, token, ip, url, method, context, DateUtil.now());
            sysBaseServiceService.getBaseServiceList(SysInterfaceLogModel.class, new SysBaseServiceDTO("AddSysInterfaceLog", BeanUtil.beanToMap(sysInterfaceLogModel)));
            filterChain.doFilter(wrapper1, responseWrapper);

            type = "请求接口出参";
            try {
                byte[] resData = responseWrapper.getResponseData();
                String str = new String(resData);
                System.out.println(str);
                date = str;
                String sign = RsaUtil.sign(str);
                long l = System.currentTimeMillis();

                System.out.printf("开始加密时间：%d", l);
                String aesKey =AESUtil.generateAESKey();
                String aes = RsaUtil.encrypt(aesKey);
                String data = AESUtil.encryptAES(str,aesKey);
                long l1 = System.currentTimeMillis();
                System.out.printf("结束加密时间：%d", l1);
                System.out.printf("时间差：%d", l1 - l);

                PrintWriter out = servletResponse.getWriter();
                Map map = new HashMap();
                map.put("aes", aes);
                map.put("data", data);
                map.put("sign", sign);
                System.out.println(JSONUtil.parse(map).toStringPretty());
                context = JSONUtil.parse(map).toStringPretty();
                out.print(JSONUtil.parse(map).toStringPretty());
                out.flush();
                out.close();
                SysInterfaceLogModel sysInterfaceLogModel2 = new SysInterfaceLogModel(uuid.toString(), date, type, user, token, ip, url, method, context, DateUtil.now());
                sysBaseServiceService.getBaseServiceList(SysInterfaceLogModel.class, new SysBaseServiceDTO("AddSysInterfaceLog", BeanUtil.beanToMap(sysInterfaceLogModel2)));

            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (StrUtil.equals(request.getMethod(), "Get") && !StrUtil.equals(request.getHeader("swagger"), "true")){
            LoginDto loginDto = LoginTokenMemory.getUserIdByToken(request.getParameter("access-token"));
            if (!BeanUtil.isEmpty(loginDto)) {
                filterChain.doFilter(servletRequest, servletResponse);
            }


        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        // 请求url中包含/hello||/online,继续执行
//        if (request.getRequestURI().contains("/hello")
//                || request.getRequestURI().contains("/online") ){
        // 交给下一个过滤器或servlet处理

//        }else {
        // 请求url不包含/hello||/online,重定向到/online接口
        //    servletRequest.sendRedirect("/entry/online");
//      }
    }

    /**
     * 过滤器销毁
     * explain:在容器中销毁当前过滤器的时候自动调用
     */
    @Override
    public void destroy() {
        System.out.println("销毁过滤器!");
    }
}