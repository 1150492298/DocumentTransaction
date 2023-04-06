package com.example.springboot.service.impl;

import com.example.springboot.controller.dto.PageUserDTO;
import com.example.springboot.controller.request.DepartmentPageRequest;
import com.example.springboot.entity.Department;
import com.example.springboot.mapper.DepartmentMapper;
import com.example.springboot.service.IDepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public List<Department> ListDepartments() {
        return departmentMapper.ListDepartments();
    }

    @Override
    public List<Department> CountDepartment() {
        return departmentMapper.CountDepartment();
    }

    @Override
    public void SaveDepartment(@RequestBody Department department) {
        departmentMapper.SaveDepartment(department);
    }

    @Override
    public String getDepartmentNameById(Integer departmentId) {
        return departmentMapper.getDepartmentNameById(departmentId);
    }

    @Override
    public Object pageDepartment(DepartmentPageRequest departmentPageRequest) {
        PageHelper.startPage(departmentPageRequest.getPageNum(), 5);
        List<Department> departments= departmentMapper.pageDepartment(departmentPageRequest);
        PageInfo<Department> departmentPageInfo = new PageInfo<>(departments);
        return departmentPageInfo;
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
        return departmentMapper.getDepartmentById(departmentId);
    }

    @Override
    public void updateDepartmentById(Department department) {
        departmentMapper.updateDepartmentById(department);
    }
}
