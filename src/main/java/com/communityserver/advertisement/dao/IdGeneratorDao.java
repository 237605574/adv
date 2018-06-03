package com.communityserver.advertisement.dao;


import com.communityserver.advertisement.entity.IdGenObj;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author lurongzhi
 */


@Repository
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
