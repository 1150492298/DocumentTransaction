package com.example.springboot.mapper;

import com.example.springboot.controller.dto.RoleSearchResultDTO;
import com.example.springboot.controller.request.FindRoleSearchRequest;
import com.example.springboot.controller.request.RoleSearchRequest;
import com.example.springboot.entity.RoleSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface RoleSearchMapper {

    void addRoleSearch(RoleSearchRequest roleSearchRequest);

    Integer SelectRoleSearch(Integer userId);

    void roleChangeSuccess(RoleSearch roleSearch);

    List<RoleSearchResultDTO> pageRoleSearch(FindRoleSearchRequest findRoleSearchRequest);

    void roleSearchReject(RoleSearch roleSearch);

}
