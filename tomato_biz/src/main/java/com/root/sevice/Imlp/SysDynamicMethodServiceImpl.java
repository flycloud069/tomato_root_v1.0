package com.root.sevice.Imlp;

import com.root.dto.SysBaseServiceDTO;
import com.root.entity.outside.SysDynamicMethodModel;
import com.root.entity.outside.SysJsonParseRelateSysTableModel;
import com.root.memory.SysClassMemory;
import com.root.sevice.SysBaseServiceService;
import com.root.sevice.SysDynamicMethodService;
import org.mdkt.compiler.InMemoryJavaCompiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.HashMap;

@Service
public class SysDynamicMethodServiceImpl implements SysDynamicMethodService {
    @Autowired
    SysBaseServiceService sysBaseServiceService;

    public String execDynamicMethod(String sys_dynamic_method_id, String inputString) {
        SysDynamicMethodModel sysDynamicMethodModel = sysBaseServiceService.getBaseServiceMap(SysDynamicMethodModel.class, new SysBaseServiceDTO("GetOneSysDynamicMethod", new HashMap<String, String>() {
            {
                put("sys_dynamic_method_id", sys_dynamic_method_id);
            }
        }));
        try {
            Class<?> helloClass = SysClassMemory.getSysClass(sysDynamicMethodModel.getSys_dynamic_method_value());
            Method method2 = helloClass.getMethod("execDynamicMethod",String.class);
            String  s=(String) method2.invoke(null,inputString);
            return s;
        } catch (Exception e) {
            return  e.getMessage();
        }
    }
}

