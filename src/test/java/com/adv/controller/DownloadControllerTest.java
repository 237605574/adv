package com.adv.controller;

import com.adv.pojo.User;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author lurongzhi
 */

@RunWith(SpringJUnit4ClassRunner.class)     //调用Spring单元测试类
@WebAppConfiguration("src/main/resources")   //调用Java Web组件，如自动注入ServletContext Bean等
@ContextConfiguration({"classpath:spring/spring-*.xml"})    //上下文配置
//@SpringBootTest(classes = Application.class)
public class DownloadControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private DownloadController downloadController;

    private MockMvc mockMvc;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void testGetAdvList() throws Exception {
        String url = "/download/getAdvList";
        String NO_INTERCEPTOR_PATH = ".*/((login)|(download)|(logout)|(code)|(app)|(weixin)|(static)|(main)|(websocket)).*";
        System.out.println(url.matches(NO_INTERCEPTOR_PATH));
        System.out.println("-------------------match---------------");
        User user = new User();
        user.setId(44L);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url,user)
        .param("id","44"))
                .andDo(print())
//                .andExpect(status().isOk())
                .andReturn();
        System.out.println("----------------------------------------------");
        System.out.println(result.getResponse().getContentAsString());
        System.out.println("----------------------------------------------");
    }

    @Test
    public void getFileTest() throws Exception {
        String url = "/download/getAdvFile";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .param("fileName","19502.jpg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
//        OutputStream outputStream = result.getResponse().get
        result.getResponse().setCharacterEncoding("utf-8");
        result.getResponse().setCharacterEncoding("utf-8");
        result.getResponse().setContentType("text/html;charset=UTF-8");
//        System.out.println("----------------------------------------------");
//        System.out.println(result.getResponse().getContentType());
//        System.out.println(result.getResponse().getContentAsString());
////        File file = new File(result.getResponse().getContentAsByteArray());
//        System.out.println("----------------------------------------------");

    }
    @Test
    public void simpleGetAdvList(){
        HttpSession session = request.getSession();
        User user = new User();
        user.setId(44L);
        String result = downloadController.getAdvList(request,response,user,session);
        System.out.println(result);

    }
}
