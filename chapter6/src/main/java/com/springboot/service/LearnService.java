package com.springboot.service;

import com.springboot.entity.LearnResource;
import com.springboot.tools.Page;

import java.util.Map;

public interface LearnService {
    int add(LearnResource learnResource);
    int delete(String id);
    Page queryLearnResourceList(Map<String,Object> params);

}
