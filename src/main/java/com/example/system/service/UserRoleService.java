package com.example.system.service;

import com.example.system.utils.ResultInfo;

import java.util.Map;

public interface UserRoleService {

    Map findRolesOfUserByUserId(Integer id);

    //给用户添加角色
    ResultInfo updateRolesOfUser(Integer userId, Integer[] roleId);
}
