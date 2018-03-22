package com.adv.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;

/**
 * @author lurongzhi
 */
//@Service
public class DaoMgr {
    @Autowired
    private IdGeneratorDao idGeneratorDao;

    public static DaoMgr getInstance() {
        return SingletonHolder.instance;
    }

    private DaoMgr() {
    }

    public void init(ServletContextEvent sce) {
        idGeneratorDao = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext()).getBean(IdGeneratorDao.class);
    }

    //  测试用的init
    public void init(IdGeneratorDao idGeneratorDao) {
        this.idGeneratorDao = idGeneratorDao;
    }

    private static class SingletonHolder {
        private static final DaoMgr instance = new DaoMgr();
    }

    public IdGeneratorDao GetIdGenDao() {
        return idGeneratorDao;
    }
}
