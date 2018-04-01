package com.adv.service;

import com.adv.constants.ResultCodes;
import com.adv.dao.TagDao;
import com.adv.pojo.ResultObj;
import com.adv.pojo.Tag;
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

    public ResultObj<List<Tag>> getAllTags() {
        List<Tag> tags = tagDao.getAllTags();
        if (tags == null) {
            return new ResultObj<>(ResultCodes.DATABASE_ERROR, "查询用户标签错误");
        }
        return new ResultObj<>(ResultCodes.SUCCESS, tags);
    }
}
