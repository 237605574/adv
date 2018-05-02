package com.adv.dao;


import com.adv.entity.IdGenObj;

import java.io.Serializable;
import java.util.List;

/**
 * @author lurongzhi
 */
public interface IdGeneratorDao extends Dao<IdGenObj> {
    @Override
    int add(IdGenObj idGenPojo);

    @Override
    int del(IdGenObj idGenPojo);

    @Override
    int update(IdGenObj idGenPojo);

    @Override
    IdGenObj findOneById(Serializable Id);

    @Override
    List<IdGenObj> findAll();
}
