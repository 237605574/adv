package com.adv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author lurongzhi
 */
@Controller
@RequestMapping("/adv")
public class MainController {
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/addAdv", method = RequestMethod.GET)
    public String addAdv() {
        return "addAdv";
    }

    @RequestMapping(value = "/queryAdv", method = RequestMethod.GET)
    public String queryAdv() {
        return "queryAdv";
    }

    @RequestMapping(value = "/changeAdv", method = RequestMethod.GET)
    public String changeAdv() {
        return "changeAdv";
    }
}
