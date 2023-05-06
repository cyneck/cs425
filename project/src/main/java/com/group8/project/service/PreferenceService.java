package com.group8.project.service;

import com.group8.project.dao.PreferenceDao;
import com.group8.project.domain.Preference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreferenceService {
    private final PreferenceDao preferenceDao;

    @Autowired
    public PreferenceService(PreferenceDao preferenceDao) {
        this.preferenceDao = preferenceDao;
    }

    public List<Preference> findAll() {
        return preferenceDao.findAll();
    }

    public Preference findByEmail(String email) {
        return preferenceDao.findByEmail(email);
    }

    public void save(Preference preference) {
        preferenceDao.save(preference);
    }

    public void update(Preference preference) {
        preferenceDao.update(preference);
    }

    public void saveOrUpdate(Preference preference) {
        if (null == findByEmail(preference.getEmail())) {
            preferenceDao.save(preference);
        }
        preferenceDao.update(preference);
    }

    public void delete(String email) {
        preferenceDao.delete(email);
    }
}
