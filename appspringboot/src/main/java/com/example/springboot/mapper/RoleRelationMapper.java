package com.example.springboot.mapper;

import com.example.springboot.entity.Rights;
import com.example.springboot.entity.RoleRelation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleRelationMapper {

    List<RoleRelation> listRoleRelations();

    void saveRoleRelation(Integer rightId,Integer roleId);

    List<RoleRelation> getRightByRoleId(Integer roleId);

    void updateRoleRelationById(RoleRelation roleRelation);

    void delRoleRelationById(Integer roleId);

    List<Rights> getRightDetailByRoleId(Integer roleId);
}
