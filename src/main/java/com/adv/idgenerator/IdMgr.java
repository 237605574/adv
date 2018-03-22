package com.adv.idgenerator;


import com.adv.dao.DaoMgr;
import com.adv.dao.IdGeneratorDao;
import com.adv.pojo.IdGenObj;

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
        Init();
    }

    private static class SingletonHolder {
        private static final IdMgr instance = new IdMgr();
    }

    private void Init() {
        IdGeneratorDao idgenDao = DaoMgr.getInstance().GetIdGenDao();
        List<IdGenObj> idGenPojos = idgenDao.findAll();
        System.out.println("-------id gen-------------------");
        for (IdGenObj idGenPojo : idGenPojos) {
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
