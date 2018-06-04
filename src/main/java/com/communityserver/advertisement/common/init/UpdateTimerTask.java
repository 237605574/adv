package com.communityserver.advertisement.common.init;

import com.communityserver.advertisement.common.config.TimerConfig;
import com.communityserver.advertisement.service.AdvService;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author lurongzhi
 */
public class UpdateTimerTask {
    private AdvService advService;
    private Timer timer = new Timer();

    public static UpdateTimerTask getInstance() {
        return SingletonHolder.instance;
    }

    private UpdateTimerTask() {
    }

    private static class SingletonHolder {
        private static final UpdateTimerTask instance = new UpdateTimerTask();
    }

    public void init() {
        advService =Init.getBean(AdvService.class);
    }

    public void start() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int result = advService.updateAdvState();
                System.out.println("upadte:" + result);
            }
        }, 0, TimerConfig.getInstance().getTimeHour() * 5000);
    }

    public void restart() {
        timer.cancel();
        start();
    }
}
