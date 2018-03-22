package com.adv.service;

import com.adv.dao.AdministratorDao;
import com.adv.pojo.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lurongzhi
 */
@Service
public class UserService {
    @Autowired
    private AdministratorDao userDao;

    public Administrator getUser() {
        System.out.println("2222222222222\n" + userDao);
        return userDao.getUser();
    }


}
