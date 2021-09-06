package com.cutty_pet.cutty_pet.common;




import  com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class PageResult {
    Integer pageNum;
    Integer pageSize;
    Integer total;
    ResponseDto pageDate;
    Integer code;
    String msg;

    public PageResult(Object vo, ResponseDto responseDto,int code,String msg) throws Exception{
        JSONObject json=(JSONObject) JSONObject.toJSON(vo);
        this.pageNum = (Integer) json.get("pageNum");
        this.pageSize = (Integer) json.get("pageSize");
        pageDate=responseDto;
        this.total=((List)responseDto.getData()).size();
        this.code=code;
        this.msg=msg;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public ResponseDto getPageDate() {
        return pageDate;
    }

    public void setPageDate(ResponseDto pageDate) {
        this.pageDate = pageDate;
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
}
