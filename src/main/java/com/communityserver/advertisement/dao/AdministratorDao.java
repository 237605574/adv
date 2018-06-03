package com.communityserver.advertisement.dao;

import com.communityserver.advertisement.entity.Administrator;
import org.springframework.stereotype.Repository;

/**
 * @author lurongzhi
 */

@Repository
public interface AdministratorDao {
    int update(Administrator user);
    Administrator getUser();
}
