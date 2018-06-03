package com.communityserver.advertisement.controller;

import com.communityserver.advertisement.entity.Administrator;
import com.communityserver.advertisement.service.AdministratorService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;

/**
 * @author lurongzhi
 */

@RunWith(SpringJUnit4ClassRunner.class)     //调用Spring单元测试类
@WebAppConfiguration("src/main/resources")   //调用Java Web组件，如自动注入ServletContext Bean等
@ContextConfiguration({"classpath:spring/applicationContext.xml"})    //上下文配置
public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private UserController userController;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void checkLogout() throws Exception {
        HttpSession session = request.getSession();
        Administrator administrator = new Administrator();
        administrator.setId(0L);
        administrator.setName("123");
        administrator.setPassword("123");
        userController.login(request, response, administrator, session);
        Assert.assertTrue(administratorService.checkLogin(session));
        userController.logout(request,response,session);
        Assert.assertTrue(!administratorService.checkLogin(session));
        userController.login(request, response, administrator, session);
        Assert.assertTrue(administratorService.checkLogin(session));
        userController.logout(request,response,session);
        Assert.assertTrue(!administratorService.checkLogin(session));
    }

}
