package com.root.common;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.manager.handler.StartUpHandlerImpl;
import com.gitee.sunchenbin.mybatis.actable.manager.util.ConfigurationUtil;
import com.gitee.sunchenbin.mybatis.actable.utils.ClassTools;
import com.root.mapper.CreateForeignKeyMapper;
import com.root.mybatis.annotation.ForeignKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.*;

@Component
public class MySqlCreateForeignKey extends StartUpHandlerImpl {
    private static final Logger log = LogManager.getLogger(MySqlCreateForeignKey.class);
    @Autowired
    private ConfigurationUtil springContextUtil;
    @Value("${mybatis.model.tableSpace:NULL}")
    String tableSpace;
    private static String pack;
    private static String tableAuto = null;
    @Autowired
    @Resource
    CreateForeignKeyMapper createForeignKeyMapper;
    private static String FOREIGN_KEY_HEAD = "fk_";
    private static String UNDERLINE = "_";
    Map<String, List<Map>> FOREIGN_KEY_MAP = new HashMap();

    public MySqlCreateForeignKey() {
    }

    @PostConstruct
    public void mySqlCreateForeignKey() {
        pack = this.springContextUtil.getConfig("mybatis.model.pack");
        tableAuto = this.springContextUtil.getConfig("mybatis.table.auto");
        if (!"none".equals(tableAuto) && !"update".equals(tableAuto) && !"create".equals(tableAuto) && !"add".equals(tableAuto)) {
            log.warn("配置mybatis.table.auto错误无法识别，当前配置只支持[none/update/create/add]三种类型!");
        } else if ("none".equals(tableAuto)) {
            log.info("配置mybatis.table.auto=none，不需要做任何事情");
        } else {
            Set<Class<?>> classes = ClassTools.getClasses(pack);
            Iterator var2 = classes.iterator();

            while(true) {
                Class clazz;
                Table table;
                do {
                    if (!var2.hasNext()) {
                        log.info("外键集合" + this.FOREIGN_KEY_MAP);
                        return;
                    }

                    clazz = (Class)var2.next();
                    table = (Table)clazz.getAnnotation(Table.class);
                } while(table == null);

                try {
                    List<Map<String, String>> tableForeignKeyList = this.createForeignKeyMapper.queryForeignKey(this.tableSpace, table.name());
                    if (tableForeignKeyList != null && tableForeignKeyList.size() != 0) {
                        for(int i = 0; i < tableForeignKeyList.size(); ++i) {
                            Map<String, String> foreignKey = (Map)tableForeignKeyList.get(i);
                            String tableName = (String)foreignKey.get("TABLE_NAME");
                            String foreignKeyName = (String)foreignKey.get("CONSTRAINT_NAME");
                            this.createForeignKeyMapper.dropForeignKey(tableName, foreignKeyName);
                        }
                    }
                } catch (Exception var16) {
                    log.info("查询外键或删除外键失败" + var16);
                    throw new RuntimeException("查询外键或删除外键失败" + var16);
                }

                Field[] fields = clazz.getDeclaredFields();
                fields = this.recursionParents(clazz, fields);
                List<Map> list = new ArrayList();
                Field[] var19 = fields;
                int var20 = fields.length;

                for(int var21 = 0; var21 < var20; ++var21) {
                    Field field = var19[var21];
                    ForeignKey foreignKey = (ForeignKey)field.getAnnotation(ForeignKey.class);
                    Column column = (Column)field.getAnnotation(Column.class);
                    if (foreignKey != null && column != null) {
                        Map<String, String> map = new HashMap();
                        map.put("refTable", foreignKey.tableName());
                        map.put("refTableColumn", foreignKey.tableColumn());
                        map.put("currentTable", table.name());
                        map.put("currentTableColumn", column.name());
                        map.put("foreignKeyName", FOREIGN_KEY_HEAD + table.name() + UNDERLINE + column.name());
                        list.add(map);

                        try {
                            this.createForeignKeyMapper.addForeignKey(map);
                        } catch (Exception var15) {
                            log.info("创建外键错误" + var15);
                            throw new RuntimeException("创建外键错误" + var15);
                        }
                    }
                }

                if (list.size() != 0) {
                    this.FOREIGN_KEY_MAP.put(table.name(), list);
                }
            }
        }
    }

    private Field[] recursionParents(Class<?> clazz, Field[] fields) {
        if (clazz.getSuperclass() != null) {
            Class clsSup = clazz.getSuperclass();
            List<Field> fieldList = new ArrayList();
            fieldList.addAll(Arrays.asList(fields));
            fieldList.addAll(Arrays.asList(clsSup.getDeclaredFields()));
            fields = new Field[fieldList.size()];
            int i = 0;
            Object[] var6 = fieldList.toArray();
            int var7 = var6.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                Object field = var6[var8];
                fields[i] = (Field)field;
                ++i;
            }

            fields = this.recursionParents(clsSup, fields);
        }

        return fields;
    }
}
