package com.insist.saki.util;

import com.insist.saki.model.BaseModel;
import org.apache.log4j.Logger;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2016/5/5.
 */
public class BaseChannel {

    private Logger logger = Logger.getLogger(this.getClass());

    Map<Long,Session> sessions = new ConcurrentHashMap<>();

    public void addSession(Long key,Session session){
        this.sessions.put(key,session);
    }

    public void sendMessageToAll(String message){
        for(Session session:sessions.values()){
            sendMessage(session,message);
        }
    }

    public void sendMessageToAll(String type,Object o){
        BaseModel baseModel = new BaseModel();
        baseModel.setType(type);
        baseModel.setData(o);
        sendMessageToAll(FasterJsonTools.writeValueAsString(baseModel));
    }

    public void sendMessage(Long id, String type,Object o) {
        Session session = sessions.get(id);
        if(session!=null){
            BaseModel baseModel = new BaseModel();
            baseModel.setType(type);
            baseModel.setData(o);
            sendMessage(session, FasterJsonTools.writeValueAsString(baseModel));
        }
    }

    public Session getSessionByUid(Long id) {
        return sessions.get(id);
    }

    public void sendMessage(Session session,String info){
        try {
//            logger.info("send Message"+info);
            session.getBasicRemote().sendText(info);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Session removeSession(Long id) {
        return sessions.remove(id);
    }
}
