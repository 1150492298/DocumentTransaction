package com.example.springboot.service;

import com.example.springboot.controller.request.FindDepartmentSearchRequest;
import com.example.springboot.controller.request.DepartmentSearchRequest;

public interface IDepartmentSearchService {

    void addDepartmentSearch(DepartmentSearchRequest departmentSearchRequest);

    void departmentSearchManage(Object departmentSearch);

    Object pageDepartmentSearch(FindDepartmentSearchRequest findDepartmentSearchRequest);

    void departmentSearchReject(Object object);
}
