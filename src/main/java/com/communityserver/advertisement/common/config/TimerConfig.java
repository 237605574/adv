package com.communityserver.advertisement.common.config;

/**
 * @author lurongzhi
 */
public class TimerConfig {
    private Long timeHour = 10L;

    public static TimerConfig getInstance() {
        return SingletonHolder.instance;
    }

    private TimerConfig() {
    }

    private static class SingletonHolder {
        private static final TimerConfig instance = new TimerConfig();
    }


    public Long getTimeHour() {
        return timeHour;
    }

    public void setTimeHour(Long timeHour) {
        this.timeHour = timeHour;
    }

    public Long getTimeMills() {
        return timeHour * 3600;
    }
}
