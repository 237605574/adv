package com.communityserver.advertisement.common.idgenerator;


import com.communityserver.advertisement.common.init.Init;
import com.communityserver.advertisement.dao.IdGeneratorDao;
import com.communityserver.advertisement.entity.IdGenObj;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lurongzhi
 */
public class IdMgr {
    private  final String ADV_ID = "adv";
    private ConcurrentHashMap<String, Generator> generatorMap = new ConcurrentHashMap<>();

    public static IdMgr getInstance() {
        return SingletonHolder.instance;
    }

    private IdMgr() {
    }

    private static class SingletonHolder {
        private static final IdMgr instance = new IdMgr();
    }

    public void init() {
        IdGeneratorDao idgenDao = Init.getBean(IdGeneratorDao.class);
        List<IdGenObj> idGenObjs = idgenDao.findAll();
        System.out.println("-------id gen-------------------");
        for (IdGenObj idGenPojo : idGenObjs) {
            System.out.println(idGenPojo.getName() + " " +
                    idGenPojo.getCurrentId() + " " +
                    idGenPojo.getStep());
            Generator generator = new Generator(idGenPojo);
            this.generatorMap.put(idGenPojo.getName(),generator);
        }
        System.out.println("-------id gen-------------------");

    }

    private Long genId(String key) {
        Generator generator = generatorMap.get(key);
        if (generator == null) {
            return -1L;
        }
        return generator.genId();
    }

    public Long genAdvId(){
        return genId(ADV_ID);
    }
}
