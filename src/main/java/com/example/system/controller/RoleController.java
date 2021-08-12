package com.example.system.controller;

import com.example.system.domain.Authority;
import com.example.system.domain.Role;
import com.example.system.service.RoleService;
import com.example.system.utils.ResultInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author VioletMitsuko
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @Authority("查询角色")
    @RequestMapping("/getRolesOfUser")
    public ResultInfo<List<Role>> getRolesOfUser(){
        List<Role> list = roleService.findAll();
        return new ResultInfo<>(200,"success",list);
    }

    @Authority("查询角色")
    @RequestMapping("/findRoleByKeyWords")
    public ResultInfo<PageInfo<Role>> findRoleByKeyWords(Integer page, Integer limit, @RequestParam Map params){
        String name = (String) params.get("name");
        String state = (String) params.get("state");
        PageInfo<Role> roleByKeyWords = roleService.findRoleByKeyWords(page, limit, name, state);
        return new ResultInfo<>(200,"success",roleByKeyWords);
    }

    @Authority("添加角色")
    @RequestMapping("/addRole")
    public ResultInfo<Integer> addRole(Role role)  {
        int i = roleService.addDemoRole(role);
        if (i > 0){
            return new ResultInfo<>(200, "success", i);
        }
        return new ResultInfo<>(405, "添加失败", i);
    }

    @Authority("修改角色")
    @PostMapping("/updateRole")
    public ResultInfo<Integer> updateRole(Role role) {
        int i = roleService.updateDemoRole(role);
        if (i > 0 ){
            return new ResultInfo<>(200,"success",i);
        }
        return new ResultInfo<>(405, "修改失败", i);
    }

    @Authority("修改角色")
    @RequestMapping("/changeState")
    public ResultInfo<Integer> changeState(Integer state,int id){
        int i = roleService.changeState(state,id);
        if (i > 0){
            return new ResultInfo<>(200, "success", i);
        }
        return new ResultInfo<>(405, "状态修改失败", i);
    }

    @Authority("删除角色")
    @RequestMapping("/deleteRole")
    public ResultInfo<Integer> deleteRole(@RequestParam List<Integer> id){
        int i = roleService.deleteDemoRole(id);
        if (i > 0){
            return new ResultInfo<>(200, "success", i);
        }
        return new ResultInfo<>(405, "删除失败", i);
    }

//    @Authority("设置角色菜单")
//    @PostMapping("/setRoleMenu")
//    public ResultInfo<Integer> setRoleMenu(Integer roleId ,Integer menuId){
//        int i = roleService.setRoleMenu(roleId,menuId);
//        if (i > 0){
//            return new ResultInfo<>(200, "success", i);
//        }
//        return new ResultInfo<>(405, "角色设置失败", i);
//    }
}
