package com.adv.utils;

import com.adv.pojo.AdvObj;

/**
 * @author Administrator
 */
public class AdvUtils {
    public static void printAdv(AdvObj advObj){
        if (advObj != null) {
            System.out.println("name:" + advObj.getName());
            System.out.println("file url:" + advObj.getFileUrl());
            System.out.println("display detail:" + advObj.getDisplayDetail());
            System.out.println("homepage:" + advObj.getHomepage());
            System.out.println("user tags:" + advObj.getUserTagIds());
            System.out.println("start date:" + advObj.getStartDate());
            System.out.println("end date:" + advObj.getEndDate());
        } else {
            System.out.println("adv obj is null");
        }

    }
}

