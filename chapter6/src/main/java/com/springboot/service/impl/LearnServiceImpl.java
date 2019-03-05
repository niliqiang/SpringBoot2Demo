package com.springboot.service.impl;

import com.springboot.dao.LearnDao;
import com.springboot.entity.LearnResource;
import com.springboot.service.LearnService;
import com.springboot.tools.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LearnServiceImpl implements LearnService {
    @Autowired
    LearnDao learnDao;
    @Override
    public int add(LearnResource learnResource) {
        return this.learnDao.add(learnResource);
    }

    @Override
    public int delete(String id) {
        return this.learnDao.delete(id);
    }

    @Override
    public Page queryLearnResourceList(Map<String,Object> params) {
        return this.learnDao.queryLearnResourceList(params);
    }
}
