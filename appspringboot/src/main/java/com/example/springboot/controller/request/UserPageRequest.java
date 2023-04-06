package com.example.springboot.controller.request;

import lombok.Data;

@Data
public class UserPageRequest extends BaseRequest{
    private String userName;
    private String updateTime;
    private String userPhone;
    private Integer departmentId;
    private Integer roleId;
}
