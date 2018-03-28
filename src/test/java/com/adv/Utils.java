package com.adv;

import com.adv.pojo.ResultObj;

import java.io.*;

/**
 * @author lurongzhi
 */
public class Utils {
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
