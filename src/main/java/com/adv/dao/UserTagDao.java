package com.adv.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lurongzhi
 */
public interface UserTagDao {
    List<Long> getUserByTags(List<Long> tagIds);

    int addTags(@Param("userId") Long userId, @Param("tagIds") List<Long> tagIds);
}
