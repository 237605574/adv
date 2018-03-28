package com.adv.service;

import com.adv.Utils;
import com.adv.dao.DaoFacade;
import com.adv.dao.FileDao;
import com.adv.dao.IdGeneratorDao;
import com.adv.pojo.AdvObj;
import com.adv.pojo.ResultObj;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author lurongzhi
 */
@RunWith(SpringJUnit4ClassRunner.class) //spring的单元测试
@ContextConfiguration({"classpath:spring/spring-*.xml"})    //上下文配置
@WebAppConfiguration("src/main/resources")
public class AdvServiceTest {
    @Autowired
    private AdvService advService;
    @Autowired
    private IdGeneratorDao idGeneratorDao;

    private FileDao fileDao = FileDao.getInstance();
    @Before
    public void init() {
        DaoFacade.getInstance().init(idGeneratorDao);
        System.out.println("init ");
    }

    @Test
    public void addAdvTest() throws IOException {
        MultipartFile advFile;
        File tempFile = new File("C:\\Users\\admin\\Desktop\\新建文件夹\\test.txt");
        InputStream inputStream = new FileInputStream(tempFile);
        advFile = new MockMultipartFile("test file", tempFile.getName(), null, inputStream);
        String fileNamePattern = "(.*((gif)|(jpg)|(jpeg)|(txt)))";
        boolean isMatch = "gif".matches(fileNamePattern);
        System.out.println(isMatch);
//        advFile.getOriginalFilename()
        AdvObj advObj = new AdvObj();
        advObj.setName("test adv obj");
        advObj.addTag(5L);
        ResultObj resultObj = advService.addAdv(advObj, advFile);
//        Assert.assertTrue(resultObj.isSuccess());
//        Assert.assertTrue(true);
        System.out.println(resultObj.getCode());
        System.out.println(resultObj.getMsg());
        System.out.println(advObj.getFileUrl());
        ResultObj<File> fileResultObj;
        System.out.println("========get 1 ==========");
        fileResultObj = advService.getFile(advObj.getFileUrl());
        Utils.printFileResult(fileResultObj);
        System.out.println("========get 2 ==========");
        fileResultObj = advService.getFile(advObj.getFileUrl());
        Utils.printFileResult(fileResultObj);
        System.out.println("=====del=====");
        fileDao.deleteFile(advObj.getFileUrl());
        fileResultObj = advService.getFile(advObj.getFileUrl());
        Utils.printFileResult(fileResultObj);

    }



}
