package com.group8.project.service;

import com.group8.project.dao.RenterDao;
import com.group8.project.dao.UserDao;
import com.group8.project.domain.Renter;
import com.group8.project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RenterService {

    private final RenterDao renterDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    public RenterService(RenterDao renterDao) {
        this.renterDao = renterDao;
    }

    public List<Renter> findAll() {
        return renterDao.findAll();
    }

    public void save(Renter renter) {
        if (null != renter.getUser()) {
            User user = userDao.findByEmail(renter.getEmail());
            if (null == user) {
                userDao.save(renter.getUser());
            }
            renterDao.save(renter);
        }
    }

    public void update(Renter renter) {
        renterDao.update(renter);
    }

    public void deleteByEmail(String email) {
        renterDao.deleteByEmail(email);
    }

    public Renter getByEmail(String email) {
        Renter renter = renterDao.findByEmail(email);
        if (null != renter) {
            User user = userDao.findByEmail(email);
            renter.setUser(user);
        }
        return renter;
    }
}
