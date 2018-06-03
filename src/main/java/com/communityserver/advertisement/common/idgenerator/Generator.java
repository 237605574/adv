package com.communityserver.advertisement.common.idgenerator;

import com.communityserver.advertisement.common.init.Init;
import com.communityserver.advertisement.dao.IdGeneratorDao;
import com.communityserver.advertisement.entity.IdGenObj;

/**
 * @author lurongzhi
 */
public class Generator {
    private IdGeneratorDao idGeneratorDao;
    private final IdGenObj idGenPojo;
    private long genId;

    public Generator(IdGenObj idGenPojo) {
        this.idGeneratorDao = Init.getBean(IdGeneratorDao.class);
        this.idGenPojo = idGenPojo;
        this.genId = idGenPojo.getCurrentId();
        idGenPojo.nextStep();
        this.idGeneratorDao.update(idGenPojo);

    }

    public Long genId() {
        synchronized (this.idGenPojo) {
            long curId = this.idGenPojo.getCurrentId();
            this.genId += 1;
            if (this.genId >= curId) {
                idGenPojo.nextStep();
                UpdateDb();
            }
            return this.genId;
        }
    }

    private void UpdateDb() {
        this.idGeneratorDao.update(idGenPojo);
    }
}
