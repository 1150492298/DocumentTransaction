package com.example.springboot.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RegisterRequest {

    private String userName;
    private String userPwd;
    private String userAccount;
    private Integer userSex;
    private String userPhone;

}
