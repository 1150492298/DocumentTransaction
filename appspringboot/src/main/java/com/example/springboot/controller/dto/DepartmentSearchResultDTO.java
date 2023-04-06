package com.example.springboot.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class DepartmentSearchResultDTO {

    private Integer departmentSearchId;

    private Integer userId;

    private Integer departmentId;

    private String searchReason;

    private String departmentName;

    private String userName;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date searchTime;

}

