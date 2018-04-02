package com.adv.dao;

import com.adv.pojo.AdvObj;
import com.adv.pojo.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author lurongzhi
 */
public interface TagDao {
    int addTag(@Param("tag") Tag tag) throws DataAccessException;

    int addTagBatch(@Param("tagList") List<Tag> tags);

    int delTagById(@Param("id") Long id);

    int delTagByIdBatch(@Param("idList") List<Long> ids);

    int checkTagByAdv(@Param("advObj") AdvObj advObj);

    List<Tag> getAllTags();
}
