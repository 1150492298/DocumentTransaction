package com.example.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.controller.request.RoleRequest;
import com.example.springboot.entity.Rights;
import com.example.springboot.entity.Role;
import com.example.springboot.entity.RoleRelation;
import com.example.springboot.mapper.RoleMapper;
import com.example.springboot.mapper.RoleRelationMapper;
import com.example.springboot.service.IRoleRelationService;
import com.example.springboot.service.IRoleService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RoleRelationService implements IRoleRelationService {

    @Autowired
    RoleRelationMapper roleRelationMapper;
    @Autowired
    RoleMapper roleMapper;


    @Override
    public List<RoleRelation> listRoleRelations() {
        return null;
    }

    @Override
    public void saveRoleRelation(Object object) {
        Map entity = (Map) object;
        ArrayList roleRelationForm = (ArrayList) entity.get("roleRelationForm");
        Integer roleId = (Integer) entity.get("roleId");
        roleRelationMapper.delRoleRelationById(roleId);
        for (Object rightId : roleRelationForm) {
            roleRelationMapper.saveRoleRelation((Integer) rightId, roleId);
        }
    }

    @Override
    public void updateRoleRelationById(RoleRelation roleRelation) {

    }

    @Override
    public List<RoleRelation> getRightByRoleId(Integer roleId) {
        return roleRelationMapper.getRightByRoleId(roleId);
    }

    @Override
    public void delRoleRelationById(Integer id) {

    }

    @Override
    public List<Rights> getRightDetailByRoleId(Integer roleId) {
        return  roleRelationMapper.getRightDetailByRoleId(roleId);
    }

    @Override
    public void AddRoleRelation(Object roleRelationForm) {
        Map entity = (Map) roleRelationForm;
        ArrayList list = (ArrayList) entity.get("roleRelationForm");
        String roleString = JSON.toJSONString(entity.get("roleForm")) ;
        Role role= JSONObject.parseObject(roleString,Role.class);
        roleMapper.saveRole(role);
        Integer roleId=role.getRoleId();
        System.out.println("最新id"+roleId);
//        roleRelationMapper.delRoleRelationById(roleId);
        for (Object rightId : list) {
            roleRelationMapper.saveRoleRelation((Integer) rightId, roleId);
        }
    }
}
