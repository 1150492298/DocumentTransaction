package com.example.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.springboot.controller.dto.RoleSearchResultDTO;
import com.example.springboot.controller.request.FindRoleSearchRequest;
import com.example.springboot.controller.request.RoleSearchRequest;
import com.example.springboot.entity.Role;
import com.example.springboot.entity.RoleSearch;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.RoleMapper;
import com.example.springboot.mapper.RoleSearchMapper;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.IRoleSearchService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class RoleSearchService implements IRoleSearchService {


    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleSearchMapper roleSearchMapper;

    @Autowired
    RoleMapper roleMapper;


    @Override
    public void addRoleSearch(RoleSearchRequest roleSearchRequest) {
        Integer userId = Integer.parseInt(JWT.decode(roleSearchRequest.getToken()).getAudience().get(0));
        Integer roleSearchId=roleSearchMapper.SelectRoleSearch(userId);
        if (roleSearchId != null) {
            throw new ServiceException("已发出权限申请，请静待审核");
        }
        roleSearchRequest.setUserId(userId);
        roleSearchMapper.addRoleSearch(roleSearchRequest);
    }

    @Override
    public void roleSearchManage(Object object) {
        Map entity = (Map) object;
        String token = (String) entity.get("token");
        Integer adminId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
        String roleSearchString = JSON.toJSONString(entity.get("roleSearch")) ;
        RoleSearch roleSearch= JSONObject.parseObject(roleSearchString,RoleSearch.class);
        Integer adminWeight=roleMapper.getRoleWeightByUserId(adminId);
        Integer userWeight=roleMapper.getRoleWeightByUserId(roleSearch.getUserId());
        System.out.println("修改者："+adminWeight+"被改者:"+userWeight);
        if (adminWeight<=userWeight) {
            throw new ServiceException("您的权限等级不足，无法批准该用户申请！");
        }
        User user=new User();
        user.setUserId(roleSearch.getUserId());
        user.setRoleId(roleSearch.getRoleId());
        userMapper.updateUserRoleById(user);
        roleSearch.setManageTime(new Date());
        roleSearch.setManageId(adminId);
        roleSearchMapper.roleChangeSuccess(roleSearch);
    }

    @Override
    public Object pageRoleSearch(FindRoleSearchRequest findRoleSearchRequest) {
        Integer adminId = Integer.parseInt(JWT.decode(findRoleSearchRequest.getToken()).getAudience().get(0));
        Integer roleWeight=roleMapper.getRoleWeightByUserId(adminId);
        findRoleSearchRequest.setRoleWeight(roleWeight);
        PageHelper.startPage(findRoleSearchRequest.getPageNum(), 5);
        List<RoleSearchResultDTO> roleSearchResultDTOS = roleSearchMapper.pageRoleSearch(findRoleSearchRequest);
        PageInfo<RoleSearchResultDTO> roleSearchResultDTOPageInfo = new PageInfo<>(roleSearchResultDTOS);
        return roleSearchResultDTOPageInfo;
    }

    @Override
    public void roleSearchReject(Object object) {
        Map entity = (Map) object;
        String token = (String) entity.get("token");
        Integer adminId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
        String roleSearchString = JSON.toJSONString(entity.get("roleSearch")) ;
        RoleSearch roleSearch= JSONObject.parseObject(roleSearchString,RoleSearch.class);
        Integer adminWeight=roleMapper.getRoleWeightByUserId(adminId);
        Integer userWeight=roleMapper.getRoleWeightByUserId(roleSearch.getUserId());
        System.out.println("修改者："+adminWeight+"被改者:"+userWeight);
        if (adminWeight<=userWeight) {
            throw new ServiceException("您的权限等级不足，无法拒绝该用户申请！");
        }
        roleSearch.setManageTime(new Date());
        roleSearch.setManageId(adminId);
        roleSearchMapper.roleSearchReject(roleSearch);
    }

}
