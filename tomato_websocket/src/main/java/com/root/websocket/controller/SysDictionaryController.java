//package com.root.websocket.controller;
//
//import com.root.dto.ReturnMessage;
//import com.root.mapper.SysDictionaryMapper;
//import com.root.util.ResponseUtil;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@RestController
//@RequestMapping("/SysDictionary")
//public class SysDictionaryController {
//     @Resource
//     SysDictionaryMapper sysDictionaryMapper;
//
//    @RequestMapping(value = "/list", method = RequestMethod.POST)
//    public ReturnMessage list() {
//        List list=sysDictionaryMapper.list();
//        return ResponseUtil.success(list);
//    }
//
//}
