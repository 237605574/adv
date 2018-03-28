package com.adv;

import com.adv.config.TimerConfig;
import com.adv.dao.AdvDao;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author lurongzhi
 */
public class UpdateTimerTask {
    private AdvDao advDao;
    private Timer timer = new Timer();

    public static UpdateTimerTask getInstance() {
        return SingletonHolder.instance;
    }

    private UpdateTimerTask() {
    }

    private static class SingletonHolder {
        private static final UpdateTimerTask instance = new UpdateTimerTask();
    }

    public void init(ServletContextEvent sce) {
        advDao = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext()).getBean(AdvDao.class);
    }

    public void start() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int result = advDao.updateState();
                System.out.println("upadte:" + result);
            }
        }, 0,TimerConfig.getInstance().getTimeHour()*500);
    }

    public void restart(){
        timer.cancel();
        start();
    }
}
