package com.example.springboot.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserUpdateRequest {

    private Integer userId;

    private String userName;

    private String userIntro;

    private Integer userSex;

    private String userPhone;
}
