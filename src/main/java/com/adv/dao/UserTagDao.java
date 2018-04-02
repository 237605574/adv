package com.adv.dao;

import com.adv.pojo.AdvObj;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lurongzhi
 */
public interface UserTagDao {
    List<Long> getUserByTags(List<Long> tagIds);
}
