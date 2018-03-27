package com.adv.dao;

import com.adv.pojo.AdvObj;
import com.adv.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lurongzhi
 */
public interface UserAdvDao {
    int addAdv(@Param("advId") Long advId, List<User> userList);

    int addUser(@Param("userId") Long UserId, List<Long> advIds);
    /**
     *  通过advObj中的userTagId更新广告用户
     *  @param advObj :必须带有userTag和id
     */
    int addAdvByTag(@Param("advObj") AdvObj advObj);

    List<Long> getAdvIds(Long userId);

    int removeAdv(@Param("advObj")AdvObj advObj);
}
