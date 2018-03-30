package com.adv.dao;

import com.adv.pojo.AdvObj;
import com.adv.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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

    @Test
    public void getAdvListTest() {
        User user = new User();
        Long userID = 44L;
        user.setId(userID);
        List<AdvObj> advObjList = advDao.getAdvListByUser(user);
        System.out.println("-------------test--------------");
        System.out.println(advObjList);
        for (AdvObj advObj : advObjList) {
            System.out.println("id " + advObj.getId());
            System.out.println("name " + advObj.getName());
            System.out.println("file " + advObj.getFileUrl());
            System.out.println("type " + advObj.getType());
            System.out.println("isValid " + advObj.getValid());
            System.out.println("homepage " + advObj.getHomepage());
            System.out.println("displayDetail " + advObj.getDisplayDetail());
            System.out.println("**********************************");
        }
        System.out.println("-------------test--------------");
    }

    @Test
    public void testUpadateState(){
        int result = advDao.updateState();
        System.out.println(result);
    }
}
