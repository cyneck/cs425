package com.group8.project.service;

import com.group8.project.dao.AddressDao;
import com.group8.project.dao.CreditCardDao;
import com.group8.project.domain.Address;
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

    public CreditCard findById(String id) {
        return creditCardDao.findById(id);
    }

    public List<CreditCard> findByEmail(String email) {
        return creditCardDao.findByEmail(email);
    }

    public void save(CreditCard address) {
        creditCardDao.save(address);
    }

    public void update(CreditCard address) {
        creditCardDao.update(address);
    }

    public void deleteById(String id) {
        creditCardDao.deleteById(id);
    }

    public void deleteByEmail(String id) {
        creditCardDao.deleteByEmail(id);
    }
}
