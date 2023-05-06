package com.group8.project.controller;

import com.group8.project.domain.Agent;
import com.group8.project.domain.Renter;
import com.group8.project.domain.User;
import com.group8.project.domain.dto.AgentDto;
import com.group8.project.domain.dto.RenterDto;
import com.group8.project.service.AgentService;
import com.group8.project.service.RenterService;
import com.group8.project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RenterService renterService;

    @Autowired
    private AgentService agentService;

    /* --- Page Router --- */
    @GetMapping("login")
    public String loginFormPage(Model model) {
        model.addAttribute("user", new User());
        return "portal/login";
    }

    @GetMapping("signUpAgent")
    public String signUpAgentPage(Model model) {
        model.addAttribute("user", new User());
        return "portal/signUpAgent";
    }

    @GetMapping("signUpRenter")
    public String signUpRenterPage(Model model) {
        model.addAttribute("user", new User());
        return "portal/signUpRenter";
    }

    /* --- Business Router --- */
    @PostMapping("login")
    public String processLoginForm(@Valid @ModelAttribute("user") User user, Model model,
                                   BindingResult bindingResult,
                                   HttpServletRequest request,
                                   RedirectAttributes attributes) {
        String email = user.getEmail();
        String password = user.getPassword();
        String sql = "SELECT * FROM Users WHERE email = ? AND passwd = ?";
        User inUser = userService.findByEmailAndPwd(email, password);
        if (inUser != null) {
            // If user validation passed, save the user info into session
            HttpSession session = request.getSession();
            if (user.getRole().equals("agent")) {
                Agent agent = agentService.findByEmail(inUser.getEmail());
                if (null == agent) {
                    model.addAttribute("user", user);
                    // If user validation failed, return to the login page
                    bindingResult.rejectValue("email", "error.user", "Invalid agent email or password.");
                    return "portal/login";
                }
                agent.setUser(inUser);
                session.setAttribute("user", inUser);
                session.setAttribute("agent", agent);
                attributes.addFlashAttribute("user", inUser);
                attributes.addFlashAttribute("agent", agent);
                return "redirect:/agent/dashboard";
            } else {
                Renter renter = renterService.getByEmail(inUser.getEmail());
                if (null == renter) {
                    model.addAttribute("user", user);
                    // If user validation failed, return to the login page
                    bindingResult.rejectValue("email", "error.user", "Invalid renter email or password.");
                    return "portal/login";
                }
                renter.setUser(inUser);
                session.setAttribute("user", inUser);
                session.setAttribute("renter", renter);
                attributes.addFlashAttribute("user", inUser);
                attributes.addFlashAttribute("renter", renter);
                return "redirect:/renter/dashboard";
            }

        } else {
            model.addAttribute("user", user);
            // If user validation failed, return to the login page
            bindingResult.rejectValue("email", "error.user", "Invalid email or password.");
            return "portal/login";
        }
    }

    @PostMapping("signUpAgent")
    public String signUpAgent(@Valid @ModelAttribute("user") AgentDto agentDto, Model model,
                              BindingResult bindingResult,
                              HttpServletRequest request,
                              RedirectAttributes attributes) {
        if (StringUtils.isNotBlank(agentDto.getEmail()) || StringUtils.isNotBlank(agentDto.getPassword())) {
            Agent agent = agentService.findByEmail(agentDto.getEmail());
            if (agent != null) {
                model.addAttribute("user", agentDto);
                bindingResult.rejectValue("email", "error.user", "agent already exists!");
            } else {
                User user = new User();
                user.setEmail(agentDto.getEmail());
                user.setFirstName(agentDto.getFirstName());
                user.setLastName(agentDto.getLastName());
                user.setPassword(agentDto.getPassword());
                user.setRole("agent");

                Agent newAgent = new Agent();
                newAgent.setUser(user);
                newAgent.setEmail(user.getEmail());
                newAgent.setPhone(agentDto.getPhone());
                newAgent.setEstateAgency(agentDto.getEstateAgency());
                newAgent.setJobTitle(agentDto.getJobTitle());

                agentService.save(newAgent);

                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("agent", newAgent);
                attributes.addFlashAttribute("user", user);
                attributes.addFlashAttribute("agent", newAgent);
                return "redirect:/agent/dashboard";
            }
        }
        return "portal/signUpAgent";
    }

    @PostMapping("signUpRenter")
    public String signUpRenter(@Valid @ModelAttribute("user") RenterDto renter, Model model,
                               BindingResult bindingResult,
                               HttpServletRequest request,
                               RedirectAttributes attributes) {
        if (StringUtils.isNotBlank(renter.getEmail()) || StringUtils.isNotBlank(renter.getPassword())) {
            Renter renterTmp = renterService.getByEmail(renter.getEmail());
            if (renterTmp != null) {
                model.addAttribute("user", renter);
                bindingResult.rejectValue("email", "error.user", "renter already exists!");
                return "portal/signUpRenter";
            } else {
                User user = new User();
                user.setEmail(renter.getEmail());
                user.setFirstName(renter.getFirstName());
                user.setLastName(renter.getLastName());
                user.setPassword(renter.getPassword());
                user.setRole("renter");

                Renter newRenter = new Renter();
                newRenter.setUser(user);
                newRenter.setAge(renter.getAge());
                newRenter.setJob(renter.getJob());
                newRenter.setPhone(renter.getPhone());
                newRenter.setSex(renter.getSex());
                newRenter.setEmail(renter.getEmail());

                renterService.save(newRenter);

                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("renter", newRenter);
                attributes.addFlashAttribute("user", user);
                attributes.addFlashAttribute("renter", newRenter);
                return "redirect:/renter/dashboard";
            }
        }
        return "portal/signUpRenter";
    }


    @GetMapping("logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // clear user in session
        request.getSession().removeAttribute("user");
        // redirect to login
        response.sendRedirect("/login");
        // invalidate session
        request.getSession().invalidate();
    }
}
