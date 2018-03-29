package com.adv;

import com.adv.idgenerator.IdMgr;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author lurongzhi
 */
//@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {
    private static ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent cre) {
        if (cre.getApplicationContext().getParent() == null) {
            applicationContext = cre.getApplicationContext();
            IdMgr.getInstance().init();
            UpdateTimerTask.getInstance().init();
            UpdateTimerTask.getInstance().start();
        }
    }

    public static  <T> T getBean(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }

}
