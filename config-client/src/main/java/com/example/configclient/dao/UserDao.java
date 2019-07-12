package com.example.configclient.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.configclient.pojo.UserPojo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao extends BaseMapper {

    /*@Insert("insert into tb_user(user_name,sex) values (#{userName},#{sex})")
    void insert(UserPojo pojo);*/

    List<UserPojo> selectList(@Param("page") Page<UserPojo> page, @Param("name") String name);
}
