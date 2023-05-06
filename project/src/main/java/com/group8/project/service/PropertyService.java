package com.group8.project.service;

import com.group8.project.dao.PropertyDao;
import com.group8.project.domain.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
    private final PropertyDao propertyDao;

    @Autowired
    public PropertyService(PropertyDao propertyDao) {
        this.propertyDao = propertyDao;
    }

    public List<Property> findAll() {
        return propertyDao.findAll();
    }

    public Property findById(String id) {
        return propertyDao.findById(id);
    }

    public List<Property> findByEmail(String email) {
        return propertyDao.findByEmail(email);
    }

    public void save(Property Property) {
        propertyDao.save(Property);
    }

    public void update(Property Property) {
        propertyDao.update(Property);
    }

    public void deleteById(String id) {
        propertyDao.deleteById(id);
    }

    public void deleteByEmail(String email) {
        propertyDao.deleteByEmail(email);
    }
}
