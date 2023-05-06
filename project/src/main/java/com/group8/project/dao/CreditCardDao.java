package com.group8.project.dao;

import com.group8.project.domain.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CreditCardDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private RenterDao renterDao;
    @Autowired
    private AddressDao addressDao;

    @Autowired
    public CreditCardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CreditCard> findAll() {
        String sql = "SELECT card_no, email, address_id, bank FROM creditcard";
        return jdbcTemplate.query(sql, new CreditCardDao.CreditCardWithAddressRowMapper());
    }

    public CreditCard findByCardNumber(String cardNumber) {
        String sql = "SELECT card_no, email, address_id, bank FROM creditcard WHERE card_no = ?";
        List<CreditCard> creditCardList = jdbcTemplate.query(sql, new CreditCardDao.CreditCardWithAddressRowMapper(), cardNumber);
        return creditCardList.isEmpty()? null : creditCardList.get(0);
    }

    public List<CreditCard> findByEmail(String email) {
        String sql = "SELECT card_no, email, address_id, bank FROM creditcard WHERE email = ?";
        return jdbcTemplate.query(sql, new CreditCardDao.CreditCardWithAddressRowMapper(), email);
    }

    public void save(CreditCard creditCard) {
        jdbcTemplate.update("INSERT INTO creditcard (card_no, email, address_id, bank) VALUES (?, ?, ?, ?)",
                creditCard.getCardNo(),
                creditCard.getRenter().getEmail(),
                creditCard.getAddress().getAddressId(),
                creditCard.getBank());
    }

    public void update(CreditCard creditCard) {
        jdbcTemplate.update("UPDATE creditcard SET card_no=?, email=?, address_id=?, bank=? WHERE card_no=?",
                creditCard.getCardNo(),
                creditCard.getRenter().getEmail(),
                creditCard.getAddress().getAddressId(),
                creditCard.getBank(),
                creditCard.getCardNo());
    }

    public void deleteByCardNumber(String cardNumber) {
        jdbcTemplate.update("DELETE FROM creditcard WHERE card_no=?", cardNumber);
    }

    public void deleteByEmail(String email) {
        jdbcTemplate.update("DELETE FROM creditcard WHERE email=?", email);
    }


    private class CreditCardWithAddressRowMapper implements RowMapper<CreditCard> {
        @Override
        public CreditCard mapRow(ResultSet rs, int rowNum) throws SQLException {
            CreditCard creditCard = new CreditCard();
            creditCard.setCardNo(rs.getString("card_no"));
            creditCard.setBank(rs.getString("bank"));
            creditCard.setRenter(renterDao.findByEmail(rs.getString("email")));
            creditCard.setAddress(addressDao.findById(rs.getString("address_id")));

            return creditCard;
        }
    }
}
