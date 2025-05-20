//package com.root.websocket.controller;
//
//import com.root.dto.ReturnMessage;
//
//import com.root.websocket.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Date;
//
///**
// * @author ：fuyunxiang
// * @date ：Created in 2022年7月26日,0026 9:28
// */
//@RestController
//@RequestMapping("/user")
//@CrossOrigin
//public class UserController {
//    @Autowired
//    UserService userService;
//
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public ReturnMessage register(String name, String password, String mailNo, String uuid, String code) {
//            return userService.creatUser(name, password, mailNo, uuid, code);
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public ReturnMessage login(String name, String password, String uuid, String code) {
//
//        return userService.getUser(name, password, uuid, code);
//    }
//
//
//}
