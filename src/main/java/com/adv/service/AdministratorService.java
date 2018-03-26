package com.adv.service;

import com.adv.constants.LocalAddrs;
import com.adv.constants.ResultCodes;
import com.adv.constants.SessionAttribute;
import com.adv.dao.AdministratorDao;
import com.adv.pojo.Administrator;
import com.adv.pojo.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * @author lurongzhi
 */
@Service
public class AdministratorService {
    @Autowired
    private AdministratorDao administratorDao;

    public ResultObj<Administrator> checkAdministrator(Administrator uncheckedUser) throws Exception {
        if (uncheckedUser == null) {
            return new ResultObj<>(ResultCodes.EMPTY_NAME_ERROR, "登录信息不能为空");
        } else {
            Administrator dbUser = administratorDao.getUser();
            if (dbUser == null) {
                throw new Exception("没有找到user数据");
            } else if (!dbUser.checkEqual(uncheckedUser)) {
                return new ResultObj<>(ResultCodes.PSW_ERROR, "用户密码错误");
            } else {  //密码正确
                dbUser.setNextUrl(LocalAddrs.HOME);
                return new ResultObj<>(ResultCodes.SUCCESS, dbUser);
            }
        }
    }

    public Boolean checkLogin(HttpSession session) {
        Administrator administrator = (Administrator) session.getAttribute(SessionAttribute.LOGIN_INFO);
        return administrator != null;
    }

    public void removeAll(HttpSession session){
        Enumeration<String> attributeNames = session.getAttributeNames();
        while(attributeNames.hasMoreElements()){
            session.removeAttribute(attributeNames.nextElement());
        }
    }


}