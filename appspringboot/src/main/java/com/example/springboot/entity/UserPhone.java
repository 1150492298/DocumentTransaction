package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserPhone {
    private Integer userId;
    private Integer roleId;
    private String userName;
    private String departmentName;
    private String roleName;
    private Integer userSex;
    private String userPhone;
    private Integer departmentId;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date updateTime;
}
