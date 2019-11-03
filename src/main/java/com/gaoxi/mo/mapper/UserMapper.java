package com.gaoxi.mo.mapper;

import com.gaoxi.mo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values(#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified})")
    void insert(User user);
}

