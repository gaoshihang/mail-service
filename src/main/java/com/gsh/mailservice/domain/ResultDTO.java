package com.gsh.mailservice.domain;

public class ResultDTO<T> {
    private int code; //结果编码
    private String msg; //结果信息
    private T result; //结果内容

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
