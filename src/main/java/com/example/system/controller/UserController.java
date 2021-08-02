package com.example.system.controller;

import com.example.system.domain.User;
import com.example.system.domain.UserRole;
import com.example.system.service.UserService;
import com.example.system.utils.ResultInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.util.List;

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
    public PageInfo findUserByKeyWords(String userName,String name,Integer state,Date startDate,Date endDate) {
        PageInfo<User> userByKeyWords = userService.findUserByKeyWords(1, 5, userName, name, state, startDate, endDate);
        return userByKeyWords;

    }

    @PostMapping("/updateUser")
    public String updateUser(User user){
        int i = userService.updateDemoUser(user);
        if (i > 0){
            return "ok";
        }
        return "no";
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
    public String changeState(Integer state,int id){
        int i = userService.changeState(state,id);
        if (i > 0 ){
            return "ok";
        }
        return "no";
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
