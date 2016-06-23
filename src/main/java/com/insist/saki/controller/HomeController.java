package com.insist.saki.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2015/11/6.
 */
@Controller
@RequestMapping
public class HomeController {

    @Value("#{configProperties['websocket.base.url']}")
    String baseUrl = "";

    @RequestMapping("/index")
    public String index(Long id){
        return "/index";
    }

    @RequestMapping("/game")
    public String game(Long id,HttpServletRequest req, HttpServletResponse resp){
        req.setAttribute("baseUrl",baseUrl);
        return "/game";
    }

    @RequestMapping("/test")
    public String test(Long id,HttpServletRequest req, HttpServletResponse resp){
        return "/test";
    }

}
