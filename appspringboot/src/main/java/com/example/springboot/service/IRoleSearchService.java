package com.example.springboot.service;

import com.example.springboot.controller.request.FindRoleSearchRequest;
import com.example.springboot.controller.request.RoleSearchRequest;
import com.example.springboot.entity.RoleRelation;
import com.example.springboot.entity.RoleSearch;

public interface IRoleSearchService {

    void addRoleSearch(RoleSearchRequest roleSearchRequest);

    void roleSearchManage(Object roleSearch);

    Object pageRoleSearch(FindRoleSearchRequest findRoleSearchRequest);

    void roleSearchReject(Object object);
}
