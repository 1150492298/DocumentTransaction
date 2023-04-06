package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.RegisterRequest;
import com.example.springboot.controller.request.UserPageRequest;
import com.example.springboot.entity.User;
import com.example.springboot.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/list")
    public Result ListUser(){
        List<User> users=userService.ListUsers();
        return Result.success(users);
    }

    @GetMapping("/listcount")
    public List<User> CountUser(){
        return userService.CountUser();
    };

    @PostMapping("/save")
    public Result SaveUser(@RequestBody User userForm){
        if(StringUtils.isBlank(userForm.getUserName())){
            return Result.error("不得为空");
        }
        userService.SaveUser(userForm);
        return Result.success();
    }

    @GetMapping("/pageUsers")
    public Result PageUsers(UserPageRequest userPageRequest){
        return Result.success(userService.PageUsers(userPageRequest));
    }

    @GetMapping("/adminGetUserById/{id}")
    public Result adminGetUserById(@PathVariable Integer id){
        return Result.success(userService.getUsersById(id));
    }

    @GetMapping("/userGetUserByToken/{Token}")
    public Result userGetUsersByToken(@PathVariable String Token){
        return Result.success(userService.userGetUsersByToken(Token));
    }

    @GetMapping("/getUserMailList/{Token}")
    public Result getUserMailList(@PathVariable String Token){
        return Result.success(userService.getUserMailList(Token));
    }

    @PutMapping("/addUserMailList")
    public Result addUserMailList(@RequestBody Object object){
        userService.addUserMailList(object);
        return Result.success();
    }

    @PutMapping("/deleteUserMailList")
    public Result deleteUserMailList(@RequestBody Object object){
        userService.deleteUserMailList(object);
        return Result.success();
    }

    @PutMapping("/adminUpdateUserById")
    public Result adminUpdateUserById(@RequestBody User userForm){
        userService.updateUserById(userForm);
        return Result.success();
    }

    @PutMapping("/userUpdateUserByToken")
    public Result userUpdateUserByToken(@RequestBody Object userForm){
        userService.userUpdateUserByToken(userForm);
        return Result.success();
    }

    @DeleteMapping("/delUserById/{userId}")
    public Result delUserById(@PathVariable Integer userId){
        userService.delUserById(userId);
        return Result.success();
    }

    @PostMapping("/userLogin")
    public Result UserLogin(@RequestBody LoginRequest loginRequest){
        if(StringUtils.isBlank(loginRequest.getUserAccount())||StringUtils.isBlank(loginRequest.getUserPwd())){
            return Result.error("不得为空");
        }
        return Result.success(userService.UserLogin(loginRequest));
    }

    @PostMapping("/userRegister")
    public Result userRegister(@RequestBody RegisterRequest registerRequest){
        return Result.success(userService.userRegister(registerRequest));
    }

    @PutMapping("/userResetPwd")
    public Result userResetPwd(@RequestBody Object userForm){
        userService.userResetPwd(userForm);
        return Result.success();
    }

    @PutMapping("/adminResetUserPwd")
    public Result adminResetUserPwd(@RequestBody Object userForm){
        userService.adminResetUserPwd(userForm);
        return Result.success();
    }
}
