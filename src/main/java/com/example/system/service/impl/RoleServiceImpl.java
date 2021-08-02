package com.example.system.service.impl;

import com.example.system.dao.RoleDao;
import com.example.system.domain.Role;
import com.example.system.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VioletMitsuko
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public PageInfo<Role> findAllByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> lists = roleDao.findAllDemoRole();
        PageInfo<Role> pageInfo = new PageInfo<>(lists);
        return pageInfo;
    }

    @Override
    public Role findDemoRoleById(Integer id) {
        return roleDao.findDemoRoleById(id);
    }

    @Override
    public PageInfo<Role> findRoleByKeyWords(int pageNum, int pageSize, String name, Integer state) {
        PageHelper.startPage(pageNum,pageSize);
        List<Role> lists = roleDao.findRoleByKeyWords(name, state);
        PageInfo<Role> pageInfo = new PageInfo<>(lists);
        return pageInfo;
    }

    @Override
    public int addDemoRole(Role role) {
        return roleDao.addDemoRole(role);
    }

    @Override
    public int deleteDemoRole(List<Integer> id) {
        return roleDao.deleteDemoRole(id);
    }

    @Override
    public int updateDemoRole(Role role) {
        return roleDao.updateDemoRole(role);
    }
}
