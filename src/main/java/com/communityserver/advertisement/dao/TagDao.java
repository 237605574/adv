package com.communityserver.advertisement.dao;

import com.communityserver.advertisement.entity.AdvObj;
import com.communityserver.advertisement.entity.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lurongzhi
 */

@Repository
public interface TagDao {
    int addTag(@Param("tag") Tag tag) throws DataAccessException;

    int addTagBatch(@Param("tagList") List<Tag> tags);

    int delTagById(@Param("id") Long id);

    int delTagByIdBatch(@Param("idList") List<Long> ids);

    int checkTagByAdv(@Param("advObj") AdvObj advObj);

    List<Tag> getAllTags();
}
