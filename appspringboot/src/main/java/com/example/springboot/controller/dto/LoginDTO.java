package com.example.springboot.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class LoginDTO {
    private Integer userId;
    private String userName;
    private String userAccount;
    private String userPhone;
    private String token;
}
