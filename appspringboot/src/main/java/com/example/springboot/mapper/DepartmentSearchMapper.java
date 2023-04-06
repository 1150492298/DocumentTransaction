package com.example.springboot.mapper;

import com.example.springboot.controller.dto.DepartmentSearchResultDTO;
import com.example.springboot.controller.request.FindDepartmentSearchRequest;
import com.example.springboot.controller.request.DepartmentSearchRequest;
import com.example.springboot.entity.DepartmentSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface DepartmentSearchMapper {

    void addDepartmentSearch(DepartmentSearchRequest departmentSearchRequest);

    Integer SelectDepartmentSearch(Integer userId);

    void departmentChangeSuccess(DepartmentSearch departmentSearch);

    List<DepartmentSearchResultDTO> pageDepartmentSearch(FindDepartmentSearchRequest findDepartmentSearchRequest);

    void departmentSearchReject(DepartmentSearch departmentSearch);
}
