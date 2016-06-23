package com.insist.saki.util;

import com.insist.saki.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by Administrator on 2016/5/11.
 */
public class ServiceUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {
        ServiceUtil.applicationContext=applicationContext;
    }

    public static UserService getUserService(){
        return ServiceUtil.applicationContext.getBean(UserService.class);
    }

}
