package com.adv.dao;

import com.adv.pojo.User;

/**
 * @author lurongzhi
 */
public interface UserDao {
    int update(User user);
    User getUser();
}
