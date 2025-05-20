package com.root.controller;


import com.root.sevice.Imlp.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webSocket")
public class TaskController {
   @Autowired
    TestService testService;
    /**
     * Put语句适合去保存和更新数据
     */
    @RequestMapping(value = "/Task", method = RequestMethod.GET)
    public void Task() {
        for (int i=0;i<30;i++){
        testService.exec();
        }
        return ;
    }
}
