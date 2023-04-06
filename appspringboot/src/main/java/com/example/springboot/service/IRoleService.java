package com.example.springboot.service;

import com.example.springboot.common.Result;
import com.example.springboot.controller.request.RoleRequest;
import com.example.springboot.entity.Role;

import java.util.List;

public interface IRoleService {

    List<Role> listRoles();

    Object PageRoles(RoleRequest roleRequest);

    void saveRole(Role role);

    void updateRoleById(Role role);

    Role getRolesById(Integer roleId);

    String getRoleNameById(Integer roleId);

    void delRoleById(Integer id);
}
