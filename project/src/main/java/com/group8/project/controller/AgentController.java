package com.group8.project.controller;


import com.group8.project.domain.Address;
import com.group8.project.domain.Agent;
import com.group8.project.domain.User;
import com.group8.project.service.AddressService;
import com.group8.project.service.AgentService;
import com.group8.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/agent")
public class AgentController {

    @Autowired
    private UserService userService;

    @Autowired
    private AgentService agentService;

    @RequestMapping("/updateUser")
    public String update(Agent agent, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (null != user) {
            agent.setEmail(user.getEmail());
            agentService.update(agent);
        }
        model.addAttribute("agent", agent);
        model.addAttribute("user", user);
        return "portal/agent/dashboard";
    }

}
