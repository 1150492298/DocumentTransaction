package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RoleSearch {

    private Integer roleSearchId;

    private Integer userId;

    private Integer roleId;

    private Integer manageId;

    private String searchReason;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date searchTime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date manageTime;

}
