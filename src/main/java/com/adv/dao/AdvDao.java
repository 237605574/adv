package com.adv.dao;

import com.adv.pojo.AdvObj;
import com.adv.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lurongzhi
 * 对应adv表格的dao
 */
public interface AdvDao {
    int addAdv(@Param("advObj") AdvObj advObj);

    int checkNameCount(@Param("advObj") AdvObj advObj);

    List<AdvObj> getAdvListByUserId(@Param("user") User user);
}
