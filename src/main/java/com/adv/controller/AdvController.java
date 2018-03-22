package com.adv.controller;

import com.adv.constants.ResultCodes;
import com.adv.constants.SessionStr;
import com.adv.pojo.AdvObj;
import com.adv.pojo.ResultObj;
import com.adv.service.AdvService;
import com.adv.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author lurongzhi
 */
@Controller
@RequestMapping("/advAction")
public class AdvController {
    @Autowired
    private AdvService advService;


    /**
     * 添加广告信息接口
     * 整个添加广告流程为：前端发送信息 -> 后端检验信息 -> 前端上传文件 -> 后端检验文件合法性 -> 保存到数据库
     *
     * @return 返回json
     */
    @RequestMapping(value = "/addInfo", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String addAdv(HttpServletRequest request, HttpServletResponse response, AdvObj advObj, HttpSession session) {
        ResultObj resultObj = advService.checkAdvInfo(advObj);
        if (resultObj.getCode() == ResultCodes.SUCCESS) {
            session.setAttribute(SessionStr.ADV_INFO, advObj);
        }
        return GsonUtils.toJson(resultObj);
    }

    /**
     * 上传广告文件接口
     *
     * @return 返回json形式的ResultObj
     */
    @RequestMapping(value = "/uploadAdvFile"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody
    public String uploadAdvFile(@RequestParam(required = false) MultipartFile file, HttpSession session) {
        AdvObj advObj = (AdvObj) session.getAttribute(SessionStr.ADV_INFO);
        ResultObj resultObj = advService.checkAdvFile(advObj, file);
        if (resultObj.getCode() != ResultCodes.SUCCESS) {
            session.removeAttribute(SessionStr.ADV_INFO);
        } else {
            session.setAttribute(SessionStr.ADV_FILE, file);
        }
        return GsonUtils.toJson(resultObj);
    }

    @RequestMapping(value = "/addAdv", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String addAdv(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        AdvObj advObj = (AdvObj) session.getAttribute(SessionStr.ADV_INFO);
        MultipartFile advFile = (MultipartFile) session.getAttribute(SessionStr.ADV_FILE);
        ResultObj<Void> resultObj = advService.addAdv(advObj, advFile);
        session.removeAttribute(SessionStr.ADV_INFO);
        session.removeAttribute(SessionStr.ADV_FILE);
        return GsonUtils.toJson(resultObj);
    }

    private ResultObj<String> checkUserTag(AdvObj advObj) {
        return new ResultObj<String>(ResultCodes.USER_TAG_NOT_FOUND, "", "用户标签xxx不存在");
    }

    private boolean checkName(AdvObj advObj) {
        return false;
    }
}
