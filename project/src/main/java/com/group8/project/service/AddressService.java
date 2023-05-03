package com.group8.project.service;

import com.group8.project.dao.AddressDao;
import com.group8.project.domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressDao addressDao;

    @Autowired
    public AddressService(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    public List<Address> findAll() {
        return addressDao.findAll();
    }

    public Address findById(String id) {
        return addressDao.findById(id);
    }

    public List<Address> findByEmail(String email) {
        return addressDao.findByEmail(email);
    }

    public void save(Address address) {
        addressDao.save(address);
    }

    public void update(Address address) {
        addressDao.update(address);
    }

    public void deleteById(String id) {
        addressDao.deleteById(id);
    }

    public void deleteByEmail(String id) {
        addressDao.deleteByEmail(id);
    }
}
