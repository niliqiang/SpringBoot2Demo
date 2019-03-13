package com.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.dao.LearnMapper;
import com.springboot.entity.LearnResource;
import com.springboot.service.LearnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearnServiceImpl implements LearnService {
    @Autowired
    LearnMapper learnMapper;

    @Override
    public int add(LearnResource learnResource) {
        return this.learnMapper.add(learnResource);
    }

    @Override
    public int delete(String id) {
        return this.learnMapper.delete(id);
    }

    @Override
    public PageInfo<LearnResource> queryLearnResourceList(int page, int size) {
        PageHelper.startPage(page, size);
        List<LearnResource> learnResourceList = learnMapper.learnResourceList();
        PageInfo<LearnResource> pageInfoDemo = new PageInfo<LearnResource>(learnResourceList);
        return pageInfoDemo;
    }
}
