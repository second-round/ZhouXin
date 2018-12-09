package com.example.projectone.model;

public class RegBean<T> {
    private String msg;
    private int code;
    private T data;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {

        return msg;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }
}
