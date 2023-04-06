package com.example.springboot.mapper;

import com.example.springboot.entity.Rights;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RightsMapper {

    @Select("select * from rights")
    List<Rights> ListRights();

    String checkUserRight(Integer userId,Integer rightId);
}
