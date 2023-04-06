package com.example.springboot.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DocumentClass {

    private Integer documentClassId;

    private Integer documentProcessCount;

    private String documentDepartmentRoleString;

    private String documentClassName;

    private String documentClassDescribe;


}
