package com.group8.project.controller;

import com.group8.project.domain.Renter;
import com.group8.project.domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class DashboardController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/agent/dashboard")
    public String showAgentDashboard(HttpSession session) {
        // Check if user is authenticated
        if (session.getAttribute("user") == null) {
            // User is not authenticated, redirect to login page
            return "redirect:/login?error";
        }

        // User is authenticated, show dashboard
        return "portal/agent/dashboard";
    }

    @GetMapping("/renter/dashboard")
    public String showRenterDashboard(HttpSession session) {
        User user = (User) session.getAttribute("user");
        // Check if user is authenticated
        if (user == null) {
            // User is not authenticated, redirect to login page
            return "redirect:/login?error";
        }

        String sql = "SELECT * FROM renter WHERE email = ?";
        List<Renter> renters = jdbcTemplate.query(sql, new Object[]{user.getEmail()}, new RowMapper<Renter>() {
            Renter renter = new Renter();

            @Override
            public Renter mapRow(ResultSet rs, int rowNum) throws SQLException {
                renter.setEmail(rs.getString("email"));
                renter.setAge(rs.getInt("age"));
                renter.setSex(rs.getString("sex"));
                renter.setJob(rs.getString("job"));
                renter.setPhone(rs.getString("phone"));
                return renter;
            }
        });
        renters.get(0).setUser(user);
        session.setAttribute("renter", renters.get(0));

        // User is authenticated, show dashboard
        return "portal/renter/dashboard";
    }
}
