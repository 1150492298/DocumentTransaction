package com.example.springboot.entity;

import lombok.Data;

@Data
public class RoleRelation {

    private Integer connectId;

    private Integer rightId;

    private Integer roleId;
}
