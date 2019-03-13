package com.springboot.service;

import com.github.pagehelper.PageInfo;
import com.springboot.entity.LearnResource;

public interface LearnService {
    int add(LearnResource learnResource);
    int delete(String id);
    PageInfo<LearnResource> queryLearnResourceList(int page, int size);

}
