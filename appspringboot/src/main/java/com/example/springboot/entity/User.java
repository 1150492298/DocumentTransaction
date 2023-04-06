package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Data
public class User {
    private Integer userId;
    private Integer roleId;
    private String userName;
    private String userPwd;
    private String userAccount;
    private String userIntro;
    private Integer userSex;
    private String userPhone;
    private String userAddressList;
    private Integer userStatue;
    private Integer departmentId;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date updateTime;
}
