package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.request.DepartmentPageRequest;
import com.example.springboot.entity.Department;
import com.example.springboot.entity.User;
import com.example.springboot.service.IDepartmentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    IDepartmentService departmentService;

    @GetMapping("/list")
    public Result ListDepartment(){
        List<Department> departments=departmentService.ListDepartments();
        return Result.success(departments);
    }

    @GetMapping("/listcount")
    public List<Department> CountDepartment(){
        return departmentService.CountDepartment();
    };

    @GetMapping("/pageDepartment")
    public Result pageDepartment(DepartmentPageRequest departmentPageRequest){
        return Result.success(departmentService.pageDepartment(departmentPageRequest));
    };

    @GetMapping("/getDepartmentById/{departmentId}")
    public Result getDepartmentById(@PathVariable Integer departmentId){
        return Result.success(departmentService.getDepartmentById(departmentId));
    };

    @PutMapping("/updateDepartmentById")
    public Result updateDepartmentById(@RequestBody Department department){
        departmentService.updateDepartmentById(department);
        return Result.success();
    }

    @PostMapping("/save")
    public Result SaveDepartment(@RequestBody Department departmentForm){
        if(StringUtils.isBlank(departmentForm.getDepartmentIntro())
                ||StringUtils.isBlank(departmentForm.getDepartmentName())){
            return Result.error("不得为空");
        }
        departmentService.SaveDepartment(departmentForm);
        return Result.success();
    }

}
