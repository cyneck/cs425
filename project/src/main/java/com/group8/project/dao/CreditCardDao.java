package com.group8.project.dao;

import com.group8.project.domain.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CreditCardDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CreditCardDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public CreditCard findById(String cardNo) {
        String sql = "SELECT * FROM CreditCard WHERE card_no = ?";
        List<CreditCard> CreditCardes = jdbcTemplate.query(sql, new CreditCardRowMapper(), cardNo);
        return CreditCardes.isEmpty() ? null : CreditCardes.get(0);
    }

    public List<CreditCard> findAll() {
        String sql = "SELECT * FROM CreditCard";
        return jdbcTemplate.query(sql, new CreditCardRowMapper());
    }

    public List<CreditCard> findByEmail(String email) {
        String sql = "SELECT * FROM CreditCard WHERE email = ?";
        return jdbcTemplate.query(sql, new CreditCardRowMapper(), email);
    }

    public void save(CreditCard creditCard) {
        String sql = "INSERT INTO CreditCard (card_no, email, address_id, bank) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, creditCard.getCardNo(), creditCard.getEmail(), creditCard.getAddressId(),creditCard.getBank());
    }

    public void update(CreditCard creditCard) {
        String sql = "UPDATE CreditCard SET email = ?, address_id = ?, bank = ? WHERE card_no = ?";
        jdbcTemplate.update(sql, creditCard.getEmail(), creditCard.getAddressId(),creditCard.getBank(),creditCard.getCardNo());
    }

    public void deleteById(String cardNo) {
        String sql = "DELETE FROM CreditCard WHERE card_no = ?";
        jdbcTemplate.update(sql, cardNo);
    }

    public void deleteByEmail(String email) {
        String sql = "DELETE FROM CreditCard WHERE email = ?";
        jdbcTemplate.update(sql, email);
    }

    private static class CreditCardRowMapper implements RowMapper<CreditCard> {
        @Override
        public CreditCard mapRow(ResultSet rs, int rowNum) throws SQLException {
            String cardNo = rs.getString("card_no");
            String email = rs.getString("email");
            String addressId = rs.getString("address_id");
            String bank = rs.getString("bank");
            return new CreditCard(cardNo, email, addressId, bank);
        }
    }
}
