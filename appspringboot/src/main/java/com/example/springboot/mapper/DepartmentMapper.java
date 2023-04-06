package com.example.springboot.mapper;


import com.example.springboot.controller.request.DepartmentPageRequest;
import com.example.springboot.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    @Select("select * from department")
    List<Department> ListDepartments();

    List<Department> CountDepartment();

    void SaveDepartment(Department department);

    String getDepartmentNameById(Integer departmentId);

    List<Department> pageDepartment(DepartmentPageRequest departmentPageRequest);

    Department getDepartmentById(Integer id);

    void updateDepartmentById(Department department);
}
