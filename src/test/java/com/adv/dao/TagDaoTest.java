package com.adv.dao;

import com.adv.pojo.AdvObj;
import com.adv.pojo.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lurongzhi
 */

@RunWith(SpringJUnit4ClassRunner.class) //spring的单元测试
@ContextConfiguration({"classpath:spring/spring-*.xml"})    //上下文配置
@WebAppConfiguration("src/main/resources")
public class TagDaoTest {
    @Autowired
    private TagDao tagDao;
    @Test
    public void testAdd(){
        Tag tag = new Tag();
        tag.setId(111L);
        tag.setName("dasda");
        int result = tagDao.addTag(tag);
        System.out.println(result);
    }

    @Test
    public void addTagBatch(){
        List<Tag> tags = new ArrayList<>();
        for(int i =0;i<10;i++){
            Tag tag = new Tag();
            Long id = (long) (i + 9999);
            tag.setId(id);
            tag.setName(String.valueOf(id));
            tags.add(tag);
        }
        tagDao.addTagBatch(tags);
    }
    @Test
    public void checkTag(){
        AdvObj advObj = new AdvObj();
        List<Long> tags = new ArrayList<>();
        tags.add(5L);
        tags.add(6L);
        advObj.setUserTagIds(tags);
        int result = tagDao.checkTagByAdv(advObj);

        System.out.println(result);
    }

}
