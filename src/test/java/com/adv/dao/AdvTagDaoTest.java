package com.adv.dao;

import com.adv.entity.AdvObj;
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
public class AdvTagDaoTest {
    @Autowired
    private AdvTagDao advTagDao;

    @Test
    public void addTagTest() {
        AdvObj advObj = new AdvObj();
        Long advId = 4444L;
        List<Long> tagIds = new ArrayList<>();
//        tagIds.add(7456123L);
//        tagIds.add(7456L);
        advObj.setId(advId);
        advObj.setUserTagIds(tagIds);
        int addResult = advTagDao.addTag(advObj);
        System.out.println("--------------------test----------------");
        System.out.println("result:" + addResult);
    }
    @Test
    public void getTag(){
        Long advId = 11204L;
        List<Long> tagList = advTagDao.getTag(advId);
        System.out.println(tagList);
    }
}
