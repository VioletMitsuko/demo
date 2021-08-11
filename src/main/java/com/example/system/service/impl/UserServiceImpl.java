package com.example.system.service.impl;

import com.example.system.dao.RoleDao;
import com.example.system.dao.UserDao;
import com.example.system.domain.Role;
import com.example.system.domain.User;
import com.example.system.domain.UserRole;
import com.example.system.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author VioletMitsuko
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public PageInfo<User> findAllByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> lists = userDao.findAllDemoUser();
        PageInfo<User> pageInfo = new PageInfo<>(lists);
        System.out.println(pageInfo);
        return pageInfo;
    }

    @Override
    public User findDemoUserById(Integer id){
        return userDao.findDemoUserById(id);
    }

    @Override
    public User demoUserLogin(String userName, String password) {
        return userDao.demoUserLogin(userName,password);
    }

    @Override
    public PageInfo<User> findUserByKeyWords(Integer pageNum, Integer pageSize,String userName,String name,String state,Date startDate,Date endDate) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> lists = userDao.findUserByKeyWords( userName, name, state, startDate, endDate);
        System.out.println(lists);
        PageInfo<User> pageInfo = new PageInfo<>(lists);
        System.out.println(pageInfo);
        return pageInfo;
    }

    @Override
    public int addDemoUser(User user) {
        return userDao.addDemoUser(user);
    }

    @Override
    public int deleteDemoUser(Integer[] id) {
        return userDao.deleteDemoUser(id);
    }

    @Override
    public int updateDemoUser(User user) {
        return userDao.updateDemoUser(user);
    }

    @Override
    public int changeState(Integer state,Integer id) {
        return userDao.changeState(state,id);
    }

//    @Override
//    public int setUserRole(Integer roleId, Integer userId) {
//        return userDao.setUserRole(roleId,userId);
//    }




}
