package com.root.util;

import cn.hutool.json.JSONUtil;
import com.root.dto.ReturnMessage;
import com.root.enums.ResponseStatus;

public final class ResponseUtil {
    public static ReturnMessage success() {
        return new ReturnMessage();
    }

    public static ReturnMessage success(int code, String msg) {
        return new ReturnMessage(code, msg);
    }

    public static ReturnMessage success(Object data) {
        return new ReturnMessage(data);
    }

    public static ReturnMessage success(int code, Object data) {
        return new ReturnMessage(code, data);
    }

    public static ReturnMessage success(String msg, Object data) {
        return new ReturnMessage(msg, data);
    }

    public static ReturnMessage mybatiesResult(int i) {
        if (i!=0){
            return new ReturnMessage();
        }else {
            return new ReturnMessage(ResponseStatus.ERROR.getCode(), ResponseStatus.ERROR.getMsg());
        }

    }


    public static String jsonSuccess() {
        ReturnMessage returnMessage = new ReturnMessage();
        return JSONUtil.parseObj(returnMessage).toString();
    }

    public static String jsonSuccess(int code, String msg) {
        ReturnMessage returnMessage = new ReturnMessage(code, msg);
        return JSONUtil.parseObj(returnMessage).toString();
    }

    public static String jsonSuccess(Object data) {
        ReturnMessage returnMessage = new ReturnMessage(data);
        return JSONUtil.parseObj(returnMessage).toString();
    }

    public static String jsonSuccess(int code, Object data) {
        ReturnMessage returnMessage = new ReturnMessage(code, data);
        return JSONUtil.parseObj(returnMessage).toString();
    }

    public static String jsonSuccess(String msg, Object data) {
        ReturnMessage returnMessage = new ReturnMessage(msg, data);
        return JSONUtil.parseObj(returnMessage).toString();
    }

    public static ReturnMessage fail() {
        return new ReturnMessage(ResponseStatus.ERROR.getCode(), ResponseStatus.ERROR.getMsg());
    }

    public static ReturnMessage fail(int code, String msg) {
        return new ReturnMessage(code, msg);
    }

    public static ReturnMessage fail(String message) {
        return new ReturnMessage(ResponseStatus.ERROR.getCode(), message.isEmpty() ? ResponseStatus.ERROR.getMsg() : message);
    }

    public static ReturnMessage fail(int code, Object data) {
        return new ReturnMessage(code, data);
    }

    public static ReturnMessage fail(String msg, Object data) {
        return new ReturnMessage(msg, data);
    }

    public static ReturnMessage fail(int code, String msg, Object data) {
        return new ReturnMessage(code, msg, data);
    }

    public static ReturnMessage fail(Object data) {
        return new ReturnMessage(ResponseStatus.ERROR.getCode(), ResponseStatus.ERROR.getMsg(), data);
    }

    public static String jsonFail() {
        ReturnMessage returnMessage = new ReturnMessage(ResponseStatus.ERROR.getCode(), ResponseStatus.ERROR.getMsg());
        return JSONUtil.parseObj(returnMessage).toString();
    }

    public static String jsonFail(int code, String msg) {
        ReturnMessage returnMessage = new ReturnMessage(code, msg);
        return JSONUtil.parseObj(returnMessage).toString();
    }

    public static String jsonFail(Object data) {
        ReturnMessage returnMessage = new ReturnMessage(ResponseStatus.ERROR.getCode(), ResponseStatus.ERROR.getMsg(), data);
        return JSONUtil.parseObj(returnMessage).toString();
    }

    public static String jsonFail(int code, Object data) {
        ReturnMessage returnMessage = new ReturnMessage(code, data);
        return JSONUtil.parseObj(returnMessage).toString();
    }

    public static String jsonFail(String msg, Object data) {
        ReturnMessage returnMessage = new ReturnMessage(msg, data);
        return JSONUtil.parseObj(returnMessage).toString();
    }

    public static String jsonFail(int code, String msg, Object data) {
        ReturnMessage returnMessage = new ReturnMessage(code, msg, data);
        return JSONUtil.parseObj(returnMessage).toString();
    }

    private ResponseUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
