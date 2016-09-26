package com.insist.saki.manager;

import com.insist.saki.entity.UserEntity;
import com.insist.saki.main.GameUtils;
import com.insist.saki.util.ServiceUtil;

import java.util.Map;

/**
 * Created by Administrator on 2016/6/22.
 */
public class GameManager {

    public static UserEntity loginGame(Map<String, String> dataMap) {
        UserEntity user = ServiceUtil.getUserService().checkLogin(dataMap);
        return user;
    }

    public static void startRankGame(Map<String, String> dataMap) {
    }

    public static void cancelRankGame(Map<String, String> dataMap) {
    }

    public static void doAction(Map<String, String> dataMap) {
    }

}
