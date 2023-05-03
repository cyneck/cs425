package com.group8.project.dao;

import com.group8.project.domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AddressDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AddressDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Address findById(String addressId) {
        String sql = "SELECT * FROM Address WHERE address_id = ?";
        List<Address> addresses = jdbcTemplate.query(sql, new AddressRowMapper(), addressId);
        return addresses.isEmpty() ? null : addresses.get(0);
    }

    public List<Address> findAll() {
        String sql = "SELECT * FROM Address";
        return jdbcTemplate.query(sql, new AddressRowMapper());
    }

    public List<Address> findByEmail(String email) {
        String sql = "SELECT * FROM Address WHERE email = ?";
        return jdbcTemplate.query(sql, new AddressRowMapper(), email);
    }

    public void save(Address address) {
        String sql = "INSERT INTO Address (address_id, email, city, state, address) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, address.getAddressId(), address.getEmail(), address.getCity(),
                address.getState(), address.getAddress());
    }

    public void update(Address address) {
        String sql = "UPDATE Address SET city = ?, state = ?, address = ? WHERE address_id = ?";
        jdbcTemplate.update(sql, address.getCity(), address.getState(), address.getAddress(), address.getAddressId());
    }

    public void deleteById(String addressId) {
        String sql = "DELETE FROM Address WHERE address_id = ?";
        jdbcTemplate.update(sql, addressId);
    }

    public void deleteByEmail(String email) {
        String sql = "DELETE FROM Address WHERE email = ?";
        jdbcTemplate.update(sql, email);
    }

    private static class AddressRowMapper implements RowMapper<Address> {
        @Override
        public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
            String addressId = rs.getString("address_id");
            String email = rs.getString("email");
            String city = rs.getString("city");
            String state = rs.getString("state");
            String address = rs.getString("address");
            return new Address(addressId, email, city, state, address);
        }
    }
}
