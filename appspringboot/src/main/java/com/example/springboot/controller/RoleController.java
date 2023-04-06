package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.RoleRequest;
import com.example.springboot.controller.request.UserPageRequest;
import com.example.springboot.entity.Role;
import com.example.springboot.service.IRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @GetMapping("/listRole")
    public Result ListRole(){
        List<Role> roles=roleService.listRoles();
        return Result.success(roles);
    }

    @GetMapping("/pageRoles")
    public Result PageRoles(RoleRequest roleRequest){
        return Result.success(roleService.PageRoles(roleRequest));
    }

    @PostMapping("/saveRole")
    public Result SaveRole(@RequestBody Role roleForm){
        roleService.saveRole(roleForm);
        return Result.success();
    }


    @GetMapping("/getRoleById/{id}")
    public Result getRoleById(@PathVariable Integer id){
        return Result.success(roleService.getRolesById(id));
    }


    @PutMapping("/updateRoleById")
    public Result updateRoleById(@RequestBody Role roleForm){
        roleService.updateRoleById(roleForm);
        return Result.success();
    }

    @DeleteMapping("/delRoleById/{id}")
    public Result delUserById(@PathVariable Integer id){
        roleService.delRoleById(id);
        return Result.success();
    }
}

