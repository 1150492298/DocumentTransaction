package com.example.springboot.mapper;

import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.RoleRequest;
import com.example.springboot.entity.Department;
import com.example.springboot.entity.Role;
import com.example.springboot.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    List<Role> listRoles();

    void saveRole(Role role);

    Role getRolesById(Integer roleId);

    void updateRoleById(Role role);

    void delRoleById(Integer roleId);

    List<Role> PageRoles(RoleRequest roleRequest);

    String getRoleNameById(Integer roleId);

    Integer getRoleWeightByUserId(Integer userId);
}

