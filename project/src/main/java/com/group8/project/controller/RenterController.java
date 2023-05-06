package com.group8.project.controller;

import com.group8.project.domain.Address;
import com.group8.project.domain.CreditCard;
import com.group8.project.domain.Renter;
import com.group8.project.domain.dto.CreditCardDto;
import com.group8.project.domain.User;
import com.group8.project.service.*;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


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

    @Autowired
    private CreditCardService creditCardService;


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
    public String addressForm(String addressId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        // Check if user is authenticated
        if (user == null) {
            // User is not authenticated, redirect to login page
            return "redirect:/login?error";
        }
        Address address = new Address();
        if (StringUtils.isNotBlank(addressId)) {
            address = addressService.findById(addressId);
        }
        model.addAttribute("address", address);
        model.addAttribute("user", user);
        // User is authenticated, show dashboard
        return "portal/renter/addressForm";
    }

    @GetMapping("/deleteAddress")
    public String deleteAddress(String addressId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        // Check if user is authenticated
        if (user == null) {
            // User is not authenticated, redirect to login page
            return "redirect:/login?error";
        }
        Address address = new Address();
        if (StringUtils.isNotBlank(addressId)) {
            addressService.deleteById(addressId);
        }
        List<Address> addressList = addressService.findByEmail(user.getEmail());
        model.addAttribute("user", user);
        model.addAttribute("addressList", addressList);
        // User is authenticated, show dashboard
        return "portal/renter/address";
    }


    @RequestMapping("/creditCard")
    public String creditCard(CreditCard entity, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        // Check if user is authenticated
        if (user == null) {
            // User is not authenticated, redirect to login page
            return "redirect:/login?error";
        }
        if (null != entity
                && StringUtils.isNotBlank(entity.getAddressId())
                && StringUtils.isNotBlank(entity.getEmail())
                && StringUtils.isNotBlank(entity.getBank())
                && StringUtils.isNotBlank(entity.getCardNo())) {
            creditCardService.save(entity);
        }
        List<CreditCard> cardList = creditCardService.findByEmail(user.getEmail());
        model.addAttribute("user", user);
        model.addAttribute("cardList", cardList);
        // User is authenticated, show dashboard
        return "portal/renter/creditCard";
    }

    @RequestMapping("/creditCardForm")
    public String creditCardForm(String id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        // Check if user is authenticated
        if (user == null) {
            // User is not authenticated, redirect to login page
            return "redirect:/login?error";
        }
        CreditCardDto creditCardDto = new CreditCardDto();
        creditCardDto.setRenter(new Renter());

        List<Address> addressList = addressService.findByEmail(user.getEmail());
        creditCardDto.setAddress(new Address());
        if (StringUtils.isNotBlank(id)) {
            CreditCard creditCard = creditCardService.findById(id);
            Address address = addressService.findById(creditCard.getAddressId());
            Renter renter = renterService.getByEmail(user.getEmail());
            creditCardDto.setCardNo(creditCard.getCardNo());
            creditCardDto.setBank(creditCard.getBank());
            creditCardDto.setRenter(renter);
            creditCardDto.setAddress(address);
        }
        model.addAttribute("creditCardDto", creditCardDto);
        model.addAttribute("addressList", addressList);
        model.addAttribute("addressId", creditCardDto.getAddress().getAddressId());
        model.addAttribute("user", user);
        // User is authenticated, show dashboard
        return "portal/renter/creditCardForm";
    }


    @RequestMapping("/deleteCreditCard")
    public String deleteCreditCard(String id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        // Check if user is authenticated
        if (user == null) {
            // User is not authenticated, redirect to login page
            return "redirect:/login?error";
        }
        if (StringUtils.isNotBlank(id)) {
            creditCardService.deleteById(id);
        }
        List<CreditCard> cardList = creditCardService.findByEmail(user.getEmail());
        model.addAttribute("user", user);
        model.addAttribute("cardList", cardList);
        // User is authenticated, show dashboard
        return "portal/renter/creditCard";
    }


}
