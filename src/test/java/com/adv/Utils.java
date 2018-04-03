package com.adv;

import com.adv.pojo.AdvObj;
import com.adv.pojo.ResultObj;

import java.io.*;
import java.util.List;

/**
 * @author lurongzhi
 */
public class Utils {
    public static void printAdvList(List<AdvObj> advObjList) {
        for (AdvObj advObj : advObjList) {
            System.out.println("id " + advObj.getId());
            System.out.println("name " + advObj.getName());
            System.out.println("file " + advObj.getFileUrl());
            System.out.println("type " + advObj.getType());
            System.out.println("isValid " + advObj.getValid());
            System.out.println("homepage " + advObj.getHomepage());
            System.out.println("displayDetail " + advObj.getDisplayDetail());
            System.out.println("start date " + advObj.getStartDate());
            System.out.println("end date " + advObj.getEndDate());
            System.out.println("user tags " + advObj.getUserTagIds());
            System.out.println("**********************************");
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
}
