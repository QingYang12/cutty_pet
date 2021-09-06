package com.cutty_pet.cutty_pet.common;

public class ResponseDto {
    Integer code;
    String msg;
    Object data;

    public ResponseDto() {
    }
    public ResponseDto(Integer code,String msg,Object data) {
        this.code=code;
        this.msg=msg;
        this.data=data;
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
