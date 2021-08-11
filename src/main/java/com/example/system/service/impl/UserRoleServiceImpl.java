package com.example.system.service.impl;

import com.example.system.dao.RoleDao;
import com.example.system.dao.UserRoleDao;
import com.example.system.domain.Role;
import com.example.system.domain.UserRole;
import com.example.system.service.UserRoleService;
import com.example.system.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RoleDao roleDao;
    @Override
    public Map findRolesOfUserByUserId(Integer id) {
        Map map = new HashMap();
        //获取该用户拥有的所有角色
        List<UserRole> userRolelist = userRoleDao.findRolesOfUserByUserId(id);
        //获取该用户拥有的所有角色的id
        ArrayList<Integer> userRoleIDlist = new ArrayList<>();
        if(userRolelist != null){
            for (UserRole userRole : userRolelist) {
                userRoleIDlist.add(userRole.getRoleId());
            }
        }
        //获取数据库中所有角色
        List<Role> roleList = roleDao.findAllDemoRole();
        map.put("userRoleIDList",userRoleIDlist);
        map.put("roleList", roleList);
        return map;
    }

    @Override
    public ResultInfo updateRolesOfUser(Integer userId, Integer[] roleId) {
        //获取该用户拥有的所有角色的主键
        List<Integer> roleIDOfUser = userRoleDao.findRoleIDOfUser(userId);
        //将该用户拥有的所有角色的主键与用户传入的角色主键数组进行对比
        if(roleId != null && roleId.length > 0){
            List<Integer> roles = Arrays.asList(roleId);
            ArrayList<Integer> rolesToDelete = new ArrayList<>();
            for (Integer roleOfUser : roleIDOfUser) {
                //如果当前拥有的角色主键集合和用户传进来的主键集合不同，则需要将该记录删除
                if(!roles.contains(roleOfUser)){
                    rolesToDelete.add(roleOfUser);
                }
            }
            roleIDOfUser.remove(rolesToDelete);
            //获取用来添加的角色id数组
            List<Integer> rolesToInsert = Arrays.asList(roleId);
            rolesToInsert.remove(roleIDOfUser);

            Integer[] role = rolesToInsert.toArray(new Integer[rolesToInsert.size()]);
            userRoleDao.deleteRolesOfUser(userId, role);

            //为用户添加角色
            Integer result = 0;
            for (Integer integer : rolesToInsert) {
                userRoleDao.insertRoleForUser(userId, integer);
                result++;
            }
            ResultInfo<Integer> resultInfo = new ResultInfo<>(200, "success", result);
            return resultInfo;
        }else{
            //清空用户身上的所有角色
            Integer result = userRoleDao.deleteAllRolesOfUser(userId);
            ResultInfo<Integer> resultInfo = new ResultInfo<>(200, "success", result);
            return resultInfo;
        }
    }
}
