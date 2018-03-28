package com.adv;

import com.adv.dao.DaoFacade;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author lurongzhi
 */
public class Init implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DaoFacade.getInstance().init(sce);
        UpdateTimerTask.getInstance().init(sce);
        UpdateTimerTask.getInstance().start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }


}
