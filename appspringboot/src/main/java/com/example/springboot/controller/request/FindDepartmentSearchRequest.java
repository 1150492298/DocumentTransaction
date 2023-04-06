package com.example.springboot.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class FindDepartmentSearchRequest extends BaseRequest{

    private Integer departmentId;

    private String userName;

    private String searchTime;
}
