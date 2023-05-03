package com.group8.project.service;

import com.group8.project.dao.RenterDao;
import com.group8.project.domain.Renter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RenterService {

    private final RenterDao renterDao;

    @Autowired
    public RenterService(RenterDao renterDao) {
        this.renterDao = renterDao;
    }

    public List<Renter> findAll() {
        return renterDao.findAll();
    }

    public void save(Renter renter) {
        renterDao.save(renter);
    }

    public void update(Renter renter) {
        renterDao.update(renter);
    }

    public void deleteByEmail(String email) {
        renterDao.deleteByEmail(email);
    }

    public Renter getByEmail(String email) {
        return renterDao.findByEmail(email);
    }
}
