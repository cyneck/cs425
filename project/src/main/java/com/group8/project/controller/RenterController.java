package com.group8.project.controller;

import com.group8.project.domain.Address;
import com.group8.project.domain.User;
import com.group8.project.service.AddressService;
import com.group8.project.service.AgentService;
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
import java.util.UUID;


@Controller
@RequestMapping("/renter")
public class RenterController {


    @Autowired
    private UserService userService;

    @Autowired
    private RenterService renterService;

    @Autowired
    private AgentService agentService;

    @Autowired
    private AddressService addressService;


    @RequestMapping("/updateUser")
    public String update(User user, HttpSession session, Model model) {
        userService.update(user);
        model.addAttribute("user", user);
        return "portal/renter/dashboard";
    }

    @RequestMapping("/address")
    public String address(Address address, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        // Check if user is authenticated
        if (user == null) {
            // User is not authenticated, redirect to login page
            return "redirect:/login?error";
        }
        if (null != address
                && StringUtils.isNotBlank(address.getState())
                && StringUtils.isNotBlank(address.getCity())
                && StringUtils.isNotBlank(address.getAddress())) {
            address.setAddressId(System.currentTimeMillis() + "");
            address.setEmail(user.getEmail());
            addressService.save(address);
        }
        List<Address> addressList = addressService.findByEmail(user.getEmail());
        model.addAttribute("user", user);
        model.addAttribute("addressList", addressList);
        // User is authenticated, show dashboard
        return "portal/renter/address";
    }

    @RequestMapping("/addressForm")
    public String addressForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        // Check if user is authenticated
        if (user == null) {
            // User is not authenticated, redirect to login page
            return "redirect:/login?error";
        }
        Address address = new Address();
        address.setEmail(user.getEmail());
        model.addAttribute("address", address);
        model.addAttribute("user", user);
        // User is authenticated, show dashboard
        return "portal/renter/addressForm";
    }

    @GetMapping("/creditCard")
    public String getCreditCard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        // Check if user is authenticated
        if (user == null) {
            // User is not authenticated, redirect to login page
            return "redirect:/login?error";
        }
        List<Address> addressList = addressService.findByEmail(user.getEmail());
        model.addAttribute("user", user);
        model.addAttribute("addressList", addressList);
        // User is authenticated, show dashboard
        return "portal/renter/creditCard";
    }
}
