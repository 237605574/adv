package com.communityserver.advertisement.dao;

import com.communityserver.advertisement.entity.AdvObj;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lurongzhi
 * <p>
 * 对应adv_tag
 */


@Repository
public interface AdvTagDao {
    int addTag(@Param("advObj") AdvObj advObj);

    int delTagBatch(@Param("tagIds") List<Long> tagsIds);

    List<Long> getTag(@Param("advId")Long advId);
}
