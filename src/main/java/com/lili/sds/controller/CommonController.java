package com.lili.sds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
public class CommonController {

    @RequestMapping("/logout")
    public String logout(HttpSession session)
    {
        session.invalidate();
        return "redirect:/index.html";
    }

}