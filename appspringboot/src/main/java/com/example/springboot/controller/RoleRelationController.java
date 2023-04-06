package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.entity.RoleRelation;
import com.example.springboot.service.IRoleRelationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roleRelation")
public class RoleRelationController {

    @Autowired
    IRoleRelationService roleRelationService;
    

    @PostMapping("/saveRoleRelation")
    public Result SaveRoleRelation(@RequestBody Object roleRelationForm){
        roleRelationService.saveRoleRelation(roleRelationForm);
        return Result.success();
    }

    @PostMapping("/addRoleRelation")
    public Result AddRoleRelation(@RequestBody Object roleRelationForm){
        roleRelationService.AddRoleRelation(roleRelationForm);
        return Result.success();
    }
    

    @GetMapping("/getRightByRoleId/{roleId}")
    public Result getRightByRoleId(@PathVariable Integer roleId){
        return Result.success(roleRelationService.getRightByRoleId(roleId));
    }

    @GetMapping("/getRightDetailByRoleId/{roleId}")
    public Result getRightDetailByRoleId(@PathVariable Integer roleId){
        return Result.success(roleRelationService.getRightDetailByRoleId(roleId));
    }
    

    @PutMapping("/updateRoleRelationById")
    public Result updateRoleRelationById(@RequestBody RoleRelation roleRelationForm){
        roleRelationService.updateRoleRelationById(roleRelationForm);
        return Result.success();
    }

    @DeleteMapping("/delRoleRelationById/{rolerelationId}")
    public Result delRoleRelationById(@PathVariable Integer roleRelationId){
        roleRelationService.delRoleRelationById(roleRelationId);
        return Result.success();
    }

}

