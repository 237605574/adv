package com.adv.service;

import com.adv.dao.UserDao;
import com.adv.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lurongzhi
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getUser() {
        System.out.println("2222222222222\n"+userDao);
        return userDao.getUser();
    }


}
