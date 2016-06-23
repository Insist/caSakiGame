package com.insist.saki.model;

import lombok.Data;

/**
 * Created by Administrator on 2015/12/21.
 */
@Data
public class BaseModel extends BaseResponse {

    private Object data;
    private String type;
}
