package com.example.springboot.controller.request;

import lombok.Data;

@Data
public class DepartmentSearchRequest {

    private String token;

    private Integer userId;

    private Integer departmentId;

    private String searchReason;
}
