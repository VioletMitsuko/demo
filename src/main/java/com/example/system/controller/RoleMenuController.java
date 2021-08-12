package com.example.system.controller;

import com.example.system.domain.Authority;
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


    @Authority("查询菜单")
    @GetMapping("/findMenus")
    public Map findMenusOfRoleByRoleId(Integer id){
        Map map = roleMenuService.findMenusOfRoleByRoleId(id);
        return map;
    };


    @Authority("设置角色菜单")
    @PostMapping("/updateMenu")
    public ResultInfo updateRolesOfUser( Integer roleId, Integer[] menuId){
        ResultInfo resultInfo = roleMenuService.updateMenusOfRole(roleId, menuId);
        return resultInfo;
    };
}
