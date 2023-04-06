package com.example.springboot.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RoleSearchResultDTO {

    private Integer roleSearchId;

    private Integer userId;

    private Integer roleId;

    private String searchReason;

    private String userName;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date searchTime;
}
