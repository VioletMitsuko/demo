package com.example.system.controller;

import com.example.system.domain.User;
import com.example.system.domain.UserRole;
import com.example.system.service.UserService;
import com.example.system.utils.DateUtils;
import com.example.system.utils.ResultInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;


import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author VioletMitsuko
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping ("/login")
    public String login(String userName,String password){
        User user = userService.demoUserLogin(userName, password);
        if (user != null){
            return "ok";
        }
        return "no";
    }

    @RequestMapping("/pageS")
    public ResultInfo<PageInfo<User>> pageS(Integer page,Integer limit){
        PageInfo<User> allByPageS = userService.findAllByPage(page, limit);
        return new ResultInfo<>(200, "success", allByPageS);
    }

    @RequestMapping("/addUser")
    public ResultInfo<Integer> addUser(User user){
        int i = userService.addDemoUser(user);
        if (i > 0){
                return new ResultInfo<>(200, "添加成功", i);
            }
        return new ResultInfo<>(405, "添加失败", i);
    }

    @PostMapping("/findUserByKeyWords")
    public ResultInfo<PageInfo<User>> findUserByKeyWords(Integer page,Integer limit,@RequestParam Map params) {
        String userName = (String) params.get("userName");
        String name = (String) params.get("name");
        String state = (String) params.get("state");
        String date = (String) params.get("date");
        Date startDate = null;
        Date endDate = null;
        if((!"".equals(date)) && (!"null".equals(date)) && date != null){
            String[] dates = date.split(",");
            DateUtils dateUtils = new DateUtils();
            startDate = dateUtils.str2Date(dates[0]);
            endDate = dateUtils.str2Date(dates[1]);
        }
        PageInfo<User> userByKeyWords = userService.findUserByKeyWords(page, limit,userName,name,state,startDate,endDate);
        if (userByKeyWords != null) {
            return new ResultInfo<>(200, "success", userByKeyWords);
        }
        return new ResultInfo<>(405, "无数据", null);
    }

    @PostMapping("/updateUser")
    public ResultInfo<Integer> updateUser(User user){
        int i = userService.updateDemoUser(user);
        if (i > 0){
            return new ResultInfo<>(200, "修改成功", i);
        }
        return new ResultInfo<>(405, "修改失败", i);
    }

    @RequestMapping("/deleteUser")
    public ResultInfo<Integer> deleteUser(@RequestParam List<Integer> id){
        int i = userService.deleteDemoUser(id);
        if (i > 0){
            return new ResultInfo<>(200, "删除成功", i);
        }
        return new ResultInfo<>(405, "删除失败", i);
    }

    @RequestMapping("/changeState")
    public ResultInfo<Integer> changeState(Integer state,int id){
        int i = userService.changeState(state,id);
        if (i > 0){
            return new ResultInfo<>(200, "状态修改成功", i);
        }
        return new ResultInfo<>(405, "状态修改失败", i);
    }

    @PostMapping("/setUserRole")
    public String setUserRole(List<Integer> roleId ,Integer userId){
        int i = userService.setUserRole(roleId,userId);
        if (i > 0){
            return "ok";
        }
        return "no";
    }
}
