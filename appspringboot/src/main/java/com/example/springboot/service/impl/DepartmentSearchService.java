package com.example.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.springboot.controller.dto.DepartmentSearchResultDTO;
import com.example.springboot.controller.request.FindDepartmentSearchRequest;
import com.example.springboot.controller.request.DepartmentSearchRequest;
import com.example.springboot.entity.DepartmentSearch;
import com.example.springboot.entity.RoleSearch;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.DepartmentMapper;
import com.example.springboot.mapper.DepartmentSearchMapper;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.IDepartmentSearchService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class DepartmentSearchService implements IDepartmentSearchService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    DepartmentSearchMapper departmentSearchMapper;

    @Autowired
    DepartmentMapper departmentMapper;


    @Override
    public void addDepartmentSearch(DepartmentSearchRequest departmentSearchRequest) {
        Integer userId = Integer.parseInt(JWT.decode(departmentSearchRequest.getToken()).getAudience().get(0));
        Integer departmentSearchId=departmentSearchMapper.SelectDepartmentSearch(userId);
        if (departmentSearchId != null) {
            throw new ServiceException("已发出权限申请，请静待审核");
        }
        departmentSearchRequest.setUserId(userId);
        departmentSearchMapper.addDepartmentSearch(departmentSearchRequest);
    }

    @Override
    public void departmentSearchManage(Object object) {
        Map entity = (Map) object;
        String token = (String) entity.get("token");
        Integer adminId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
        String departmentSearchString = JSON.toJSONString(entity.get("departmentSearch")) ;
        DepartmentSearch departmentSearch= JSONObject.parseObject(departmentSearchString,DepartmentSearch.class);
        User user=new User();
        user.setUserId(departmentSearch.getUserId());
        user.setDepartmentId(departmentSearch.getDepartmentId());
        userMapper.updateUserDepartmentById(user);
        departmentSearch.setManageTime(new Date());
        departmentSearch.setManageId(adminId);
        departmentSearchMapper.departmentChangeSuccess(departmentSearch);
    }

    @Override
    public Object pageDepartmentSearch(FindDepartmentSearchRequest findDepartmentSearchRequest) {
        PageHelper.startPage(findDepartmentSearchRequest.getPageNum(), 5);
        List<DepartmentSearchResultDTO> departmentSearchResultDTOS = departmentSearchMapper.pageDepartmentSearch(findDepartmentSearchRequest);
        PageInfo<DepartmentSearchResultDTO> departmentSearchResultDTOPageInfo = new PageInfo<>(departmentSearchResultDTOS);
        return departmentSearchResultDTOPageInfo;
    }

    @Override
    public void departmentSearchReject(Object object) {
        Map entity = (Map) object;
        String token = (String) entity.get("token");
        Integer adminId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
        String departmentSearchString = JSON.toJSONString(entity.get("departmentSearch")) ;
        DepartmentSearch departmentSearch= JSONObject.parseObject(departmentSearchString,DepartmentSearch.class);
        departmentSearch.setManageTime(new Date());
        departmentSearch.setManageId(adminId);
        departmentSearchMapper.departmentSearchReject(departmentSearch);
    }
}
