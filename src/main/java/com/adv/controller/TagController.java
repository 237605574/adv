package com.adv.controller;

import com.adv.service.TagService;
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
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/getAllUserTags", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String getAllUserTags(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        return GsonUtils.toJson(tagService.getAllTags());
    }
}
