package com.insist.saki.controller;

import com.insist.saki.entity.UserEntity;
import com.insist.saki.util.FasterJsonTools;
import org.apache.log4j.Logger;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/26.
 */
@ServerEndpoint(value = "/websocket")
public class WsController {

    Logger logger = Logger.getLogger(this.getClass());

    Session session;

    UserEntity user;

    @OnOpen
    public void open(Session s) {
        this.session = s;
    }
    @OnClose
    public void close(CloseReason c) {
    }
    @OnError
    public void error(Throwable t) {
    }
    @OnMessage
    public void receiveMessage(String message,Session session) {
        try {
            logger.info("receive Message:" + message);
            Map<String,String> dataMap = FasterJsonTools.readValue2Map(message, String.class, String.class);
            handlerMessage(dataMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object handlerMessage(Map<String,String> dataMap){
        try {
            if(dataMap.get("type")==null){
                return "";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
