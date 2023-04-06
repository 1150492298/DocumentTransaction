package com.example.springboot.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class FindRoleSearchRequest extends BaseRequest{

    private String token;

    private Integer roleWeight;

    private Integer roleId;

    private String userName;

    private String searchTime;

}
