package com.example.springboot.controller;

import com.example.springboot.entity.Rights;
import com.example.springboot.service.impl.RightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@CrossOrigin
@RequestMapping("/right")
public class RightController {

    @Autowired
    RightsService rightsService;

    @GetMapping("/list")
    public List<Rights> ListRights(){
        return rightsService.ListRights();
    }
}
