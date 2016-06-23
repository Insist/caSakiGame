package com.insist.saki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/4/29.
 */
@Controller
@RequestMapping(name="gate")
public class GateController {

    String baseUrl = "http://localhost:8080/";

    @RequestMapping("/getHttpBaseUrl")
    @ResponseBody
    public String getHttpBaseUrl(){
        return baseUrl;
    }


}
