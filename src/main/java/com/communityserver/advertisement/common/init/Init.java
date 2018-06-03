package com.communityserver.advertisement.common.init;

import com.communityserver.advertisement.common.idgenerator.IdMgr;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author lurongzhi
 */

public class Init implements ApplicationListener<ContextRefreshedEvent> {
    private static ApplicationContext applicationContext;


    //  初始化操作
    @Override
    public void onApplicationEvent(ContextRefreshedEvent cre) {
        if (cre.getApplicationContext().getParent() == null) {
            System.out.println("----------- init ---------------");
            applicationContext = cre.getApplicationContext();
            //  初始化id
            IdMgr.getInstance().init();

            //  启动定时更新
            UpdateTimerTask.getInstance().init();
            UpdateTimerTask.getInstance().start();
        }
    }

    public static  <T> T getBean(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }

}
