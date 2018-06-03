package com.communityserver.advertisement.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lurongzhi
 */
public class AdvObj {
    private Long id;
    private String name;
    private List<Long> userTagIds = new ArrayList<>();
    private Integer type;
    //  广告文件下载链接
    private String fileUrl;
    //  广告有效截止日期
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date endDate;
    //  广告有效开始日期
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date startDate;
    //  点击广告后跳转的商家主页
    private String homepage;
    private Boolean isValid;
    //  json格式
    private String displayDetail;

    public AdvObj() {

//        this.name = "";
//        userTagIds = new ArrayList<>();
//        fileUrl = "";
//        endDate = new Date();
//        startDate = new Date();
//        homepage = "";
//        isValid = true;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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
