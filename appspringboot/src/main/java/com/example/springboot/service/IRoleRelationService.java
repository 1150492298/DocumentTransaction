package com.example.springboot.service;

import com.example.springboot.entity.Rights;
import com.example.springboot.entity.RoleRelation;

import java.util.List;

public interface IRoleRelationService {

    List<RoleRelation> listRoleRelations();

    void saveRoleRelation(Object roleRelationForm);

    void updateRoleRelationById(RoleRelation roleRelation);

    List<RoleRelation> getRightByRoleId(Integer roleId);

    void delRoleRelationById(Integer id);

    List<Rights> getRightDetailByRoleId(Integer roleId);

    void AddRoleRelation(Object roleRelationForm);
}
