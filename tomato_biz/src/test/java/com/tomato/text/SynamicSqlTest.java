package com.tomato.text;


import com.WkdbackmannngeApplication;
import com.root.sevice.SysBaseServiceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = WkdbackmannngeApplication.class)
@RunWith(SpringRunner.class)
public class SynamicSqlTest {

    @Test
    public void contextLoads()  {
        System.out.println(select("*",from("tst")));
    }
    public String select(String s,String f){
        return  "select "+s +f;
    }
    public String from(String s){
        return "from "+s;
    }
}
