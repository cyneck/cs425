package com.group8.project.dao;

import com.group8.project.domain.Preference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PreferenceDao {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PreferenceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Preference> findAll() {
        String sql = "SELECT email, desired_date, preferred_location, budget FROM preference";
        return jdbcTemplate.query(sql, new PreferenceDao.PreferenceRowMapper());
    }

    public Preference findByEmail(String email) {
        String sql = "SELECT email, desired_date, preferred_location, budget FROM preference where email=?";
        List<Preference> preferenceList = jdbcTemplate.query(sql, new PreferenceRowMapper(), email);
        return preferenceList.isEmpty()? null : preferenceList.get(0);
    }

    public void save(Preference preference) {
        jdbcTemplate.update("INSERT INTO preference (email, desired_date, preferred_location, budget) VALUES (?, ?, ?, ?)",
                preference.getEmail(),
                preference.getDesiredDate(),
                preference.getPreferredLocation(),
                preference.getBudget());
    }

    public void update(Preference preference) {
        jdbcTemplate.update("UPDATE preference SET email=?, desired_date=?, preferred_location=?, budget=? WHERE email=?",
                preference.getEmail(),
                preference.getDesiredDate(),
                preference.getPreferredLocation(),
                preference.getBudget(),
                preference.getEmail());
    }

    public void delete(String email) {
        jdbcTemplate.update("DELETE FROM preference WHERE email=?", email);
    }


    private class PreferenceRowMapper implements RowMapper<Preference> {
        @Override
        public Preference mapRow(ResultSet rs, int rowNum) throws SQLException {
            Preference preference = new Preference();
            preference.setEmail(rs.getString("email"));
            preference.setPreferredLocation(rs.getString("preferred_location"));
            preference.setDesiredDate(rs.getDate("desired_date"));
            preference.setBudget(rs.getBigDecimal("budget"));

            return preference;
        }
    }
}
