package com.adv.dao;


import com.adv.entity.AdvObj;
import com.adv.entity.User;
import com.adv.common.utils.AdvUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public void addAdvTest() throws ParseException {
        AdvObj advObj = new AdvObj();
        advObj.setId(66L);
        advObj.setName("hahahahah");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        String dateStr = "0200-01-01T07:11";
        Date date = sdf.parse(dateStr);
        System.out.println(date);
        advObj.setStartDate(date);
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
        AdvUtils.printAdvList(advObjList);
        System.out.println("-------------test--------------");
    }



    @Test
    public void testUpadateState(){
        int result = advDao.updateState();
        System.out.println(result);
    }

    @Test
    public void queryAdvTest() throws ParseException {
        AdvObj advObj = new AdvObj();
//        advObj.setName("1");
//        advObj.addTag(5L);
//        advObj.addTag(2L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateStr = "2222-04-01 01:01";
        Date date = sdf.parse(dateStr);
//        advObj.setStartDate(date);
//        advObj.setHomepage("");
        AdvUtils.printAdv(advObj);
        int offset = 0;
        int limit = 10;
        System.out.println("---------------------query---------------------");
        List<AdvObj> advObjs = advDao.queryAdv(advObj,offset,limit);
        AdvUtils.printAdvList(advObjs);
    }

    @Test
    public void testCountID(){
        int result = advDao.checkIdCount(19002L);
        System.out.println(result);
    }

    @Test
    public void testUpdateAdv(){
        AdvObj advObj = advDao.getAdv(2L);
        advObj.setName("测试修改");
        advDao.updateAdv(advObj);
    }
}
