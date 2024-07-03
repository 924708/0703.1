package com.soft.demo04.beans;

public class JsonResult {
    private int code;
    private Object result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "code=" + code +
                ", result=" + result +
                '}';
    }
}