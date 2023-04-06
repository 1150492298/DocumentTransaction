package com.example.springboot.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RoleSearchRequest {

    private String token;

    private Integer userId;

    private Integer roleId;

    private String searchReason;

}
