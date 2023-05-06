package com.group8.project.service;

import com.group8.project.dao.AgentDao;
import com.group8.project.dao.UserDao;
import com.group8.project.domain.Agent;
import com.group8.project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;

@Service
public class AgentService {

    private final AgentDao agentDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    public AgentService(AgentDao agentDao) {
        this.agentDao = agentDao;
    }

    public Agent findByEmail(String email) {
        Agent agent = agentDao.findByEmailWithoutJoin(email);
        if (null != agent) {
            User user = userDao.findByEmail(email);
            agent.setUser(user);
        }
        return agent;
    }

    public List<Agent> findAll() {
        return agentDao.findAll();
    }

    public void save(Agent agent) {
        if (null != agent.getUser()) {
            User user = userDao.findByEmail(agent.getEmail());
            if (null == user) {
                userDao.save(agent.getUser());
            }
            agentDao.save(agent);
        }
    }

    @Transient
    public void update(Agent agent) {
        if (null != agent.getUser()) {
            userDao.update(agent.getUser());
        }
        agentDao.update(agent);
    }

    public void deleteByEmail(String email) {
        agentDao.deleteByEmail(email);
    }
}
