package com.insist.saki.service;

import com.insist.saki.entity.UserEntity;
import com.insist.saki.util.ServiceUtil;

import java.util.Map;

/**
 * Created by Administrator on 2016/6/23.
 */
public interface GameService {

    public UserEntity loginGame(Map<String, String> dataMap);

    public void startRankGame(Map<String, String> dataMap);

    public void cancelRankGame(Map<String, String> dataMap);

    public void doAction(Map<String, String> dataMap);
}
