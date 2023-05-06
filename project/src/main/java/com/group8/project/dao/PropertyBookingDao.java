package com.group8.project.dao;

import com.group8.project.domain.Property;
import com.group8.project.domain.PropertyBooking;
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
public class PropertyBookingDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PropertyBookingDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    private RenterDao renterDao;

    @Autowired
    private CreditCardDao creditCardDao;

    public List<PropertyBooking> findAll() {
        String sql = "SELECT property_id, booking_date, status, email, card_no FROM propertybooking";
        return jdbcTemplate.query(sql, new PropertyBookingDao.PropertyRowMapper());
    }

    public List<PropertyBooking> findById(String id) {
        String sql = "SELECT property_id, booking_date, status, email, card_no FROM propertybooking where property_id=?";
        return jdbcTemplate.query(sql, new PropertyBookingDao.PropertyRowMapper(), id);
    }

    public List<PropertyBooking> findByEmail(String email) {
        String sql = "SELECT property_id, booking_date, status, email, card_no FROM propertybooking where email=?";
        return jdbcTemplate.query(sql, new PropertyBookingDao.PropertyRowMapper(), email);
    }

    public void save(PropertyBooking propertybooking) {
        jdbcTemplate.update("INSERT INTO propertybooking (property_id, booking_date, status, email, card_no) VALUES (?, ?, ?, ?, ?)",
                propertybooking.getPropertyId(),
                propertybooking.getBookingDate(),
                propertybooking.getStatus(),
                propertybooking.getRenter().getEmail(),
                propertybooking.getCreditCard().getCardNo());
    }

    public void update(PropertyBooking propertybooking) {
        jdbcTemplate.update("UPDATE propertybooking SET property_id=?, booking_date=?, status=?, email=?, card_no=? WHERE property_id=?",
                propertybooking.getPropertyId(),
                propertybooking.getBookingDate(),
                propertybooking.getStatus(),
                propertybooking.getRenter().getEmail(),
                propertybooking.getCreditCard().getCardNo(),
                propertybooking.getPropertyId());
    }

    public void deleteById(String propertyId) {
        jdbcTemplate.update("DELETE FROM propertybooking WHERE property_id=?", propertyId);
    }

    public void deleteByEmail(String email) {
        jdbcTemplate.update("DELETE FROM propertybooking WHERE email=?", email);
    }

    private class PropertyRowMapper implements RowMapper<PropertyBooking> {
        @Override
        public PropertyBooking mapRow(ResultSet rs, int rowNum) throws SQLException {
            PropertyBooking propertyBooking = new PropertyBooking();
            propertyBooking.setPropertyId(rs.getString("property_id"));
            propertyBooking.setBookingDate(rs.getDate("booking_date"));
            propertyBooking.setStatus(rs.getString("status"));
            propertyBooking.setRenter(renterDao.findByEmail(rs.getString("email")));
            propertyBooking.setCreditCard(creditCardDao.findById(rs.getString("card_no")));

            return propertyBooking;
        }
    }
}
