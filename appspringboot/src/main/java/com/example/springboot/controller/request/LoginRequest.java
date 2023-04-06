package com.example.springboot.controller.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String userAccount;
    private String userPwd;
}
