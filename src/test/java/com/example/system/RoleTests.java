package com.example.system;

import com.example.system.dao.RoleDao;
import com.example.system.domain.Role;
import com.example.system.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RoleTests {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleService roleService;

    @Test
    void testFindAllDemoRole(){
        List<Role> allRole = roleDao.findAllDemoRole();
        for (Role role : allRole) {
            System.out.println(role);
        }
    }

    @Test
    void testFindDemoRoleById(){
        int id = 1;
        Role roleById = roleDao.findDemoRoleById(id);
        System.out.println(roleById);
    }

    @Test
    void testFindAllByPage(){
        PageInfo<Role> allByPage = roleService.findAllByPage(1, 5);
        System.out.println(allByPage);
    }
}
