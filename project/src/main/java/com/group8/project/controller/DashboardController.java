package com.group8.project.controller;

import com.group8.project.domain.Address;
import com.group8.project.domain.Agent;
import com.group8.project.domain.Renter;
import com.group8.project.domain.User;
import com.group8.project.service.AddressService;
import com.group8.project.service.AgentService;
import com.group8.project.service.RenterService;
import com.group8.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class DashboardController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AgentService agentService;


    @GetMapping("/agent/dashboard")
    public String showAgentDashboard(HttpSession session, Model model) {
        // Check if user is authenticated
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // User is not authenticated, redirect to login page
            return "redirect:/login?error";
        }
        Agent agent = agentService.findByEmail(user.getEmail());
        model.addAttribute("user", agent.getUser());
        model.addAttribute("agent", agent);
        // User is authenticated, show dashboard
        return "portal/agent/dashboard";
    }

    @GetMapping("/renter/dashboard")
    public String showRenterDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        // Check if user is authenticated
        if (user == null) {
            // User is not authenticated, redirect to login page
            return "redirect:/login?error";
        }
        model.addAttribute("user", user);
        // User is authenticated, show dashboard
        return "portal/renter/dashboard";
    }


}
