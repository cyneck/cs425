package com.group8.project.controller;


import com.group8.project.domain.*;
import com.group8.project.service.AgentService;
import com.group8.project.service.PropertyService;
import com.group8.project.service.RenterService;
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
@RequestMapping("/agent/")
public class PropertyController {

    @Autowired
    private UserService userService;

    @Autowired
    private AgentService agentService;

    @Autowired
    private PropertyService propertyService;


    @RequestMapping("/property")
    public String address(Property entity, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        // Check if user is authenticated
        if (user == null) {
            // User is not authenticated, redirect to login page
            return "redirect:/login?error";
        }
        if (null != entity
                && StringUtils.isNotBlank(entity.getState())
                && StringUtils.isNotBlank(entity.getCity())
                && StringUtils.isNotBlank(entity.getPropertyType())
                && StringUtils.isNotBlank(entity.getAddress())) {
            Agent agent = agentService.findByEmail(user.getEmail());
            entity.setAgent(agent);
            entity.setPropertyId(System.currentTimeMillis() + "");
            propertyService.save(entity);
        }
        List<Property> propertyList = propertyService.findByEmail(user.getEmail());
        model.addAttribute("user", user);
        model.addAttribute("propertyList", propertyList);
        // User is authenticated, show dashboard
        return "portal/agent/property";
    }

    @RequestMapping("/propertyForm")
    public String addressForm(String id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        // Check if user is authenticated
        if (user == null) {
            // User is not authenticated, redirect to login page
            return "redirect:/login?error";
        }
        Property entity = new Property();
        if (StringUtils.isNotBlank(id)) {
            entity = propertyService.findById(id);
        }
        model.addAttribute("property", entity);
        model.addAttribute("propertyTypeList", PropertyTypeEnum.values());
        model.addAttribute("propertyType", entity.getPropertyType());
        model.addAttribute("user", user);
        // User is authenticated, show dashboard
        return "portal/agent/propertyForm";
    }

    @GetMapping("/deletePropertyForm")
    public String deleteProperty(String id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        // Check if user is authenticated
        if (user == null) {
            // User is not authenticated, redirect to login page
            return "redirect:/login?error";
        }
        Property entity = new Property();
        if (StringUtils.isNotBlank(id)) {
            propertyService.deleteById(id);
        }
        List<Property> propertyList = propertyService.findByEmail(user.getEmail());
        model.addAttribute("user", user);
        model.addAttribute("propertyList", propertyList);
        // User is authenticated, show dashboard
        return "portal/agent/property";
    }

}
