package com.group8.project.service;

import com.group8.project.dao.CreditCardDao;
import com.group8.project.domain.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardService {
    private final CreditCardDao creditCardDao;

    @Autowired
    public CreditCardService(CreditCardDao creditCardDao) {
        this.creditCardDao = creditCardDao;
    }

    public List<CreditCard> findAll() {
        return creditCardDao.findAll();
    }

    public CreditCard findByCardNumber(String cardNubmer) {
        return creditCardDao.findByCardNumber(cardNubmer);
    }

    public List<CreditCard> findByEmail(String email) {
        return creditCardDao.findByEmail(email);
    }

    public void save(CreditCard creditCard) {
        creditCardDao.save(creditCard);
    }

    public void update(CreditCard creditCard) {
        creditCardDao.update(creditCard);
    }

    public void deleteByCardNumber(String cardNubmer) {
        creditCardDao.deleteByCardNumber(cardNubmer);
    }

    public void deleteByEmail(String id) {
        creditCardDao.deleteByEmail(id);
    }
}
