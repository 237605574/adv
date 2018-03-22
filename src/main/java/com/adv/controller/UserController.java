package com.adv.controller;

import com.adv.constants.LocalAddrs;
import com.adv.constants.ResultCodes;
import com.adv.pojo.ResultObj;
import com.adv.pojo.Administrator;
import com.adv.service.UserService;
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
    private UserService userService ;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String CheckLogin(HttpServletRequest request, HttpServletResponse response, Administrator uncheckedUser, HttpSession session) throws Exception {
        String resultJson = "";
        if (uncheckedUser == null) {
            ResultObj<Void> resultObj = new ResultObj<>(ResultCodes.EMPTY_NAME_ERROR, "登录信息不能为空");
            resultJson = GsonUtils.toJson(resultObj);
            return resultJson;
        } else {
            Administrator dbUser = userService.getUser();
            if (dbUser == null) {
                throw new Exception("没有找到user数据");
            } else if (!dbUser.checkEqual(uncheckedUser)) {
                ResultObj<Void> resultObj = new ResultObj<>(ResultCodes.PSW_ERROR, "用户密码错误");
                resultJson = GsonUtils.toJson(resultObj);
                return resultJson;
            } else {  //密码正确
                session.setAttribute("userInfo", dbUser);
                dbUser.setNextUrl(LocalAddrs.HOME);
                ResultObj<Administrator> resultObj = new ResultObj<>(ResultCodes.SUCCESS, dbUser);
                resultJson = GsonUtils.toJson(resultObj);
                return resultJson;
            }
        }
    }

}
