package com.root.auto;

import com.root.sevice.Imlp.SysExtractsServiceImpl;
import com.root.sevice.SysExtractsService;
import com.root.util.ApplicationContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysExtractServiceTask implements Runnable {
    String sys_extracts_service_id;
    String inoutString;

    @Override
    public void run() {
        // 这里编写需要在新线程中运行的逻辑代码
        ApplicationContextUtils.autowire(new SysExtractsServiceImpl()).runSysExtractsService(this.sys_extracts_service_id, this.inoutString);
    }
    public SysExtractServiceTask  init(String sys_extracts_service_id, String inputString){
        this.sys_extracts_service_id = sys_extracts_service_id;
        this.inoutString = inputString;
        return  this;
    }
}
