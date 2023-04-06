package com.example.springboot.service.impl;

import com.example.springboot.controller.request.RoleRequest;
import com.example.springboot.entity.Role;
import com.example.springboot.entity.Role;
import com.example.springboot.mapper.RoleMapper;
import com.example.springboot.service.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RoleService implements IRoleService {

    @Autowired
    RoleMapper roleMapper;


    @Override
    public List<Role> listRoles() {
        return roleMapper.listRoles();
    }

    @Override
    public Object PageRoles(RoleRequest roleRequest) {
        PageHelper.startPage(roleRequest.getPageNum(), 5);
        List<Role> roles = roleMapper.PageRoles(roleRequest);
        PageInfo<Role> rolesPageInfo = new PageInfo<>(roles);
        return rolesPageInfo;
    }

    @Override
    public void saveRole(Role role) {
        roleMapper.saveRole(role);
    }

    @Override
    public void updateRoleById(Role role) {
        roleMapper.updateRoleById(role);
    }

    @Override
    public Role getRolesById(Integer roleId) {
        return roleMapper.getRolesById(roleId);
    }

    @Override
    public String getRoleNameById(Integer roleId) {
        return roleMapper.getRoleNameById(roleId);
    }

    @Override
    public void delRoleById(Integer id) {
        roleMapper.delRoleById(id);
    }
}
