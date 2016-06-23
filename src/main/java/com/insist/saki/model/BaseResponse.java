package com.insist.saki.model;

import lombok.Data;

/**
 * Created by Administrator on 2016/5/3.
 */
@Data
public class BaseResponse {
    private int code = 0;
    private String message;
    public static BaseResponse createResponse(int code,String message){
        BaseResponse cm = new BaseResponse();
        cm.setCode(code);
        cm.setMessage(message);
        return cm;
    }
    public final static BaseResponse SUCCESS = BaseResponse.createResponse(0, "success");
}
