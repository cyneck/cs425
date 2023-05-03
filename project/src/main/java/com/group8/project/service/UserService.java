package com.group8.project.service;

import com.group8.project.dao.UserDao;
import com.group8.project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public void save(User user) {
        userDao.save(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void deleteByEmail(String email) {
        userDao.deleteByEmail(email);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }
}
