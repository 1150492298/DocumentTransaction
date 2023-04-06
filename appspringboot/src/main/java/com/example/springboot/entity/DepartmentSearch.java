package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class DepartmentSearch {

    private Integer departmentSearchId;

    private Integer userId;

    private Integer departmentId;

    private Integer manageId;

    private String searchReason;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date searchTime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date manageTime;
}
