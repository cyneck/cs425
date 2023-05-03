package com.group8.project.dao;

import com.group8.project.domain.Agent;
import com.group8.project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
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

    public Agent findByEmailWithoutJoin(String email) {
        String sql = "SELECT * FROM Agent WHERE Agent.email=?";
        List<Agent> agentList = jdbcTemplate.query(sql, new Object[]{email}, new AgentRowMapper());
        return agentList.isEmpty() ? null : agentList.get(0);
    }

    public Agent findByEmail(String email) {
        String sql = "SELECT * FROM Agent LEFT JOIN Users ON Agent.email = Users.email WHERE Agent.email=?";
        List<Agent> agentList = jdbcTemplate.query(sql, new Object[]{email}, new AgentWithUserRowMapper());
        return agentList.isEmpty() ? null : agentList.get(0);
    }

    public List<Agent> findByEstateAgency(String estateAgency) {
        String sql = "SELECT * FROM Agent LEFT JOIN Users ON Agent.email = Users.email WHERE estate_agency=?";
        List<Agent> agentList = jdbcTemplate.query(sql, new Object[]{estateAgency}, new AgentWithUserRowMapper());
        return agentList.isEmpty() ? null : agentList;
    }

    public void save(Agent agent) {
        String sql = "INSERT INTO Agent(email, estate_agency, job_title, phone) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, agent.getUser().getEmail(), agent.getEstateAgency(), agent.getJobTitle(), agent.getPhone());
    }

    public void update(Agent agent) {
        String sql = "UPDATE Agent SET estate_agency=?, job_title=?, phone=? WHERE email=?";
        jdbcTemplate.update(sql, agent.getEstateAgency(), agent.getJobTitle(), agent.getPhone(), agent.getUser().getEmail());
    }

    public void deleteByEmail(String email) {
        String sql = "DELETE FROM Agent WHERE email=?";
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

            agent.setUser(user);

            return agent;
        }
    }

    private static class AgentRowMapper implements RowMapper<Agent> {

        @Override
        public Agent mapRow(ResultSet rs, int rowNum) throws SQLException {
            Agent agent = new Agent();
            agent.setEstateAgency(rs.getString("estate_agency"));
            agent.setJobTitle(rs.getString("job_title"));
            agent.setPhone(rs.getString("phone"));
            agent.setEmail(rs.getString("email"));
            return agent;
        }
    }

}
