package com.example.configclient.dao;

import com.example.configclient.pojo.TestPojo;
import org.apache.ibatis.annotations.Param;

public interface TestDao {


    TestPojo findByName(@Param("name") String name);
}
