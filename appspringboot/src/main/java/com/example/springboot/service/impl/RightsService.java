package com.example.springboot.service.impl;

import com.example.springboot.entity.Rights;
import com.example.springboot.mapper.DepartmentMapper;
import com.example.springboot.mapper.RightsMapper;
import com.example.springboot.service.IRightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RightsService implements IRightsService {

    @Autowired
    RightsMapper rightsMapper;

    @Override
    public List<Rights> ListRights() {
        return rightsMapper.ListRights();
    }
}
