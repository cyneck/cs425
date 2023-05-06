package com.group8.project.dao;

import com.group8.project.domain.Property;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PropertyDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PropertyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    private AgentDao agentDao;

    public List<Property> findAll() {
        String sql = "SELECT property_id, email, property_type, description, city, state, address, availability, rental_price, square_footage FROM property";
        return jdbcTemplate.query(sql, new PropertyDao.PropertyRowMapper());
    }

    public Property findById(String id) {
        String sql = "SELECT property_id, email, property_type, description, city, state, address, availability, rental_price, square_footage FROM property where email=?";
        List<Property> propertyList = jdbcTemplate.query(sql, new PropertyRowMapper(), id);
        return propertyList.isEmpty() ? null : propertyList.get(0);
    }

    public List<Property> findByEmail(String email) {
        String sql = "SELECT property_id, email, property_type, description, city, state, address, availability, rental_price, square_footage FROM property where email=?";
        return jdbcTemplate.query(sql, new PropertyDao.PropertyRowMapper(), email);
    }

    public List<Property> findByConditions(String propertyType, String city, String state, Boolean availability, BigDecimal rentalPriceLow, BigDecimal rentalPriceHigh, BigDecimal squareFootageLow, BigDecimal squareFootageHigh) {
        StringBuilder sql = new StringBuilder("SELECT property_id, email, property_type, description, city, state, address, availability, rental_price, square_footage FROM property where 1=1");
        if (StringUtils.isNotBlank(propertyType)) {
            sql.append(" and property_type=");
            sql.append(propertyType);
        }
        if (StringUtils.isNotBlank(city)) {
            sql.append(" and city=");
            sql.append(city);
        }
        if (StringUtils.isNotBlank(state)) {
            sql.append(" and state=");
            sql.append(state);
        }
        if (availability != null) {
            sql.append(" and availability=");
            sql.append(availability ? 1 : 0);
        }
        if (rentalPriceLow != null) {
            sql.append(" and rental_price>=");
            sql.append(rentalPriceLow);
        }
        if (rentalPriceHigh != null) {
            sql.append(" and rental_price<=");
            sql.append(rentalPriceHigh);
        }
        if (squareFootageLow != null) {
            sql.append(" and square_footage>=");
            sql.append(squareFootageLow);
        }
        if (squareFootageHigh != null) {
            sql.append(" and square_footage<=");
            sql.append(squareFootageHigh);
        }

        return jdbcTemplate.query(sql.toString(), new PropertyDao.PropertyRowMapper());
    }

    public void save(Property property) {
        jdbcTemplate.update("INSERT INTO property (property_id, email, property_type, description, city, state, address, availability, rental_price, square_footage) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                property.getPropertyId(),
                property.getAgent().getEmail(),
                property.getPropertyType(),
                property.getDescription(),
                property.getCity(),
                property.getState(),
                property.getAddress(),
                property.getAvailability(),
                property.getRentalPrice(),
                property.getSquareFootage());
    }

    public void update(Property property) {
        jdbcTemplate.update("UPDATE property SET property_id=?, email=?, property_type=?, description=?, city=?, state=?, address=?, availability=?, rental_price=?, square_footage=? WHERE property_id=?",
                property.getPropertyId(),
                property.getAgent().getEmail(),
                property.getPropertyType(),
                property.getDescription(),
                property.getCity(),
                property.getState(),
                property.getAddress(),
                property.getAvailability(),
                property.getRentalPrice(),
                property.getSquareFootage(),
                property.getPropertyId());
    }

    public void deleteById(String propertyId) {
        jdbcTemplate.update("DELETE FROM property WHERE property_id=?", propertyId);
    }

    public void deleteByEmail(String email) {
        jdbcTemplate.update("DELETE FROM property WHERE email=?", email);
    }

    private class PropertyRowMapper implements RowMapper<Property> {
        @Override
        public Property mapRow(ResultSet rs, int rowNum) throws SQLException {
            Property property = new Property();
            property.setPropertyId(rs.getString("property_id"));
            property.setPropertyType(rs.getString("property_type"));
            property.setDescription(rs.getString("description"));
            property.setCity(rs.getString("city"));
            property.setState(rs.getString("state"));
            property.setAddress(rs.getString("address"));
            property.setAvailability(rs.getInt("availability"));
            property.setRentalPrice(rs.getBigDecimal("rental_price"));
            property.setSquareFootage(rs.getBigDecimal("square_footage"));
            property.setEmail(rs.getString("email"));
            property.setAgent(agentDao.findByEmail(rs.getString("email")));

            return property;
        }
    }
}
