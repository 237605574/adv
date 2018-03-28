package com.adv.dao;

import com.adv.pojo.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lurongzhi
 */
public interface TagDao {
    int addTag(@Param("tag") Tag tag);

    int addTagBatch(@Param("tagList") List<Tag> tags);

    int delTagById(@Param("id") Long id);

    int delTagByIdBatch(@Param("idList") List<Long> ids);

}
