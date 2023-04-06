package com.example.springboot.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserAboutDTO {
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;
    private Integer roleId;
    private String userName;
    private String userAccount;
    private String userIntro;
    private Integer userSex;
    private String userPhone;
    private Integer departmentId;
    private String departmentName;
    private String roleName;
}
