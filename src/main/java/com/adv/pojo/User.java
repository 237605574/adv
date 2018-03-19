package com.adv.pojo;

/**
 * @author lurongzhi
 */
public class User {
    private String name;
    private String password;
    private Integer id;
    private String nextUrl;

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkEqual(User user) {
        return this.name.equals(user.name) && this.password.equals(user.password);
    }
}
