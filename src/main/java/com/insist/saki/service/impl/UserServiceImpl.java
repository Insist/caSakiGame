package com.insist.saki.service.impl;

import com.insist.saki.dao.UserDao;
import com.insist.saki.entity.UserEntity;
import com.insist.saki.service.UserService;
import com.insist.saki.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/3.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public Object save(UserEntity test) {
        return userDao.save(test);
    }

    public UserEntity get(Long id) {
        return userDao.getById(id);
    }

    public boolean exists(String username) {
        return userDao.getByProperty("username", username) != null;
    }

    public UserEntity checkLogin(Map<String, String> dataMap) {
        String uid = dataMap.get("uid");
        String token = dataMap.get("token");
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("id",Long.valueOf(uid));
        map.put("token",token);
        return userDao.getByProperty(map);
    }

    public UserEntity checkLogin(String username, String password) {
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("username",username);
        map.put("password",password);
        UserEntity user = userDao.getByProperty(map);
        user.setToken(MD5Util.MD5("COMIAI_UNOGAME_"+new Date().getTime()+"_username"));
        userDao.update(user);
        return user;
    }
}
