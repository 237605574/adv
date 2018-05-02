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
/**
 * @WebAppConfiguration注解在类上，
 * 用来声明加载的ApplicationContext是一个WebApplicationContext。
 * 它的属性指定的是Web资源的位置，默认为src/main/webapp
 */
@WebAppConfiguration("src/main/resources")
public class UserAdvDaoTest {
    @Autowired
    private UserAdvDao userAdvDao;

    @Test
    public void addAdvTest() {
        List<Long> tagIds = new ArrayList<>();
//        tagIds.add(1L);
//        tagIds.add(4L);
        Long advId = 1L;
//        int result = userAdvDao.addAdvByTag(advId, tagIds);
        AdvObj advObj = new AdvObj();
        advObj.setId(advId);
        advObj.addTag(1L);
        Boolean test ;
        int result = userAdvDao.addAdvByTag(advObj);
        System.out.println("-------------------test----------------\n" +
                result +
                "\n-------------------test----------------");
    }
    @Test
    public void testUpdateState(){
        int result = userAdvDao.updateAdvState();
        System.out.println(result);
    }
}
