package com.root.websocket.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.root.dto.*;
import com.root.mapper.SysColumnsMapper;
import com.root.mapper.SysMenuMapper;
import com.root.mapper.SysTableMapper;
import com.root.memory.LoginTokenMemory;
import com.root.util.ResponseUtil;
import com.root.util.TomatoHttpUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/SysMenu")
public class SysMenuController {
    @Resource
    SysMenuMapper sysMenuMapper;


    @RequestMapping(value = "/getMenu", method = RequestMethod.GET)
    public ReturnMessage getMenu(HttpServletRequest httpServletRequest) {
        LoginDto loginDto = LoginTokenMemory.getUserIdByToken(TomatoHttpUtil.getToken(httpServletRequest));
        List list = sysMenuMapper.getMenu(loginDto);
        List<SysMenuDto> sysMenuList = new ArrayList();
        for (Object menuObject : list) {
            Map menuMap = (Map) menuObject;
            MetaDto metaDto = new MetaDto(Convert.toStr(menuMap.get("title")));
            SysMenuDto sysMenuDto = new SysMenuDto(
                    Convert.toStr(menuMap.get("name")),
                    Convert.toStr(menuMap.get("parentId")),
                    Convert.toStr(menuMap.get("id")),
                    Convert.toStr(menuMap.get("path")),
                    Convert.toStr(menuMap.get("component")),
                    metaDto, Convert.toStr(menuMap.get("redirect")),
                    Convert.toBool(menuMap.get("hidden")),
                    Convert.toInt(menuMap.get("menu_xh"))
            );
            sysMenuList.add(sysMenuDto);
        }
        return ResponseUtil.success(sysMenuList);
    }

    @RequestMapping(value = "/saveSysRoleFunction", method = RequestMethod.POST)
    public ReturnMessage saveSysRoleFunction(@RequestBody List<SysFunctionDTO> sysFunctionDTOList) {
        return ResponseUtil.mybatiesResult(sysMenuMapper.saveSysRoleFunction(sysFunctionDTOList));

    }

    @RequestMapping(value = "/deleteSysRoleFunction", method = RequestMethod.POST)
    public ReturnMessage deleteSysRoleFunction(@RequestBody List<SysFunctionDTO> sysFunctionDTOList) {
        return ResponseUtil.mybatiesResult(sysMenuMapper.deleteSysRoleFunction(sysFunctionDTOList));

    }

}
