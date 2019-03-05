package com.springboot.dao.impl;

import com.springboot.dao.LearnDao;
import com.springboot.entity.LearnResource;
import com.springboot.tools.Page;
import com.springboot.tools.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class LearnDaoImpl implements LearnDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(LearnResource learnResource) {
        return jdbcTemplate.update("insert into learn_resource(author,title,url) values(?, ?, ?)",learnResource.getAuthor(),learnResource.getTitle(),learnResource.getUrl());
    }

    @Override
    public int delete(String id) {
        return jdbcTemplate.update("delete from learn_resource where id = ?", id);
    }

    @Override
    public Page queryLearnResourceList(Map<String,Object> params) {
        StringBuffer sql =new StringBuffer();
        sql.append("select * from learn_resource where 1=1");
        if(!StringUtil.isNull((String)params.get("author"))){
            sql.append(" and author like '%").append((String)params.get("author")).append("%'");
        }
        if(!StringUtil.isNull((String)params.get("title"))){
            sql.append(" and title like '%").append((String)params.get("title")).append("%'");
        }
        Page page = new Page(sql.toString(), Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()), jdbcTemplate);
        return page;
    }
}
