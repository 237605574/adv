package com.communityserver.advertisement.common.utils;

import com.communityserver.advertisement.entity.AdvObj;
import com.communityserver.advertisement.entity.ResultObj;

import java.io.*;
import java.util.List;

/**
 * @author Administrator
 */
public class AdvUtils {
    public static void printAdvList(List<AdvObj> advObjList) {
        for (AdvObj advObj : advObjList) {
            printAdv(advObj);
        }
    }

    public static void printFileResult(ResultObj<File> fileResultObj) throws IOException {
        if (fileResultObj.isSuccess()) {
            File file = fileResultObj.getData();
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } else {
            System.out.println("code " + fileResultObj.getCode());
            System.out.println("msg " + fileResultObj.getMsg());
        }
    }

    public static void printListResult(ResultObj<List<AdvObj>> resultObj) {
        if (resultObj.isSuccess()) {
            printAdvList(resultObj.getData());
        } else {
            System.out.println("code " + resultObj.getCode());
            System.out.println("msg " + resultObj.getMsg());
        }
    }
    public static void printAdv(AdvObj advObj){
        if (advObj != null) {
            System.out.println("id " + advObj.getId());
            System.out.println("name " + advObj.getName());
            System.out.println("file " + advObj.getFileUrl());
            System.out.println("type " + advObj.getType());
            System.out.println("isValid " + advObj.getValid());
            System.out.println("homepage " + advObj.getHomepage());
            System.out.println("displayDetail " + advObj.getDisplayDetail());
            System.out.println("start date " + advObj.getStartDate());
            System.out.println("type " + advObj.getType());
            System.out.println("end date " + advObj.getEndDate());
            System.out.println("user tags " + advObj.getUserTagIds());
            System.out.println("**********************************");
        } else {
            System.out.println("adv obj is null");
        }

    }
}

