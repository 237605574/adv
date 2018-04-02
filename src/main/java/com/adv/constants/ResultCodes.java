package com.adv.constants;

/**
 * @author lurongzhi
 */
public class ResultCodes {
    public static final int UNKNOWN_ERROR = -1;
    public static final int SUCCESS = 0;
    //  登录错误码
    public static final int EMPTY_NAME_ERROR = 1;
    public static final int PSW_ERROR = 2;
    //  添加广告错误码
    public static final int ADV_NAME_DUPLICATE = 3;
    public static final int USER_TAG_NOT_FOUND = 4;
    public static final int ADV_INFO_NOT_FOUND = 5;
    public static final int EMPTY_FILE = 6;
    public static final int FILE_NAME_ERROR = 7;
    public static final int DATE_ERROR = 8;
    public static final int ADV_INFO_ERROR = 9;
    //  获取广告错误码
    public static final int USER_ID_ERROR = 11;

    //常用错误码
    public static final int PARAM_ERROR = 20;
    public static final int DATABASE_ERROR = 21;
}
