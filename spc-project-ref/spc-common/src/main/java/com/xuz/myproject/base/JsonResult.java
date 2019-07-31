package com.xuz.myproject.base;

public class JsonResult {
    private Integer code;
    private String msg;
    private Object data;

    public JsonResult() {
        super();
    }

    public JsonResult(Integer _code, String _msg, Object _data) {
        super();
        this.code = _code;
        this.msg = _msg;
        this.data = _data;
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
