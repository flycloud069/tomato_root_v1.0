package com.root.util;

import cn.hutool.core.util.StrUtil;
import org.mdkt.compiler.InMemoryJavaCompiler;

import java.lang.reflect.Method;

public class DynamicUtil {

 public String getStr(){
     return StrUtil.toString(1);
 }
    public static void main(String[] args) throws Exception {
        StringBuffer sourceCode = new StringBuffer();
        sourceCode.append("package com.yl;\n \n" +
                "import cn.hutool.core.util.StrUtil;");
        sourceCode.append("public class Main {\n" +
                " public static String getStr(String s){\n" +
                        "    return StrUtil.toString(1);" +
                        " }"+
                "\tpublic static void main(String[] args) {\n" +
                "\t\tSystem.out.println(\"Hello123, World\");\n" +
                "\t}\n" +
                "}");
        Class<?> helloClass = InMemoryJavaCompiler.newInstance().compile("com.yl.Main", sourceCode.toString());
        Method method2 = helloClass.getMethod("getStr",String.class);
        Object o=method2.invoke(null,"s");
        //执行 main方法
        Method method = helloClass.getMethod("main", String[].class);
        method.invoke(null, new Object[]{new String[0]});
        System.out.println(StrUtil.toString(o));
    }
}
