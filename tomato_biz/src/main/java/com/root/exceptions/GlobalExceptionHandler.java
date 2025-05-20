package com.root.exceptions;

import com.root.dto.ReturnMessage;
import com.root.enums.ServiceEnum;
import com.root.util.ResponseUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(GlobalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ReturnMessage handleUserNotExistException(GlobalException e) {
        log.error(e.getMessage(), e);
        return ResponseUtil.fail(e.getCode(), e.getMessage(), e.getData());
    }


    /**
     * 自定义异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ReturnMessage handleUserNotExistException(Exception ex){
        log.error(ex.getMessage(),ex);
        return  ResponseUtil.fail(ServiceEnum.SYSTEM_ERROR.getCode(), ServiceEnum.SYSTEM_ERROR.getMsg());
    }


    /**
     * 参数验证异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ReturnMessage handleUserNotExistException2(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        for(int i = 0 ; i < bindingResult.getFieldErrors().size() ;i++){
            FieldError fieldError = bindingResult.getFieldErrors().get(i);
            Map map = new HashMap<String,String>();
            map.put("fieId",fieldError.getField());
            map.put("errorMsg",fieldError.getDefaultMessage());
            list.add(map);
        }
        return  ResponseUtil.fail(-1,list.get(0).get("fieId")+":"+list.get(0).get("errorMsg"),list);
    }
}
