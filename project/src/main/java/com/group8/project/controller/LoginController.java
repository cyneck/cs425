package com.group8.project.controller;

import com.group8.project.dao.UserDao;
import com.group8.project.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDao userDao;

    @GetMapping("login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "portal/login";
    }

    @PostMapping("login")
    public String processLoginForm(@Valid @ModelAttribute("user") User user, Model model, BindingResult bindingResult, HttpServletRequest request) {
        String email = user.getEmail();
        String password = user.getPassword();
        String sql = "SELECT * FROM Users WHERE email = ? AND passwd = ?";
        User inUser = userDao.findByEmailAndPwd(email, password);
        if (inUser != null) {
            // If user validation passed, save the user info into session
            HttpSession session = request.getSession();
            session.setAttribute("user", inUser);
            model.addAttribute("user", inUser);
            if (inUser.equals("agent")) {
                return "portal/agent/dashboard";
            } else {
                return "portal/renter/dashboard";
            }

        } else {
            model.addAttribute("user", user);
            // If user validation failed, return to the login page
            bindingResult.rejectValue("email", "error.user", "Invalid email or password.");
            return "portal/login";
        }
    }

    @GetMapping("signUp")
    public String signUpPage() {
        return "portal/signUp";
    }

    @PostMapping("signUp")
    public String signUp(@ModelAttribute("user") User user, Model model, BindingResult bindingResult, HttpServletRequest request) {
        if (StringUtils.isNotBlank(user.getEmail()) ||
                StringUtils.isNotBlank(user.getPassword())) {
            userDao.save(user);
            User inUser = userDao.findByEmail(user.getEmail());
            if (inUser != null) {
                // If user validation passed, save the user info into session
                HttpSession session = request.getSession();
                session.setAttribute("user", inUser);
                model.addAttribute("user", inUser);
                if (inUser.getRole().equals("agent")) {
                    return "portal/agent/dashboard";
                } else {
                    return "portal/renter/dashboard";
                }

            } else {
                model.addAttribute("user", user);
                // If user validation failed, return to the login page
                bindingResult.rejectValue("email", "error.user", "Invalid email or password.");
                return "portal/login";
            }
        }
        return "portal/signUp";
    }

    @GetMapping("logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 清除 session 中用户信息
        request.getSession().removeAttribute("user");

        // 重定向到登录页面
        response.sendRedirect("/login");
        // 销毁 session
        request.getSession().invalidate();

    }

}
