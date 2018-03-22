package com.adv.dao;

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
/**
 * @WebAppConfiguration注解在类上，
 * 用来声明加载的ApplicationContext是一个WebApplicationContext。
 * 它的属性指定的是Web资源的位置，默认为src/main/webapp
 */
@WebAppConfiguration("src/main/resources")
public class UserTagDaoTest {
    @Autowired
    private UserTagDao userTagDao;

    @Test
    public void getUserByTags() {
        List<Long> tagIds = new ArrayList<>();
        tagIds.add(1L);
        List<Long> users = userTagDao.getUserByTags(tagIds);
        System.out.println("------------test start-----------\n" +
                users +
                "\n-------------test end------------");
    }
}
