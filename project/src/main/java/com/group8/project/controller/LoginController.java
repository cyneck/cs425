package com.group8.project.controller;

import com.group8.project.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping()
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "portal/login";
    }

    @PostMapping()
    public String processLoginForm(@ModelAttribute("user") User user, BindingResult bindingResult, HttpServletRequest request) {
        String email = user.getEmail();
        String password = user.getPassword();
        String sql = "SELECT * FROM Users WHERE email = ? AND passwd = ?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{email, password}, new RowMapper<User>() {
            User user = new User();

            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setPassword(rs.getString("passwd"));
                user.setRole(rs.getString("role"));
                return user;
            }
        });

        if (users.size() > 0) {
            // If user validation passed, save the user info into session
            HttpSession session = request.getSession();
            session.setAttribute("user", users.get(0));
            if (users.get(0).getRole().equals("agent")) {
                return "redirect:agent/dashboard";
            } else {
                return "redirect:renter/dashboard";
            }

        } else {
            // If user validation failed, return to the login page
            bindingResult.rejectValue("email", "error.user", "Invalid email or password.");
            return "portal/login";
        }
    }
}
