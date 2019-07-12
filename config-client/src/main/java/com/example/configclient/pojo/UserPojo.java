package com.example.configclient.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@TableName("tb_user")
@Data
public class UserPojo {

    private int id;
    private String userName;
    private Integer sex=0;

}
