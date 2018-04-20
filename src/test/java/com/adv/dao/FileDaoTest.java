package com.adv.dao;

import com.adv.constants.FilePaths;
import com.adv.utils.AdvUtils;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * @author Administrator
 */
@RunWith(SpringJUnit4ClassRunner.class) //spring的单元测试
@ContextConfiguration({"classpath:spring/spring-*.xml"})    //上下文配置
/**
 * @WebAppConfiguration注解在类上，
 * 用来声明加载的ApplicationContext是一个WebApplicationContext。
 * 它的属性指定的是Web资源的位置，默认为src/main/webapp
 */
@WebAppConfiguration("src/main/resources")
public class FileDaoTest {
    @Test
    public void testFileName() {
        String savePath = FilePaths.ADV_PATH + "/" + "a.txt";
        File saveFile = new File(savePath);
        File parentFile = saveFile.getParentFile();
        System.out.println(saveFile.getName());
    }

    @Test
    public void testCache() throws ExecutionException {
        final Integer[] cnt = {0};
        LoadingCache<String, Integer> cache = CacheBuilder.newBuilder().build(
                new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String s) throws Exception {
                        cnt[0] += 1;
                        return cnt[0];
                    }
                }
        );
        String testKey = "test";
        Integer expectedN  = 1;
        Assert.assertEquals(expectedN,cache.get(testKey));
        Assert.assertEquals(expectedN,cache.get(testKey));
        cache.invalidate(testKey);
        expectedN+=1;
        Assert.assertEquals(expectedN,cache.get(testKey));

    }
    @Test
    public void testGetFile() throws IOException {
        FileDao fileDao = FileDao.getInstance();
//        fileDao.getFile("1002.txt");
        AdvUtils.printFileResult(fileDao.getFile("19003.png"));
    }

    @Test
    public void testSaveFile(){

    }

}
