package com.example.configclient.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@TableName("tb_user")
@Data
public class TestPojo {

    private int id;
    private String user_name;
    private int sex;

}
