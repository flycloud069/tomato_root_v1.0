package com.root.dto;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

@Data
public class SysMenuDto {
    /**
     *菜单名称
     */
    private String name;
    /**
     *菜单父id
     */
    private String parentId;

    /**
     *菜单id
     */
    private String id;

    /**
     *菜单对象
     */
    private String component;
    /**
     *菜单路径
     */
    private String path;
    /**
     * 重定向url
     */
    private  String redirect;
    /**
     * 菜单是否隐藏
     */
    private  Boolean hidden;
    /**
     * 菜单序号
     */
    private  Integer menu_xh;
    /**
     *菜单补充
     */
    private MetaDto meta;


    public SysMenuDto(String name,String parentId,String id,String path,String component,MetaDto meta,String redirect ,Boolean hidden,Integer menu_xh){
        this.name=name;
        this.parentId=parentId;
        this.id=id;
        this.component=component;
        this.meta=meta;
        this.path=path;
        this.redirect=redirect;
        this.hidden= hidden;
        this.menu_xh=menu_xh;
    }
}
