package com.communityserver.advertisement.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lurongzhi
 */
public class TimeCostUtils {
    class CostObj {
        private Long time;
        private String name;

        public CostObj(Long time, String name) {
            this.time = time;
            this.name = name;
        }
    }

    ThreadLocal<List<CostObj>> costList = new ThreadLocal<>();
    ThreadLocal<Long> lastTime = new ThreadLocal<>();

    public static TimeCostUtils getInstance() {
        return SingletonHolder.instance;
    }

    private TimeCostUtils() {
    }

    private static class SingletonHolder {
        private static final TimeCostUtils instance = new TimeCostUtils();
    }

    public  void start() {
        lastTime.set(System.currentTimeMillis());
        costList.set(new ArrayList<>());
    }

    public void setPoint(String desc) {
        if (lastTime.get() == null) {
            System.out.println("没有调用start方法");
            return;
        }
        Long thisTime = System.currentTimeMillis();
        Long deltaTime = thisTime - lastTime.get();
        CostObj costObj = new CostObj(deltaTime, desc);
        lastTime.set(thisTime);
        costList.get().add(costObj);
    }

    public void print() {
        if (lastTime.get() == null) {
            System.out.println("没有调用start方法");
            return;
        }
        List<CostObj> costObjs = costList.get();
        System.out.println("--------------------------------------------");
        for (CostObj costObj : costObjs) {
            System.out.println(costObj.name + ":" + costObj.time);
        }
        System.out.println("--------------------------------------------");
        lastTime.set(null);
        costList.set(null);
    }

}
