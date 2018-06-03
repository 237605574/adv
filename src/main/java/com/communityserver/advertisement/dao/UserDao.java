package com.communityserver.advertisement.dao;

import com.communityserver.advertisement.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author lurongzhi
 */


@Repository
public interface UserDao {
    int update(User user);
    User getUser();
}
