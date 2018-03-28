package com.adv.dao;

import com.adv.pojo.AdvObj;
import org.apache.ibatis.annotations.Param;

/**
 * @author lurongzhi
 * <p>
 * 对应adv_tag
 */
public interface AdvTagDao {
    int addTag(@Param("advObj") AdvObj advObj);
}
