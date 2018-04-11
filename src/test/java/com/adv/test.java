package com.adv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Administrator
 */
public class test {
    public static void main(String[] args) throws ParseException {
        printOption();

//        dateTest();
    }

    private static void dateTest() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateStr = "0200-01-01T00:00";
        Date date = sdf.parse(dateStr);
        System.out.println(date);
    }

    private static void printOption() {
//        String formatStr = " <option value=\"%s\">%s:00 —— %s:00</option>";
        String formatStr = " %s:\"%s:00 —— %s:00\",";
        for (int i=0;i<24;i++){
            int ii = i+1;
            String str = String.format(formatStr, i,i,ii);
            System.out.println(str);
        }
    }
}
