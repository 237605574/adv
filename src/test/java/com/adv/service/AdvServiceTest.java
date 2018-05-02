package com.adv.service;

import com.adv.dao.FileDao;
import com.adv.dao.IdGeneratorDao;
import com.adv.common.idgenerator.IdMgr;
import com.adv.entity.AdvObj;
import com.adv.entity.ResultObj;
import com.adv.common.utils.AdvUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
        System.out.println("init ");
    }

    @Test
    public void addAdvTest() throws IOException {
        MultipartFile advFile;
//        File tempFile = new File("C:\\Users\\admin\\Desktop\\新建文件夹\\test.txt");
        File tempFile = new File("C:\\Users\\Administrator\\Desktop\\新建文件夹\\test.txt");
        InputStream inputStream = new FileInputStream(tempFile);
        advFile = new MockMultipartFile("test file", tempFile.getName(), null, inputStream);
        String fileNamePattern = "(.*((gif)|(jpg)|(jpeg)|(txt)))";
        boolean isMatch = "gif".matches(fileNamePattern);
        System.out.println(isMatch);
//        advFile.getOriginalFilename()
        AdvObj advObj = new AdvObj();
        advObj.setName(IdMgr.getInstance().genAdvId().toString());
        advObj.addTag(5L);
        advObj.setStartDate(new Date());
        advObj.setEndDate(new Date());
        advObj.setHomepage("  sdas");
        advObj.setDisplayDetail("dsadasd");
        ResultObj resultObj = advService.addAdv(advObj, advFile);
//        Assert.assertTrue(resultObj.isSuccess());
//        Assert.assertTrue(true);
        System.out.println(resultObj.getCode());
        System.out.println(resultObj.getMsg());
        System.out.println(advObj.getFileUrl());
        ResultObj<File> fileResultObj;
        System.out.println("========get 1 ==========");
        fileResultObj = advService.getFile(advObj.getFileUrl());
        AdvUtils.printFileResult(fileResultObj);
        System.out.println("========get 2 ==========");
        fileResultObj = advService.getFile(advObj.getFileUrl());
        AdvUtils.printFileResult(fileResultObj);
        System.out.println("=====del=====");
        fileDao.deleteFile(advObj.getFileUrl());
        fileResultObj = advService.getFile(advObj.getFileUrl());
        AdvUtils.printFileResult(fileResultObj);

    }

    @Test
    public void testQuery() throws ParseException {
        AdvObj advObj = new AdvObj();
//        advObj.setName("");
//        advObj.addTag(5L);
//        advObj.addTag(2L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateStr = "2222-01-13 01:01";
        Date date = sdf.parse(dateStr);
        advObj.setStartDate(date);
        advObj.setHomepage("");
//        advObj.setType(1);
        AdvUtils.printAdv(advObj);
        int offset = 0;
        int limit = 10;
        System.out.println("---------------------query---------------------");
        ResultObj<List<AdvObj>> resultObj = advService.queryAdv(advObj, offset, limit);
        if (resultObj.isSuccess()) {
            AdvUtils.printListResult(resultObj);
        }
    }
}
