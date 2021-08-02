package com.example.system;

import com.example.system.dao.UserDao;
import com.example.system.domain.User;
import com.example.system.service.UserService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Test
    void testFindAllDemoUser(){
        List<User> allUser = userDao.findAllDemoUser();
        for (User user : allUser) {
            System.out.println(user);
        }
    }

    @Test
    void testFindDemoUserById(){
        int id = 1;
        User userById = userDao.findDemoUserById(id);
        System.out.println(userById);
    }

    @Test
    void testDemoUserLogin(){
        String userName = "admin";
        String password = "123";
        User user = userDao.demoUserLogin(userName, password);
        if (user != null){
            System.out.println("登录成功");
        }
    }

    @Test
    void testFindAllByPageS(){
        PageInfo<User> allByPageS = userService.findAllByPage(1, 5);
        System.out.println(allByPageS);
    }
}
