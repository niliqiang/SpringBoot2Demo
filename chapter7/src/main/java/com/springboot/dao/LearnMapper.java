package com.springboot.dao;

import com.springboot.entity.LearnResource;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface LearnMapper {
    @Insert("insert into learn_resource(author, title, url) values (#{author},#{title},#{url})")
    int add(LearnResource learnResource);

    @Delete("delete from learn_resource where id = #{id}")
    int delete(String id);

    @Select("select * from learn_resource order by id")
    List<LearnResource> learnResourceList();
}
