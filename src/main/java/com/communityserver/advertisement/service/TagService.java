package com.communityserver.advertisement.service;

import com.communityserver.advertisement.common.constants.ResultCodes;
import com.communityserver.advertisement.dao.AdvTagDao;
import com.communityserver.advertisement.dao.TagDao;
import com.communityserver.advertisement.entity.ResultObj;
import com.communityserver.advertisement.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class TagService {
    @Autowired
    private TagDao tagDao;

    @Autowired
    private AdvTagDao advTagDao;
    public ResultObj<List<Tag>> getAllTags() {
        List<Tag> tags = tagDao.getAllTags();
        if (tags == null) {
            return new ResultObj<>(ResultCodes.DATABASE_ERROR, "查询用户标签错误");
        }
        return new ResultObj<>(ResultCodes.SUCCESS, tags);
    }

    public ResultObj<Void> addTagBatch(List<Tag> tagList) {
        if (tagList == null || tagList.size() == 0) {
            return new ResultObj<>(ResultCodes.PARAM_ERROR, "标签为空");
        }
        int result = tagDao.addTagBatch(tagList);
        if (result <= 0) {
            return new ResultObj<>(ResultCodes.DATABASE_ERROR, "插入失败，可能已经存在该用户标签了");
        }
        return new ResultObj<>(ResultCodes.SUCCESS);
    }

    public ResultObj<Void> delTagBatch(List<Long> tagIds) {
        if (tagIds == null || tagIds.size() == 0) {
            return new ResultObj<>(ResultCodes.PARAM_ERROR, "id为空");
        }
        int result = tagDao.delTagByIdBatch(tagIds);
        if (result <= 0) {
            return new ResultObj<>(ResultCodes.DATABASE_ERROR, "删除失败，可能数据库不存在该id");
        }
        //  更新广告信息


        //  todo 更新用户广告列表

        return new ResultObj<>(ResultCodes.SUCCESS);
    }

    public ResultObj<Void> addUserTag(Long userId,List<Long> tagId) {
        return null;
    }
}
