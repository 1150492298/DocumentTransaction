package com.example.springboot.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.LoginDTO;
import com.example.springboot.controller.dto.PageUserDTO;
import com.example.springboot.controller.dto.UserAboutDTO;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.RegisterRequest;
import com.example.springboot.controller.request.UserPageRequest;
import com.example.springboot.controller.request.UserUpdateRequest;
import com.example.springboot.entity.*;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.DepartmentMapper;
import com.example.springboot.mapper.RightsMapper;
import com.example.springboot.mapper.RoleMapper;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.IUserService;
import com.example.springboot.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Slf4j
@Service
public class UserService implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RightsMapper rightsMapper;

    private final static String DEFAULT_PASS = "123";
    private final static String PASS_SALT = "123";

    private String securePass(String password) {
        return SecureUtil.md5(password + PASS_SALT);
    }

    @Override
    public List<User> ListUsers() {
        return userMapper.ListUsers();
    }

    @Override
    public List<User> CountUser() {
        return userMapper.CountUser();
    }

    @Override
    public void SaveUser(@RequestBody User user) {
        if (StringUtils.isBlank(user.getUserPwd())) {
            user.setUserPwd(DEFAULT_PASS);
        }
        user.setUserPwd(securePass(user.getUserPwd()));
        Date date = new Date();
        user.setUserAccount(DateUtil.format(date, "yyyyMMdd") + Math.abs(IdUtil.fastSimpleUUID().hashCode()));
        userMapper.SaveUser(user);
    }

    @Override
    public Object PageUsers(UserPageRequest userPageRequest) {
        PageHelper.startPage(userPageRequest.getPageNum(), 5);
        List<PageUserDTO> users = userMapper.PageUsers(userPageRequest);
        PageInfo<PageUserDTO> userPageInfo = new PageInfo<>(users);
        return userPageInfo;
    }

    @Override
    public User getUsersById(Integer userId) {
        return userMapper.getUsersById(userId);
    }

    @Override
    public void updateUserById(User user) {
        user.setUpdateTime(new Date());
        userMapper.updateUserById(user);
    }

    @Override
    public void delUserById(Integer userId) {
        userMapper.delUserById(userId);
    }

    @Override
    public LoginDTO UserLogin(LoginRequest loginRequest) {
        loginRequest.setUserPwd(securePass(loginRequest.getUserPwd()));
        User user = userMapper.UserLogin(loginRequest);
        if (user == null) {
            throw new ServiceException("用户名或密码错误");
        }
        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(user, loginDTO);
        loginDTO.setToken(TokenUtils.genToken(String.valueOf(user.getUserId()), loginRequest.getUserPwd()));
        return loginDTO;
    }

    @Override
    public String userRegister(RegisterRequest registerRequest) {
        String userPhone = registerRequest.getUserPhone();
        Integer userExist = userMapper.getUsersByUserPhone(userPhone);
        if (userExist >0) {
            throw new ServiceException("已存在相同手机号");
        }
        registerRequest.setUserPwd(securePass(registerRequest.getUserPwd()));
        Date date = new Date();
        String userAccount = DateUtil.format(date, "yyyyMMdd") + Math.abs(IdUtil.fastSimpleUUID().hashCode());
        registerRequest.setUserAccount(userAccount);
        userMapper.userRegister(registerRequest);
        return userAccount;
    }

    @Override
    public UserAboutDTO userGetUsersByToken(String token) {
        String userId = JWT.decode(token).getAudience().get(0);
        User user = userMapper.getUsersById(Integer.parseInt(userId));
        if (user == null) {
            throw new ServiceException("获取信息失败");
        }
        UserAboutDTO userAboutDTO = new UserAboutDTO();
        BeanUtils.copyProperties(user, userAboutDTO);
        userAboutDTO.setRoleName(roleMapper.getRoleNameById(user.getRoleId()));
        userAboutDTO.setDepartmentName(departmentMapper.getDepartmentNameById(user.getDepartmentId()));
        return userAboutDTO;
    }

    @Override
    public void userUpdateUserByToken(Object userForm) {
        Map entity = (Map) userForm;
        String token = (String) entity.get("token");
        String userId = JWT.decode(token).getAudience().get(0);
        User user = userMapper.getUsersById(Integer.parseInt(userId));
        if (user == null) {
            throw new ServiceException("您的信息有误");
        }
        String userMessage = JSON.toJSONString(entity.get("userForm"));
        UserUpdateRequest userUpdateRequest = JSONObject.parseObject(userMessage, UserUpdateRequest.class);
        userUpdateRequest.setUserId(Integer.parseInt(userId));
        userMapper.userUpdateUserByToken(userUpdateRequest);
    }

    @Override
    public void userResetPwd(Object userForm) {
        Map entity = (Map) userForm;
        String token = (String) entity.get("token");
        String userId = JWT.decode(token).getAudience().get(0);
        String nowPwd = (String) entity.get("nowPwd");
        User user = new User();
        user.setUserId(Integer.parseInt(userId));
        user.setUserPwd(securePass(nowPwd));
        String userAccount = userMapper.userCheck(user);
        if (userAccount == null) {
            throw new ServiceException("当前密码有误");
        }
        String checkPwd = (String) entity.get("checkPwd");
        user.setUserPwd(securePass(checkPwd));
        userMapper.userResetPwd(user);
    }

    @Override
    public void adminResetUserPwd(Object userForm) {
        Map entity = (Map) userForm;
        String token = (String) entity.get("token");
        Integer adminId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
        String rightClass = rightsMapper.checkUserRight(adminId, 17);
        if (rightClass == null) {
            throw new ServiceException("您的权限不够");
        }
        Integer userId = (Integer) entity.get("userId");
        Integer adminWeight = roleMapper.getRoleWeightByUserId(adminId);
        Integer userWeight = roleMapper.getRoleWeightByUserId(userId);
        if (adminWeight <= userWeight) {
            throw new ServiceException("您的权限等级不足，无法重置该用户密码！");
        }
        User user = new User();
        String newPwd = (String) entity.get("newPwd");
        user.setUserId(userId);
        user.setUserPwd(securePass(newPwd));
        userMapper.userResetPwd(user);
    }

    @Override
    public Object getUserMailList(String token) {
        String userId = JWT.decode(token).getAudience().get(0);
        String userAddressString = userMapper.getUserAddressListById(Integer.parseInt(userId));
        if (userAddressString == null) {
            return null;
        }
        String[] userAddressList = userAddressString.split("-");
        List<UserPhone> userPhoneList = new ArrayList<>();
        for (int i = 0; i < userAddressList.length; i++) {
            UserPhone userPhone = userMapper.getUserPhoneById(Integer.parseInt(userAddressList[i]));
            userPhoneList.add(userPhone);
        }
        return userPhoneList;
    }

    @Override
    public void addUserMailList(Object object) {
        Map entity = (Map) object;
        String token = (String) entity.get("token");
        Integer userId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
        String addId = Integer.toString((Integer) entity.get("addId"));
        String userAddressString = userMapper.getUserAddressListById(userId);
        if (userAddressString==null) {
            userAddressString = addId + '-';
        } else {
            String[] userAddressList = userAddressString.split("-");
            if (Arrays.asList(userAddressList).contains(addId)) {
                throw new ServiceException("已添加该用户");
            }
            userAddressString += addId + '-';
        }
        userMapper.updateUserAddressListById(userId, userAddressString);
    }

    @Override
    public void deleteUserMailList(Object object) {
        Map entity = (Map) object;
        String token = (String) entity.get("token");
        Integer userId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
        String deleteId = Integer.toString((Integer) entity.get("deleteId"));
        String userAddressString = userMapper.getUserAddressListById(userId);
        String[] userAddressList = userAddressString.split("-");
        List<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, userAddressList);
        System.out.println(arrayList.indexOf(deleteId));
        arrayList.remove(arrayList.indexOf(deleteId));
        userAddressList = arrayList.toArray(new String[arrayList.size()]);
        userAddressString = StringUtils.join(userAddressList, "-");
        if (userAddressString.length() == 0) {
            userAddressString = null;
        } else {
            userAddressString += '-';
        }
        userMapper.updateUserAddressListById(userId, userAddressString);
    }


}
