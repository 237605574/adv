package com.adv.dao;

import com.adv.pojo.AdvObj;
import org.apache.ibatis.annotations.Param;

/**
 * @author lurongzhi
 * 对应adv表格的dao
 */
public interface AdvDao {
    int addAdv(@Param("advObj")AdvObj advObj);
    int checkNameCount(@Param("advObj")AdvObj advObj);
}
