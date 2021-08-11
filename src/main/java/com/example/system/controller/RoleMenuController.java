package com.example.system.controller;

import com.example.system.service.RoleMenuService;
import com.example.system.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author VioletMitsuko
 */
@RestController
@RequestMapping("/roleMenu")
public class RoleMenuController {
    @Autowired
    private RoleMenuService roleMenuService;


    @GetMapping("/findMenus")
    public Map findMenusOfRoleByRoleId(Integer id){
        Map map = roleMenuService.findMenusOfRoleByRoleId(id);
        return map;
    };


    @PostMapping("/updateMenu")
    public ResultInfo updateRolesOfUser( Integer roleId, Integer[] menuId){
        ResultInfo resultInfo = roleMenuService.updateMenusOfRole(roleId, menuId);
        return resultInfo;
    };
}
