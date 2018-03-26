package com.adv.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;

/**
 * @author lurongzhi
 */
//@Service
public class DaoFacade {
    @Autowired
    private IdGeneratorDao idGeneratorDao;

    public static DaoFacade getInstance() {
        return SingletonHolder.instance;
    }

    private DaoFacade() {
    }

    public void init(ServletContextEvent sce) {
        idGeneratorDao = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext()).getBean(IdGeneratorDao.class);
    }

    //  测试用的init
    public void init(IdGeneratorDao idGeneratorDao) {
        this.idGeneratorDao = idGeneratorDao;
    }

    private static class SingletonHolder {
        private static final DaoFacade instance = new DaoFacade();
    }

    public IdGeneratorDao GetIdGenDao() {
        return idGeneratorDao;
    }
}
