package com.example.system.controller;

import com.example.system.domain.Role;
import com.example.system.service.RoleService;
import com.example.system.utils.ResultInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
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
    public ResultInfo<PageInfo<Role>> findRoleByKeyWords(Integer page, Integer limit, @RequestParam Map params){
        String name = (String) params.get("name");
        String state = (String) params.get("state");
        PageInfo<Role> roleByKeyWords = roleService.findRoleByKeyWords(page, limit, name, state);
        return new ResultInfo<>(200,"success",roleByKeyWords);
    }

    @RequestMapping("/addRole")
    public ResultInfo<Integer>  addROle(Role role)  {
        int i = roleService.addDemoRole(role);
        if (i > 0){
            return new ResultInfo<>(200, "添加成功", i);
        }
        return new ResultInfo<>(405, "添加失败", i);
    }

    @RequestMapping("/updateRole")
    public ResultInfo<Integer>  updateRole(Role role) {
        int i = roleService.updateDemoRole(role);
        if (i > 0 ){
            return new ResultInfo<>(200,"success",i);
        }
        return new ResultInfo<>(405, "修改失败", i);
    }

    @RequestMapping("/changeState")
    public ResultInfo<Integer> changeState(Integer state,int id){
        int i = roleService.changeState(state,id);
        if (i > 0){
            return new ResultInfo<>(200, "状态修改成功", i);
        }
        return new ResultInfo<>(405, "状态修改失败", i);
    }
}
