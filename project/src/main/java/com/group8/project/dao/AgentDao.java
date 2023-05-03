package com.group8.project.dao;

import com.group8.project.domain.Agent;
import com.group8.project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AgentDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AgentDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Agent> findAll() {
        String sql = "SELECT * FROM Agent LEFT JOIN Users ON Agent.email = Users.email";
        return jdbcTemplate.query(sql, new AgentWithUserRowMapper());
    }

    public Agent findByEmail(String email) {
        String sql = "SELECT * FROM Agent LEFT JOIN Users ON Agent.email = Users.email WHERE Agent.email=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, new AgentWithUserRowMapper());
    }

    public List<Agent> findByEstateAgency(String estateAgency) {
        String sql = "SELECT * FROM Agent LEFT JOIN Users ON Agent.email = Users.email WHERE estate_agency=?";
        return jdbcTemplate.query(sql, new Object[]{estateAgency}, new AgentWithUserRowMapper());
    }

    public void save(Agent agent) {
        String sql = "INSERT INTO Users(email, firstname, lastname, passwd, role) VALUES (?, ?, ?, ?, 'agent')";
        jdbcTemplate.update(sql, agent.getUser().getEmail(), agent.getUser().getFirstName(), agent.getUser().getLastName(), agent.getUser().getPassword());

        sql = "INSERT INTO Agent(email, estate_agency, job_title, phone) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, agent.getUser().getEmail(), agent.getEstateAgency(), agent.getJobTitle(), agent.getPhone());
    }

    public void update(Agent agent) {
        String sql = "UPDATE Users SET firstname=?, lastname=?, passwd=? WHERE email=?";
        jdbcTemplate.update(sql, agent.getUser().getFirstName(), agent.getUser().getLastName(), agent.getUser().getPassword(), agent.getUser().getEmail());

        sql = "UPDATE Agent SET estate_agency=?, job_title=?, phone=? WHERE email=?";
        jdbcTemplate.update(sql, agent.getEstateAgency(), agent.getJobTitle(), agent.getPhone(), agent.getUser().getEmail());
    }

    public void deleteByEmail(String email) {
        String sql = "DELETE FROM Agent WHERE email=?";
        jdbcTemplate.update(sql, email);

        sql = "DELETE FROM Users WHERE email=?";
        jdbcTemplate.update(sql, email);
    }

    private static class AgentWithUserRowMapper implements RowMapper<Agent> {

        @Override
        public Agent mapRow(ResultSet rs, int rowNum) throws SQLException {
            Agent agent = new Agent();
            agent.setEstateAgency(rs.getString("estate_agency"));
            agent.setJobTitle(rs.getString("job_title"));
            agent.setPhone(rs.getString("phone"));

            User user = new User();
            user.setEmail(rs.getString("email"));
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));
            user.setPassword(rs.getString("passwd"));
            user.setRole(rs.getString("role").toLowerCase());

            agent.setUser(user);

            return agent;
        }
    }

}
