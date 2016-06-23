package com.insist.saki.service;

import com.insist.saki.entity.UserEntity;

import java.util.Map;

/**
 * Created by Administrator on 2016/5/3.
 */
public interface UserService {

    public Object save(UserEntity test);

    UserEntity get(Long id);

    boolean exists(String username);

    UserEntity checkLogin(Map<String, String> dataMap);

    UserEntity checkLogin(String username, String password);
}
