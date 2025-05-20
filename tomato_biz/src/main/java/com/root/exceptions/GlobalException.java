package com.root.exceptions;

import com.root.enums.ServiceEnum;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

/**
 * 自定义错误
 *
 * @author gaoj
 */
@Data
@Log4j2
public class GlobalException extends RuntimeException {

    private int code;
    private Object data;
    private String msg;

    public GlobalException(ServiceEnum serviceEnum) {
        this(serviceEnum.getMsg(), serviceEnum.getCode());
    }


    public GlobalException(ServiceEnum serviceEnum, Object data) {
        this(serviceEnum.getMsg(), serviceEnum.getCode(), data);
    }

    public GlobalException(Integer code, String msg) {
        super(msg);
        log.error(msg);
        this.code = code;
    }

    public GlobalException(String msg, Integer code) {
        super(msg);
        log.error(msg);
        this.code = code;
    }

    private GlobalException(String msg, Integer code, Object data) {
        super(msg);
        this.code = code;
        this.data = data;
    }

//    public GlobalException(ServiceEnum serviceEnum, String... params) {
//        String msg = serviceEnum.getMsg();
//        for(int i = 0; i < params.length ; i++){
//            this.msg = msg.replaceFirst("\\{\\}",params[i]);
//        }
//        this.code = serviceEnum.getCode();
//        this.msg = msg;
//    }
}
