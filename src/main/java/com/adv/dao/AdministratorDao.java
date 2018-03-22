package com.adv.dao;

import com.adv.pojo.Administrator;

/**
 * @author lurongzhi
 */
public interface AdministratorDao {
    int update(Administrator user);
    Administrator getUser();
}
