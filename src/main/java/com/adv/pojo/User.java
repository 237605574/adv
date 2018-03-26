package com.adv.pojo;

import java.util.List;

/**
 * @author lurongzhi
 */
public class User {
    private List<Long> tagIds;
    private List<Long> advIds;
    private Long id;
    private String psw;

    public void removeAdv(Long advId) {
        this.advIds.remove(advId);
    }

    public void addAdv(Long advId) {
        if (this.advIds.contains(advId)) {
            return;
        }
        this.tagIds.add(advId);
    }

    public void removeTag(Long tagId) {
        this.tagIds.remove(tagId);
    }

    public void addTag(Long tagId) {
        if (this.tagIds.contains(tagId)) {
            return;
        }
        this.tagIds.add(tagId);
    }

    public List<Long> getAdvIds() {
        return advIds;
    }

    public void setAdvIds(List<Long> advIds) {
        this.advIds = advIds;
    }

    public List<Long> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Long> tagIds) {
        this.tagIds = tagIds;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
