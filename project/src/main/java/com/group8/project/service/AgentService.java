package com.group8.project.service;

import com.group8.project.dao.AgentDao;
import com.group8.project.domain.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {

    private final AgentDao agentDao;

    @Autowired
    public AgentService(AgentDao agentDao) {
        this.agentDao = agentDao;
    }

    public Agent findByEmail(String email) {
        return agentDao.findByEmail(email);
    }

    public List<Agent> findAll() {
        return agentDao.findAll();
    }

    public void save(Agent agent) {
        agentDao.save(agent);
    }

    public void update(Agent agent) {
        agentDao.update(agent);
    }

    public void deleteByEmail(String email) {
        agentDao.deleteByEmail(email);
    }
}
