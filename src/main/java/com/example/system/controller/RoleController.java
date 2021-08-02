package com.example.system.controller;

import com.example.system.domain.Role;
import com.example.system.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author VioletMitsuko
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/pageS")
    public PageInfo<Role> pageS(){
        PageInfo<Role> allByPage = roleService.findAllByPage(1, 5);
        return allByPage;
    }

    @RequestMapping("/findRoleByKeyWords")
    public PageInfo<Role> findRoleByKeyWords(String name,Integer state){
        PageInfo<Role> roleByKeyWords = roleService.findRoleByKeyWords(1, 5, name, state);
        return roleByKeyWords;
    }

    @RequestMapping("/addRole")
    public String addROle(String roleCode, String name, String starTime,String stopTime,Integer state, Integer createBy, Date creationDate, Integer lastUpdateBy, Date lastUpdateDate,Integer lastUpdateLogin,String description,String deleteFlag) throws ParseException {
        Role role;
        if (Objects.nonNull(starTime) && Objects.nonNull(stopTime)){
            Date start = new SimpleDateFormat("yyyy-MM-dd").parse(starTime);
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse(stopTime);
            role = new Role(roleCode, name, start, end, state, createBy, creationDate, lastUpdateBy, lastUpdateDate, lastUpdateLogin, description, deleteFlag);
        } else {
            role = new Role(roleCode, name, null, null, state, createBy, creationDate, lastUpdateBy, lastUpdateDate, lastUpdateLogin, description, deleteFlag);
        }
        int i = roleService.addDemoRole(role);
        if (i > 0){
            return "ok";
        }
        return "no";
    }

    @RequestMapping("/updateRole")
    public String updateRole(String roleCode,String name,String starTime,String stopTime,int state,int createBy, Date creationDate, int lastUpdateBy, Date lastUpdateDate,int lastUpdateLogin,String description,String deleteFlag) throws ParseException {
        Role role;
        if (Objects.nonNull(starTime) && Objects.nonNull(stopTime)){
            Date start = new SimpleDateFormat("yyyy-MM-dd").parse(starTime);
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse(stopTime);
            role = new Role(roleCode, name, start, end, state, createBy, creationDate, lastUpdateBy, lastUpdateDate, lastUpdateLogin, description, deleteFlag);
        } else {
            role = new Role(roleCode, name, null, null, state, createBy, creationDate, lastUpdateBy, lastUpdateDate, lastUpdateLogin, description, deleteFlag);
        }
        int i = roleService.updateDemoRole(role);
        if (i > 0 ){
            return "ok";
        }
        return "no";
    }
}
