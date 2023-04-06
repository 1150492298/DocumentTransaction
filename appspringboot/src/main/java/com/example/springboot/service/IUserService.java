package com.example.springboot.service;

import com.example.springboot.controller.dto.LoginDTO;
import com.example.springboot.controller.dto.UserAboutDTO;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.RegisterRequest;
import com.example.springboot.controller.request.UserPageRequest;
import com.example.springboot.entity.User;

import java.util.List;

public interface IUserService {

    List<User> ListUsers();

    List<User> CountUser();

    void SaveUser(User user);

    Object PageUsers(UserPageRequest userPageRequest);

    User getUsersById(Integer userId);

    void updateUserById(User user);

    void delUserById(Integer userId);

    LoginDTO UserLogin(LoginRequest loginRequest);

    String userRegister(RegisterRequest registerRequest);

    UserAboutDTO userGetUsersByToken(String token);

    void userUpdateUserByToken(Object userForm);

    void userResetPwd(Object userForm);

    void adminResetUserPwd(Object userForm);

    Object getUserMailList(String token);

    void addUserMailList(Object object);

    void deleteUserMailList(Object object);
}
