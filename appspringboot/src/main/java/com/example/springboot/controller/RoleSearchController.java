package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.request.FindRoleSearchRequest;
import com.example.springboot.controller.request.RoleSearchRequest;
import com.example.springboot.controller.request.UserPageRequest;
import com.example.springboot.entity.RoleRelation;
import com.example.springboot.entity.RoleSearch;
import com.example.springboot.service.IRoleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/roleSearch")
public class RoleSearchController {

    @Autowired
    IRoleSearchService roleSearchService;



    @PostMapping("/addRoleSearch")
    public Result AddRoleSearch(@RequestBody RoleSearchRequest roleSearchRequest){
        roleSearchService.addRoleSearch(roleSearchRequest);
        return Result.success();
    }

    @PutMapping("/roleSearchManage")
    public Result roleSearchManage(@RequestBody Object object){
        roleSearchService.roleSearchManage(object);
        return Result.success();
    }

    @PutMapping("/roleSearchReject")
    public Result roleSearchReject(@RequestBody Object object){
        roleSearchService.roleSearchReject(object);
        return Result.success();
    }

    @GetMapping("/pageRoleSearch")
    public Result pageRoleSearch(FindRoleSearchRequest findRoleSearchRequest){
        return Result.success(roleSearchService.pageRoleSearch(findRoleSearchRequest));
    }


}

