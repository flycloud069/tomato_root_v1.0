package com.root.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CreateForeignKeyMapper {
    List<Map<String, String>> queryForeignKey(@Param("tableSpace") String var1, @Param("tableName") String var2);

    void dropForeignKey(@Param("tableName") String var1, @Param("foreignKeyName") String var2);

    void addForeignKey(@Param("map") Map var1);
}
