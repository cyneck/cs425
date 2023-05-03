package com.group8.project.dao;

import com.group8.project.domain.Renter;
import com.group8.project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RenterDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RenterDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Renter> findAll() {
        String sql = "SELECT r.email, r.age, r.sex, r.job, r.phone, u.firstname, u.lastname, u.passwd, u.role FROM Renter r INNER JOIN Users u ON r.email=u.email";
        return jdbcTemplate.query(sql, new RenterWithUserRowMapper());
    }

    public Renter findByEmail(String email) {
        String sql = "SELECT r.email, r.age, r.sex, r.job, r.phone, u.firstname, u.lastname, u.passwd, u.role FROM Renter r INNER JOIN Users u ON r.email=u.email WHERE r.email=?";
        List<Renter> renters = jdbcTemplate.query(sql, new RenterWithUserRowMapper(), email);
        return renters.isEmpty() ? null : renters.get(0);
    }

    public void save(Renter renter) {
        jdbcTemplate.update("INSERT INTO Renter (email, age, sex, job, phone) VALUES (?, ?, ?, ?, ?)", renter.getEmail(),
                renter.getAge(), renter.getSex(), renter.getJob(), renter.getPhone());
    }

    public void update(Renter renter) {
        jdbcTemplate.update("UPDATE Renter SET age=?, sex=?, job=?, phone=? WHERE email=?", renter.getAge(),
                renter.getSex(), renter.getJob(), renter.getPhone(), renter.getEmail());
    }

    public void deleteByEmail(String email) {
        jdbcTemplate.update("DELETE FROM Renter WHERE email=?", email);
    }

    private static class RenterWithUserRowMapper implements RowMapper<Renter> {
        @Override
        public Renter mapRow(ResultSet rs, int rowNum) throws SQLException {
            Renter renter = new Renter();
            renter.setEmail(rs.getString("email"));
            renter.setAge(rs.getInt("age"));
            renter.setSex(rs.getString("sex"));
            renter.setJob(rs.getString("job"));
            renter.setPhone(rs.getString("phone"));

            User user = new User();
            user.setEmail(rs.getString("email"));
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));
            user.setPassword(rs.getString("passwd"));
            user.setRole(rs.getString("role"));

            renter.setUser(user);

            return renter;
        }
    }
}
