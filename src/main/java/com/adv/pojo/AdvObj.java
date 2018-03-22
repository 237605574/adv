package com.adv.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lurongzhi
 */
public class AdvObj {
    private Long id;
    private String name;
    private List<Long> userTagIds;
    private Integer type;
    //  广告文件下载链接
    private String fileUrl;
    //  广告有效截止日期
    private Date validDate;
    //  点击广告后跳转的商家主页
    private String homepage;
    private Boolean isValid;
    //  json格式
    private String displayDetail;

    public AdvObj() {
        this.name = "";
        userTagIds = new ArrayList<>();
        fileUrl = "";
        validDate = new Date();
        homepage = "";
        isValid = true;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public String getDisplayDetail() {
        return displayDetail;
    }

    public void setDisplayDetail(String displayDetail) {
        this.displayDetail = displayDetail;
    }

    public void removeTag(Long tagId) {
        this.userTagIds.remove(tagId);
    }

    public void addTag(Long tagId) {
        if (this.userTagIds.contains(userTagIds)) {
            return;
        }
        this.userTagIds.add(tagId);
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getUserTagIds() {
        return userTagIds;
    }

    public void setUserTagIds(List<Long> userTagIds) {
        this.userTagIds = userTagIds;
    }
}
