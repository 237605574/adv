package com.communityserver.advertisement.controller;

import com.communityserver.advertisement.common.constants.ResultCodes;
import com.communityserver.advertisement.common.constants.SessionStr;
import com.communityserver.advertisement.common.utils.AdvUtils;
import com.communityserver.advertisement.common.utils.GsonUtils;
import com.communityserver.advertisement.entity.AdvObj;
import com.communityserver.advertisement.entity.ResultObj;
import com.communityserver.advertisement.service.AdvService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author lurongzhi
 */
@Controller
@RequestMapping("/advAction")
public class AdvController {
    @Resource
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
    public String addAdv(HttpServletRequest request, HttpServletResponse response, AdvObj advObj, @RequestParam("tags[]") List<Long> tags, HttpSession session) {
        // 测试
        if (tags != null) {
            advObj.setUserTagIds(tags);
            System.out.println(tags);
        }
        System.out.println(tags);
        AdvUtils.printAdv(advObj);
        ResultObj resultObj = advService.checkAdvInfo(advObj);
        if (resultObj.getCode() == ResultCodes.SUCCESS) {
            session.setAttribute(SessionStr.ADD_ADV_INFO, advObj);
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
        System.out.println("get file");
        AdvObj advObj = (AdvObj) session.getAttribute(SessionStr.ADD_ADV_INFO);
        ResultObj resultObj = advService.checkAdvFile(advObj, file);
        if (resultObj.getCode() != ResultCodes.SUCCESS) {
            session.removeAttribute(SessionStr.ADD_ADV_INFO);
        } else {
            session.setAttribute(SessionStr.ADD_ADV_FILE, file);
        }
        return GsonUtils.toJson(resultObj);
    }

    /**
     * 上传文件之后，客户端再进行一次post提交
     */
    @RequestMapping(value = "/addAdv", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String addAdv(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        AdvObj advObj = (AdvObj) session.getAttribute(SessionStr.ADD_ADV_INFO);
        MultipartFile advFile = (MultipartFile) session.getAttribute(SessionStr.ADD_ADV_FILE);
        ResultObj<Void> resultObj = advService.addAdv(advObj, advFile);
        session.removeAttribute(SessionStr.ADD_ADV_INFO);
        session.removeAttribute(SessionStr.ADD_ADV_FILE);
        return GsonUtils.toJson(resultObj);
    }

    /**
     * 更新广告流程：
     * 客户端上传广告信息 -> 服务端检查回应并保存session ->
     * (客户端上传广告文件 -> 服务器检查文件更新session->) // 这一步不是必要的
     * 客户端再次发送post请求 -> 服务器拿到session并更新数据库和广告文件
     */
    @RequestMapping(value = "/updateAdvInfo", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String updateAdvInfo(HttpServletRequest request, HttpServletResponse response, AdvObj advObj, @RequestParam("tags[]") List<Long> tags, HttpSession session) {
        AdvUtils.printAdv(advObj);
        ResultObj resultObj = advService.checkUpdateAdvInfo(advObj);
        if (resultObj.getCode() == ResultCodes.SUCCESS) {
            session.setAttribute(SessionStr.UPDATE_ADV_INFO, advObj);
        }
        return GsonUtils.toJson(resultObj);
    }

    @RequestMapping(value = "/updateAdvFile"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody
    public String updateAdvFile(@RequestParam(required = false) MultipartFile file, HttpSession session) {
        System.out.println("get file");
        AdvObj advObj = (AdvObj) session.getAttribute(SessionStr.UPDATE_ADV_INFO);
        ResultObj resultObj = advService.checkAdvFile(advObj, file);
        if (resultObj.getCode() != ResultCodes.SUCCESS) {
            session.removeAttribute(SessionStr.UPDATE_ADV_INFO);
        } else {
            session.setAttribute(SessionStr.UPDATE_ADV_FIlE, file);
        }
        return GsonUtils.toJson(resultObj);
    }

    @RequestMapping(value = "/updateAdv", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String updateAdv(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        AdvObj advObj = (AdvObj) session.getAttribute(SessionStr.UPDATE_ADV_INFO);
        MultipartFile advFile = (MultipartFile) session.getAttribute(SessionStr.UPDATE_ADV_FIlE);
        ResultObj<Void> resultObj = advService.updateAdv(advObj, advFile);
        session.removeAttribute(SessionStr.UPDATE_ADV_INFO);
        session.removeAttribute(SessionStr.UPDATE_ADV_FIlE);
        return GsonUtils.toJson(resultObj);
    }

    @RequestMapping(value = "/queryAdv", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String queryAdv(HttpServletRequest request, HttpServletResponse response, AdvObj advObj, @RequestParam("offset") int offset, @RequestParam("limit") int limit, @RequestParam(value = "tags[]",required=false) List<Long> tags,HttpSession session) {
        if(tags!=null){
            advObj.setUserTagIds(tags);
        }
        AdvUtils.printAdv(advObj);
        System.out.println("offset：" + offset);
        System.out.println("limit：" + limit);
        ResultObj<List<AdvObj>> resultObj = advService.queryAdv(advObj, offset, limit);
        return GsonUtils.toJson(resultObj);
    }

    @RequestMapping(value = "/changeInfoRequest", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String changeInfoRequest(HttpServletRequest request, HttpServletResponse response, @RequestParam("advId") Long advId, HttpSession session) {
        ResultObj<Void> resultObj = advService.checkUpdateId(advId);
        if (resultObj.isSuccess()) {
            request.setAttribute(SessionStr.CHANGE_INFO_ID, advId);
        }
        return GsonUtils.toJson(resultObj);
    }

    @RequestMapping(value = "/queryAdvById", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String queryAdvById(HttpServletRequest request, HttpServletResponse response,  @RequestParam("advId")Long advId,HttpSession session) {
        ResultObj<AdvObj> resultObj = advService.queryAdvById(advId);
        return GsonUtils.toJson(resultObj);
    }
}
