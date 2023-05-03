package com.group8.project.controller;

import com.group8.project.dao.UserDao;
import com.group8.project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDao userDao;

    @GetMapping("/")
    public String Home(Model model) {
        model.addAttribute("user", new User());
        return "portal/login";
    }


}
