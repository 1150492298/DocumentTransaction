package com.example.springboot.controller.request;

import lombok.Data;

@Data
public class DepartmentPageRequest extends BaseRequest{
    private String departmentName;
}
