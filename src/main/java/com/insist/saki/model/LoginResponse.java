package com.insist.saki.model;

import lombok.Data;

/**
 * Created by Administrator on 2016/5/4.
 */
@Data
public class LoginResponse extends BaseResponse {

    public String token;
    public Long uid;

    public static LoginResponse createResponse(int code,String message){
        LoginResponse cm = new LoginResponse();
        cm.setCode(code);
        cm.setMessage(message);
        return cm;
    }
    public static LoginResponse createSuccessResponse(String token,Long uid){
        LoginResponse cm = new LoginResponse();
        cm.setCode(0);
        cm.setMessage("success");
        cm.setToken(token);
        cm.setUid(uid);
        return cm;
    }

}
