package com.example.system.controller;

import com.example.system.domain.Authority;
import com.example.system.service.UserRoleService;
import com.example.system.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

/**
 * @author VioletMitsuko
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @Authority("查询角色")
    @GetMapping("/findRoles")
    public Map findRolesOfUserByUserId(Integer id){
        System.out.println("id="+id);
        Map map = userRoleService.findRolesOfUserByUserId(id);
        return map;
    };

    @Authority("设置用户角色")
    @PostMapping("/updateRole")
    public ResultInfo updateRolesOfUser(Integer userId, Integer[] roleId){
        System.out.println("userId >> :"+userId);
        System.out.println("roleId >> :"+ Arrays.toString(roleId));
        ResultInfo resultInfo = userRoleService.updateRolesOfUser(userId, roleId);
        return resultInfo;
    };
}
