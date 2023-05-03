package com.group8.project.dao;

import com.group8.project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findByEmail(String email) {
        String sql = "SELECT email, firstname, lastname, passwd FROM Users WHERE email = ?";
        List<User> userList = jdbcTemplate.query(sql, new Object[]{email}, new UserRowMapper());
        return userList.isEmpty() ? null : userList.get(0);
    }

    public User findByEmailAndPwd(String email,String passwd) {
        String sql = "SELECT email, firstname, lastname, passwd FROM Users WHERE email = ? and passwd = ?";
        List<User> userList = jdbcTemplate.query(sql, new Object[]{email,passwd}, new UserRowMapper());
        return userList.isEmpty() ? null : userList.get(0);
    }

    public List<User> findAll() {
        String sql = "SELECT email, firstname, lastname, passwd FROM Users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }


    public void save(User user) {
        String sql = "INSERT INTO Users (email, firstname, lastname, passwd) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getFirstName(), user.getLastName(), user.getPassword());
    }

    public void update(User user) {
        String sql = "UPDATE Users SET firstname = ?, lastname = ?, passwd = ? WHERE email = ?";
        jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getPassword(), user.getEmail());
    }

    public void deleteByEmail(String email) {
        String sql = "DELETE FROM Users WHERE email = ?";
        jdbcTemplate.update(sql, email);
    }

    private class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setEmail(rs.getString("email"));
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));
            user.setPassword(rs.getString("passwd"));
            return user;
        }
    }
}
