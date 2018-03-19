package com.adv.service;

import com.adv.pojo.AdvObj;
import com.adv.pojo.ResultObj;

/**
 * @author lurongzhi
 */
public interface IAdvService {
    ResultObj<Void> uploadAdv();
    ResultObj<Void> updateAdv(AdvObj advObj);
    ResultObj<AdvObj> getAdv();
}
