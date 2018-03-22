package com.adv.dao;

import com.adv.pojo.AdvObj;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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
public class AdvDaoTest {
    @Autowired
    private AdvDao advDao;

    @Test
    public void addAdvTest() {
        AdvObj advObj = new AdvObj();
        advObj.setId(66L);
        advObj.setName("hahahahah");
        int result = advDao.addAdv(advObj);
        System.out.println("-------------test-------------\n" +
                result +
                "\n-------------test-------------");
    }
}
