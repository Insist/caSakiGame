package com.insist.saki.controller;

import com.insist.saki.model.BaseResponse;
import com.insist.saki.model.LoginResponse;
import com.insist.saki.entity.UserEntity;
import com.insist.saki.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/5/3.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    static Pattern nameMatcher = Pattern.compile("[0-9a-zA-Z@_]{8,30}");
    static Pattern passMatcher = Pattern.compile("[0-9a-zA-Z_!@#$%&]{6,30}");

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse register(String username,String password,HttpServletRequest req, HttpServletResponse resp){
        //checkName
        if(!nameMatcher.matcher(username).matches()){
            return BaseResponse.createResponse(1, "username error!");
        }
        if(!passMatcher.matcher(password).matches()){
            return BaseResponse.createResponse(2, "password error!");
        }
        if(userService.exists(username)){
            return BaseResponse.createResponse(3, "username exists!");
        }
        UserEntity entity = new UserEntity();
        entity.setNickname(username);
        entity.setUsername(username);
        entity.setPassword(password);
        userService.save(entity);
        return BaseResponse.SUCCESS;
    }

    @RequestMapping("/login")
    @ResponseBody
    public BaseResponse login(String username,String password,HttpServletRequest req, HttpServletResponse resp){
        UserEntity user = userService.checkLogin(username, password);
        if(user == null){
            return LoginResponse.createResponse(101, "登录错误");
        }
        Cookie token = new Cookie("uno-token",user.getToken());token.setPath("/");
        Cookie uid = new Cookie("uno-uid",user.getId()+"");uid.setPath("/");
        resp.addCookie(token);
        resp.addCookie(uid);
        return LoginResponse.createSuccessResponse("",0l);
    }

}
