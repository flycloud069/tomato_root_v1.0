package com.root.dto;

import com.root.enums.ResponseStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(
        value = "ReturnMessage",
        description = "统一返回对象"
)
public class ReturnMessage<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(
            value = "操作码",
            notes = "默认成功200,失败500"
    )
    private int code;
    @ApiModelProperty(
            value = "操作结果",
            notes = "默认为对应的成功和失败提示"
    )
    private String msg;
    @ApiModelProperty(
            value = "返回对象",
            notes = "任意结构的对象"
    )
    private T data;

    public ReturnMessage() {
        this.code = ResponseStatus.SUCCESS.getCode();
        this.msg = ResponseStatus.SUCCESS.getMsg();
    }

    public ReturnMessage(T data) {
        this.code = ResponseStatus.SUCCESS.getCode();
        this.msg = ResponseStatus.SUCCESS.getMsg();
        this.data = data;
    }

    public ReturnMessage(int code, T data) {
        this.code = ResponseStatus.SUCCESS.getCode();
        this.msg = ResponseStatus.SUCCESS.getMsg();
        this.data = data;
        this.code = code;
    }

    public ReturnMessage(String msg, T data) {
        this.code = ResponseStatus.SUCCESS.getCode();
        this.msg = ResponseStatus.SUCCESS.getMsg();
        this.data = data;
        this.msg = msg;
    }

    public ReturnMessage(Throwable e) {
        this.code = ResponseStatus.SUCCESS.getCode();
        this.msg = ResponseStatus.SUCCESS.getMsg();
        this.msg = e.getMessage();
        this.code = -1;
    }

    public ReturnMessage(int code, String msg, T data) {
        this.code = ResponseStatus.SUCCESS.getCode();
        this.msg = ResponseStatus.SUCCESS.getMsg();
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public ReturnMessage(int code, String msg) {
        this.code = ResponseStatus.SUCCESS.getCode();
        this.msg = ResponseStatus.SUCCESS.getMsg();
        this.code = code;
        this.msg = msg;
    }

    public static <T> ReturnMessage.ReturnMessageBuilder<T> builder() {
        return new ReturnMessage.ReturnMessageBuilder();
    }

    public String toString() {
        return "ReturnMessage(super=" + super.toString() + ", code=" + this.getCode() + ", msg=" + this.getMsg() + ", data=" + this.getData() + ")";
    }

    public int getCode() {
        return this.code;
    }

    public ReturnMessage<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return this.msg;
    }

    public ReturnMessage<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public ReturnMessage<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static class ReturnMessageBuilder<T> {
        private int code;
        private String msg;
        private T data;

        ReturnMessageBuilder() {
        }

        public ReturnMessage.ReturnMessageBuilder<T> code(int code) {
            this.code = code;
            return this;
        }

        public ReturnMessage.ReturnMessageBuilder<T> msg(String msg) {
            this.msg = msg;
            return this;
        }

        public ReturnMessage.ReturnMessageBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ReturnMessage<T> build() {
            return new ReturnMessage(this.code, this.msg, this.data);
        }

        public String toString() {
            return "ReturnMessage.ReturnMessageBuilder(code=" + this.code + ", msg=" + this.msg + ", data=" + this.data + ")";
        }
    }
}
