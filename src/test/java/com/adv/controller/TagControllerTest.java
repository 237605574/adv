package com.adv.controller;

import com.adv.entity.Administrator;
import com.adv.service.TagService;
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

import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 */

@RunWith(SpringJUnit4ClassRunner.class)     //调用Spring单元测试类
@WebAppConfiguration("src/main/resources")   //调用Java Web组件，如自动注入ServletContext Bean等
@ContextConfiguration({"classpath:spring/spring-*.xml"})
public class TagControllerTest {

    @Autowired
    private TagService tagService;

    @Autowired
    private TagController tagController;

    @Autowired
    private UserController userController;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        HttpSession session = request.getSession();
        Administrator administrator = new Administrator();
        administrator.setId(0L);
        administrator.setName("123");
        administrator.setPassword("123");
        userController.login(request, response, administrator, session);
    }

    @Test
    public void checkLogout() throws Exception {
        String result = tagController.getAllUserTags(request, response, request.getSession());
        System.out.println(result);
    }
}
