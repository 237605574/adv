package com.communityserver.advertisement.entity;

import com.communityserver.advertisement.common.constants.ResultCodes;

import java.io.*;

/**
 * @author lurongzhi
 */
public class ResultObj<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public ResultObj(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultObj(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResultObj(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultObj(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return this.code == ResultCodes.SUCCESS;
    }

    public byte[] toBytes(){
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(bo);
            oos.writeObject(this);
            oos.flush();
            oos.close();
            bo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bo.toByteArray();
    }
    public static ResultObj toResultObj(byte[] bytes){
        ResultObj resultObj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream (bytes);
            ObjectInputStream ois = new ObjectInputStream (bis);
            resultObj = (ResultObj) ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return resultObj;
    }
}
