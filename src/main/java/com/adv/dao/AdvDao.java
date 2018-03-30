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
    /**
     *  该方法只对adv表格操作，而保存广告数据的还有一个一对多的表格adv_tag
     *  所以在调用这个方法之后，要再调用AdvTagDao.addTag()
     */
    int addAdv(@Param("advObj") AdvObj advObj);

    int checkNameCount(@Param("advObj") AdvObj advObj);

    int updateAdv(@Param("advObj") AdvObj advObj);

    AdvObj getAdv(@Param("advId") Long advId);

    int deleteAdv(@Param("advId") Long advId);

    List<AdvObj> getAdvListByUser(@Param("user") User user);

    int updateState();
}
