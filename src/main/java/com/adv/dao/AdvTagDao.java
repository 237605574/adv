package com.adv.dao;

import com.adv.pojo.AdvObj;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lurongzhi
 * <p>
 * 对应adv_tag
 */
public interface AdvTagDao {
    int addTag(@Param("advObj") AdvObj advObj);

    int delTagBatch(@Param("tagIds") List<Long> tagsIds);

    List<Long> getTag(@Param("advId")Long advId);
}
