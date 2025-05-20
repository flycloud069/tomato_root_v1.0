//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//  欢迎来吃屎
//
package com.root.util;

import cn.hutool.core.date.DateUtil;
import com.root.entity.SysBaseEntity;
import lombok.experimental.UtilityClass;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * @Description 工具
 * @Authror GONG.SHE.NG
 * @Date 2020/8/7 14:22
 */
@UtilityClass
public class CreateCodeUtil {

    /*
     * @Author GONG.SHE.NG
     * @param sysBaseEntity :
     * @return 自动生成代码 controller service serviceImpl mapper
     * @throws
     * @Date 2020/8/7 10:02
     */
    public void outFile(String url, String javaTemplate, String mapping, String describe, String name, String type) throws Exception {
        String controllerJava = javaTemplate;
        String nameMin = name.substring(0, 1).toLowerCase() + name.substring(1);
        controllerJava = controllerJava.replace(NAME_MIN_TEMPLATE, nameMin).replace(MAPPING_TEMPLATE, mapping).replace(DESCRIBE_TEMPLATE, describe).replace(NAME_TEMPLATE, name);

        File controllerJavaFile = new File(url);
        if (!controllerJavaFile.exists()) {
            controllerJavaFile.createNewFile();
        }
        BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(controllerJavaFile));
        bufOut.write(controllerJava.getBytes());
        bufOut.flush();
        bufOut.close();
        System.out.println("【" + name + type + file_end + "】生成完成");
    }

    public String getMapping(String name) throws Exception {
        String mapping = "/";

        if (name.length() == 0) {
            throw new Exception("类名不合法");
        } else if (name.length() == 1) {
            mapping = name;
        } else {
            int beginIndex = 0;
            for (int i = 1; i < name.length(); i++) {
                if (Character.isUpperCase(name.charAt(i))) {
                    mapping += name.substring(beginIndex, i).toLowerCase() + "/";
                    beginIndex = i;
                } else if (i == name.length() - 1) {
                    mapping += name.substring(beginIndex).toLowerCase() + "/";
                }
            }
        }
        return mapping;
    }

    private static final String filePath = System.getProperty("user.dir") + "/";
    private static final String projectName = "/src/main/java/com/zyyx/zyyxcloud/ali/pay/";
    private static final String baseUrlStr = filePath + projectName;

    private static final String controller = "Controller";
    private static final String service = "Service";
    private static final String service_impl = "ServiceImpl";
    private static final String file_end = ".java";
    private static final String mapper = "Mapper";
    private static final String entity = "entity";
    private static final String bak_str = "";

    private static final String MAPPING_TEMPLATE = "MAPPING";
    private static final String DESCRIBE_TEMPLATE = "DESCRIBE";
    private static final String NAME_TEMPLATE = "NAME";
    private static final String NAME_MIN_TEMPLATE = "NAME_MIN";


    private static final String controllerJavaTemplate = "//\n" +
            "// Source code recreated from a .class file by IntelliJ IDEA\n" +
            "// (powered by Fernflower decompiler)\n" +
            "//  欢迎来吃屎\n" +
            "//\n" +
            "package com.zyyx.zyyxcloud.ali.pay.controller;\n" +
            "\n" +

            "import com.zyyx.zyyxcloud.core.entity.ReturnMessage;\n" +
            "import com.zyyx.zyyxcloud.core.util.ResponseUtil;\n" +
            "import org.springframework.web.bind.annotation.RequestMapping;\n" +
            "import org.springframework.web.bind.annotation.RestController;\n" +
            "import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;\n" +
            "import com.baomidou.mybatisplus.extension.plugins.pagination.Page;\n" +
            "import com.zyyx.zyyxcloud.ali.pay.utils.SysStaticVariable;\n" + //TODO
            "import com.zyyx.zyyxcloud.ali.pay.entity.NAME" + entity + ";\n" + //TODO
            "import com.zyyx.zyyxcloud.ali.pay.service.NAME" + service + ";\n" + //TODO
            "import io.swagger.annotations.Api;\n" +
            "import io.swagger.annotations.ApiOperation;\n" +
            "import lombok.extern.log4j.Log4j2;\n" +
            "import org.springframework.beans.factory.annotation.Autowired;\n" +
            "import org.springframework.web.bind.annotation.*;" +
            "\n" +
            "/**\n" +
            " * @Description DESCRIBE\n" +
            " * @Authror GONG.SHE.NG\n" +
            " * @Date " + DateUtil.formatDateTime(new Date()) + "\n" +
            " */\n" +
            "@Log4j2\n" +
            "@RestController\n" +
            "@RequestMapping(\"MAPPING\")\n" +
            "@Api(value = \"MAPPING\", tags = \"DESCRIBE管理\")\n" +
            "public class NAME" + controller + " {\n" +
            "    @Autowired\n" +
            "    NAME" + service + " NAME_MIN" + service + ";\n" +
            "\n" +
            "    @GetMapping(\"page\")\n" +
            "    @ApiOperation(value = \"page\", notes = \"DESCRIBE分页\")\n" +
            "    public ReturnMessage page(Page page, NAME" + entity + " NAME_MIN" + entity + ") {\n" +
            "        return ResponseUtil.success(NAME_MIN" + service + ".page(page,new QueryWrapper(NAME_MIN" + entity + ")));\n" +
            "    }\n" +
            "\n" +
            "\n" +
            "    @PostMapping(value = \"byId\")\n" +
            "    @ApiOperation(value = \"byId\", notes = \"DESCRIBE详情\")\n" +
            "    public ReturnMessage byId(String id){\n" +
            "        return ResponseUtil.success(NAME_MIN" + service + ".getById(id));\n" +
            "    };" +
            "\n" +
            "\n" +
            "    @GetMapping(\"list\")\n" +
            "    @ApiOperation(value = \"list\", notes = \"DESCRIBE查询\")\n" +
            "    public ReturnMessage list(NAME" + entity + " NAME_MIN" + entity + "){\n" +
            "        return ResponseUtil.success(NAME_MIN" + service + ".list(new QueryWrapper(NAME_MIN" + entity + ")));\n" +
            "    };\n" +
            "\n" +
            "\n" +
            "    @PostMapping(\"add\")\n" +
            "    @ApiOperation(value = \"add\", notes = \"DESCRIBE添加\")\n" +
            "    public ReturnMessage add(@RequestBody  NAME" + entity + " NAME_MIN" + entity + "){\n" +
            "        return ResponseUtil.success(NAME_MIN" + service + ".save(NAME_MIN" + entity + "));\n" +
            "    };\n" +
            "\n" +
            "\n" +
            "    @PostMapping(\"update\")\n" +
            "    @ApiOperation(value = \"update\", notes = \"DESCRIBE修改\")\n" +
            "    public ReturnMessage update(@RequestBody  NAME" + entity + " NAME_MIN" + entity + "){\n" +
            "        return ResponseUtil.success(NAME_MIN" + service + ".updateById(NAME_MIN" + entity + "));\n" +
            "    };\n" +
            "\n" +
            "\n" +
            "    @DeleteMapping(\"del\")\n" +
            "    @ApiOperation(value = \"update\", notes = \"DESCRIBE逻辑删除\")\n" +
            "    public ReturnMessage update(String id){\n" +
            "        QueryWrapper<NAME" + entity + "> query = new QueryWrapper();\n" +
            "        query.lambda().eq(NAME" + entity + "::getId,id);\n" +
            "        NAME" + entity + " NAME_MIN" + entity + " = new NAME" + entity + "();\n" +
            "        NAME_MIN" + entity + ".setDelFlg(SysStaticVariable.STR_1);\n" +
            "        return ResponseUtil.success(NAME_MIN" + service + ".update(NAME_MIN" + entity + ",query));\n" +
            "    };\n" +
            "\n" +
            "\n" +
            "    @DeleteMapping(\"remove\")\n" +
            "    @ApiOperation(value = \"remove\", notes = \"DESCRIBE物理删除\")\n" +
            "    public ReturnMessage remove(String id){\n" +
            "        return ResponseUtil.success(NAME_MIN" + service + ".removeById(id));\n" +
            "    };" +
            "}";

    private static final String serviceJavaTemplate = "//\n" +
            "// Source code recreated from a .class file by IntelliJ IDEA\n" +
            "// (powered by Fernflower decompiler)\n" +
            "//  欢迎来吃屎\n" +
            "//\n" +
            "package com.zyyx.zyyxcloud.ali.pay.service;\n" +
            "\n" +
            "import com.baomidou.mybatisplus.extension.service.IService;\n" +
            "import com.zyyx.zyyxcloud.ali.pay.entity.NAME" + entity + ";\n" + //TODO
            "\n" +
            "/**\n" +
            " * @Description DESCRIBE管理\n" +
            " * @Authror GONG.SHE.NG\n" +
            " * @Date " + DateUtil.formatDateTime(new Date()) + "\n" +
            " */\n" +
            "public interface NAME" + service + " extends IService<NAME" + entity + "> {\n" +
            "}";

    private static final String serviceImplJavaTemplate = "//\n" +
            "// Source code recreated from a .class file by IntelliJ IDEA\n" +
            "// (powered by Fernflower decompiler)\n" +
            "//  欢迎来吃屎\n" +
            "//\n" +
            "package com.zyyx.zyyxcloud.ali.pay.service.impl;\n" +
            "\n" +
            "import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;\n" +
            "import com.zyyx.zyyxcloud.ali.pay.entity.NAME" + entity + ";\n" + //TODO
            "import com.zyyx.zyyxcloud.ali.pay.mapper.NAME" + mapper + ";\n" +  //TODO
            "import com.zyyx.zyyxcloud.ali.pay.service.NAME" + service + ";\n" + //TODO
            "import org.springframework.stereotype.Service;\n" +
            "\n" +
            "\n" +
            "/**\n" +
            " * @Description DESCRIBE管理\n" +
            " * @Authror GONG.SHE.NG\n" +
            " * @Date " + DateUtil.formatDateTime(new Date()) + "\n" +
            " */\n" +
            "@Service\n" +
            "public class NAME" + service_impl + " extends ServiceImpl<NAME" + mapper + ", NAME" + entity + "> implements NAME" + service + " {\n" +
            "}\n" +
            "\n";

    private static final String mapperJavaTemplate = "//\n" +
            "// Source code recreated from a .class file by IntelliJ IDEA\n" +
            "// (powered by Fernflower decompiler)\n" +
            "//  欢迎来吃屎\n" +
            "//\n" +
            "package com.zyyx.zyyxcloud.ali.pay.mapper;\n" +
            "\n" +
            "import com.baomidou.mybatisplus.core.mapper.BaseMapper;\n" +
            "import com.zyyx.zyyxcloud.ali.pay.entity.NAME" + entity + ";\n" +
            "import org.apache.ibatis.annotations.Mapper;\n" +
            "\n" +
            "/**\n" +
            " * @Description DESCRIBE管理\n" +
            " * @Authror GONG.SHE.NG\n" +
            " * @Date " + DateUtil.formatDateTime(new Date()) + "\n" +
            " */\n" +
            "@Mapper\n" +
            "public interface NAME" + mapper + " extends BaseMapper<NAME" + entity + "> {\n" +
            "}";

    public void createCode(SysBaseEntity sysBaseEntity, String describe) throws Exception {
        String entityName = sysBaseEntity.getClass().getSimpleName();
        String name = entityName.replace(entity, bak_str);

        String mapping = getMapping(name);

        String controllerUrl = baseUrlStr + "controller" + File.separator + name + controller + file_end;
        String serviceUrl = baseUrlStr + "service" + File.separator + name + service + file_end;
        String serviceImplUrl = baseUrlStr + "service" + File.separator + "impl" + File.separator + name + service_impl + file_end;
        String mapperUrl = baseUrlStr + "mapper" + File.separator + name + mapper + file_end;

        outFile(controllerUrl, controllerJavaTemplate, mapping, describe, name, controller);
        outFile(serviceUrl, serviceJavaTemplate, mapping, describe, name, service);
        outFile(serviceImplUrl, serviceImplJavaTemplate, mapping, describe, name, service_impl);
        outFile(mapperUrl, mapperJavaTemplate, mapping, describe, name, mapper);
    }

}

    