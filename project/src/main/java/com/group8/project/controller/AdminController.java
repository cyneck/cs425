package com.group8.project.controller;

import com.group8.project.domain.User;
import com.group8.project.service.AgentService;
import com.group8.project.service.RenterService;
import com.group8.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private RenterService renterService;

    @Autowired
    private AgentService agentService;

    /**
     * Fetch User List
     *
     * @param map
     * @return user
     */
    @RequestMapping("/users")
    public String userList(ModelMap map) {
        String sql = "SELECT * FROM users";
        List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {
            User user = null;

            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                user = new User();
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setPassword(rs.getString("passwd"));
                return user;
            }
        });
        map.addAttribute("users", userList);
        return "admin/users";
    }

}
