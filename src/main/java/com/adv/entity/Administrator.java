package com.adv.entity;

/**
 * @author lurongzhi
 */
public class Administrator {
    private String name;
    private String password;
    private Long id;
    private String nextUrl;



    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkEqual(Administrator user) {
        return this.name.equals(user.name) && this.password.equals(user.password);
    }
}
