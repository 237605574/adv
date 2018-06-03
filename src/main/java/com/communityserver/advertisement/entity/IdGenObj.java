package com.communityserver.advertisement.entity;

import java.io.Serializable;

/**
 * @author lurongzhi
 */
public class IdGenObj implements Serializable {
    private String name;
    private int step;
    private long currentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public long getCurrentId() {
        return currentId;
    }

    public void setCurrentId(long currentId) {
        this.currentId = currentId;
    }

    public void nextStep() {
        this.currentId = this.currentId + this.step;
    }
}
