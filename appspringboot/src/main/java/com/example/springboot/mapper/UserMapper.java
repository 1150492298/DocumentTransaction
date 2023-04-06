package com.example.springboot.mapper;

import com.example.springboot.controller.dto.PageUserDTO;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.RegisterRequest;
import com.example.springboot.controller.request.UserPageRequest;
import com.example.springboot.controller.request.UserUpdateRequest;
import com.example.springboot.entity.*;
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> ListUsers();

    List<User> CountUser();

    void SaveUser(User user);

    List<PageUserDTO> PageUsers(UserPageRequest userPageRequest);

    User getUsersById(Integer userId);

    Integer getUsersByUserPhone(String userPhone);

    void updateUserById(User user);

    void updateUserRoleById(User user);

    void updateUserDepartmentById(User user);

    void delUserById(Integer userId);

    User UserLogin(LoginRequest loginRequest);

    void userUpdateUserByToken(UserUpdateRequest userUpdateRequest);

    void userRegister(RegisterRequest registerRequest);

    String userCheck(User user);

    String getUserAddressListById(Integer userId);

    void updateUserAddressListById(Integer userId,String userAddressList);

    void userResetPwd(User user);

    UserPhone getUserPhoneById(Integer userId);
}
