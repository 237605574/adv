package com.communityserver.advertisement.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lurongzhi
 */


@Repository
public interface UserTagDao {
    List<Long> getUserByTags(List<Long> tagIds);

    int addTags(@Param("userId") Long userId, @Param("tagIds") List<Long> tagIds);
}
