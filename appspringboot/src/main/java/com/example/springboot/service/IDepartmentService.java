package com.example.springboot.service;

import com.example.springboot.controller.request.DepartmentPageRequest;
import com.example.springboot.entity.Department;

import java.util.List;

public interface IDepartmentService {

    List<Department> ListDepartments();

    List<Department> CountDepartment();

    void SaveDepartment(Department department);

    String getDepartmentNameById(Integer departmentId);

    Object pageDepartment(DepartmentPageRequest departmentPageRequest);

    Department getDepartmentById(Integer departmentId);

    void updateDepartmentById(Department department);
}
