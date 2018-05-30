package com.adv.dao;

import com.adv.entity.AdvObj;
import com.adv.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lurongzhi
 * 对应adv表格的dao
 */
public interface AdvDao {
    /**
     * 该方法只对adv表格操作，而保存广告数据的还有一个一对多的表格adv_tag
     * 所以在调用这个方法之后，要再调用AdvTagDao.addTag()
     */
    int addAdv(@Param("advObj") AdvObj advObj);

    /**
     * 如果advObj id 不为空，则查询除了该id之外是否还存在同名
     * 则该语句可用于检查新添加的广告信息以及用于更新信息的广告
     */
    int checkNameCount(@Param("advObj") AdvObj advObj);

    int updateAdv(@Param("advObj") AdvObj advObj);

    AdvObj getAdv(@Param("advId") Long advId);

    int deleteAdv(@Param("advId") Long advId);

    List<AdvObj> getAdvListByUser(@Param("user") User user);

    int updateStateInvalid();

    int updateStateValid();

    List<AdvObj> queryAdv(@Param("advObj") AdvObj advObj, @Param("offset") int offset, @Param("limit") int limit);

    int checkIdCount(@Param("advId")Long id);

    List<AdvObj> getAllValidAdv();

}
