package com.root.sevice.Imlp;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class TestService {


    @Async("taskExecutor1")//使用上面配置的taskExecutor1线程池
    public Future exec(){
        try {
            Thread.sleep(3000);
        }catch (Exception e){

        }

        Future future = new AsyncResult("dududududu");
        System.out.println(Thread.currentThread().getName()+111111);

        return future;
    }


}