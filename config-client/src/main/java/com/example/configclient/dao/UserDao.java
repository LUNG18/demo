package com.example.configclient.dao;

import com.example.configclient.pojo.UserPojo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    @Insert("insert into tb_user(user_name,sex) values (#{userName},#{sex})")
    void insert(UserPojo pojo);

    List<UserPojo> selectList(@Param("name") String name);
}
