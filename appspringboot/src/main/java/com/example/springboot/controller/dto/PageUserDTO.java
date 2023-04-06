package com.example.springboot.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PageUserDTO {
    private String roleName;
    private String departmentName;
    private Integer userId;
    private Integer roleId;
    private String userName;
    private Integer userSex;
    private String userPhone;
    private Integer departmentId;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date updateTime;
}
