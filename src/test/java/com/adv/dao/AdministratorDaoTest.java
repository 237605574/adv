package com.adv.dao;

import com.adv.pojo.Administrator;
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
@WebAppConfiguration("src/main/resources")
public class AdministratorDaoTest {
    @Autowired
    private AdministratorDao administratorDao;

    @Test
    public void testFind() {
        Administrator administrator = administratorDao.getUser();
        System.out.println("-----------test start------------------\n" +
                administrator.getName() +
                administrator.getPassword() +
                "\n-----------test start------------------");
    }
}
