package com.springframework.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping("/")
    public @ResponseBody String index(){
        return "Hello, Spring Security!";
    }

    @RequestMapping(value = "/toLogin")
    public String toLogin() {
        return "login";
    }

}
