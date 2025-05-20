package com.root.memory;

import com.root.dto.LoginDto;
import com.root.dto.SysBaseServiceDTO;
import com.root.entity.outside.SysDynamicMethodModel;
import com.root.sevice.SysBaseServiceService;
import org.mdkt.compiler.InMemoryJavaCompiler;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SysClassMemory {

    /**
     * 保存系统动态类的class实例
     */
    private static ConcurrentHashMap<String, Class<?> > concurrentHashMap = new ConcurrentHashMap<>();

    public static Class<?> getSysClass(String sys_dynamic_method_value){
        return concurrentHashMap.get(sys_dynamic_method_value);
    }
    public static  void  initSysClass(List <SysDynamicMethodModel> sysDynamicMethodModels ){

        for (SysDynamicMethodModel sysDynamicMethodModel :sysDynamicMethodModels){
            try {
                System.out.println(sysDynamicMethodModel.getSys_dynamic_method_code_context());
                String sys_dynamic_method_code_context ="package sys.dynamic.method;"+ "public class "+sysDynamicMethodModel.getSys_dynamic_method_value()+" { \n" +
                        "public static  String execDynamicMethod(String i){"+sysDynamicMethodModel.getSys_dynamic_method_code_context()+" return i;}}";
                System.out.println(sys_dynamic_method_code_context);
                Class<?> helloClass = InMemoryJavaCompiler.newInstance().compile("sys.dynamic.method."+sysDynamicMethodModel.getSys_dynamic_method_value(), sys_dynamic_method_code_context);
                concurrentHashMap.put(sysDynamicMethodModel.getSys_dynamic_method_value(), helloClass);
            } catch (Exception e) {
                System.out.println(sysDynamicMethodModel.getSys_dynamic_method_value()+"----》类解析失败");
            }

        }
    }

}
