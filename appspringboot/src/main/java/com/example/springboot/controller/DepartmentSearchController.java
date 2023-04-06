package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.request.FindDepartmentSearchRequest;
import com.example.springboot.controller.request.DepartmentSearchRequest;
import com.example.springboot.service.IDepartmentSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/departmentSearch")
public class DepartmentSearchController {

    @Autowired
    IDepartmentSearchService departmentSearchService;



    @PostMapping("/addDepartmentSearch")
    public Result AddDepartmentSearch(@RequestBody DepartmentSearchRequest departmentSearchRequest){
        departmentSearchService.addDepartmentSearch(departmentSearchRequest);
        return Result.success();
    }

    @PutMapping("/departmentSearchManage")
    public Result departmentSearchManage(@RequestBody Object object){
        departmentSearchService.departmentSearchManage(object);
        return Result.success();
    }

    @GetMapping("/pageDepartmentSearch")
    public Result pageDepartmentSearch(FindDepartmentSearchRequest findDepartmentSearchRequest){
        return Result.success(departmentSearchService.pageDepartmentSearch(findDepartmentSearchRequest));
    }

    @PutMapping("/departmentSearchReject")
    public Result departmentSearchReject(@RequestBody Object object){
//        System.out.println("丁真"+object);
        departmentSearchService.departmentSearchReject(object);
        return Result.success();
    }
}
