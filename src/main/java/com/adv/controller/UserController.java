package com.adv.controller;

import com.adv.constants.SessionAttribute;
import com.adv.pojo.Administrator;
import com.adv.pojo.ResultObj;
import com.adv.service.AdministratorService;
import com.adv.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author lurongzhi
 */
@Controller
@RequestMapping("/userAction")
public class UserController {
    @Autowired
    private AdministratorService administratorService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response, Administrator uncheckedUser, HttpSession session) throws Exception {
        ResultObj<Administrator> resultObj = administratorService.checkAdministrator(uncheckedUser);
        if (resultObj.isSuccess()) {
            Administrator dbUser = resultObj.getData();
            session.setAttribute(SessionAttribute.LOGIN_INFO, dbUser);
        }
        return GsonUtils.toJson(resultObj);
    }
    /**
     *  管理员退出整个系统的时候要调用下列接口，把服务器的session去掉
     *  否则，切换账号的时候回出现问题
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        administratorService.removeAll(session);
        return "";
    }


}
