package com.example.system.service.impl;

import com.example.system.dao.RoleDao;
import com.example.system.dao.UserRoleDao;
import com.example.system.domain.Role;
import com.example.system.domain.UserRole;
import com.example.system.service.UserRoleService;
import com.example.system.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
    public ResultInfo updateRolesOfUser(Integer userId,Integer[] roleId) {
        //获取该用户拥有的所有角色的主键
        List<Integer> roleIDOfUser = userRoleDao.findRoleIDOfUser(userId);
        List<Integer> roleIds = new ArrayList<Integer>(Arrays.asList(roleId));
        System.out.println(roleIDOfUser+"-----------------------");
        System.out.println(roleIds+"-----------------------");
        //如果用户传来的角色数组有值
        if(roleIds.size() > 0){
            //如果用户原本拥有的角色不为空
            if(roleIDOfUser.size() > 0){
                System.out.println("============="+roleIDOfUser.containsAll(roleIds));
                if(roleIDOfUser.containsAll(roleIds)){
                    roleIDOfUser.removeAll(roleIds);
                    System.out.println(roleIds+"roleIds=========");
                    userRoleDao.deleteRolesOfUser(userId, roleIDOfUser.toArray(new Integer[roleIDOfUser.size()]));
                }else{
                    List<Integer> roleIDofUserBackUp = new ArrayList<Integer>();
                    roleIDofUserBackUp.addAll(roleIDOfUser);
                    List<Integer> roleIdsBackUp = new ArrayList<Integer>();
                    roleIdsBackUp.addAll(roleIds);
                    //删除角色
                    roleIds.removeAll(roleIDOfUser);
                    userRoleDao.deleteRolesOfUser(userId, roleIds.toArray(new Integer[roleIds.size()]));
                    //添加角色
                    System.out.println("添加角色");
                    roleIDOfUser.removeAll(roleIdsBackUp);
                    roleIDOfUser.addAll(roleIdsBackUp);
                    roleIDOfUser.removeAll(roleIDofUserBackUp);
                    Integer result = 0;
                    for (Integer integer : roleIDOfUser) {
                        userRoleDao.insertRoleForUser(userId, integer);
                        result++;
                    }
                    ResultInfo<Integer> resultInfo = new ResultInfo<>(200, "success", result);
                    return resultInfo;
                }
            }else{
                Integer result = 0;
                for (Integer rId : roleIds) {
                    userRoleDao.insertRoleForUser(userId,rId);
                    result ++;
                }
                ResultInfo<Integer> resultInfo = new ResultInfo<>(200, "success", result);
                return resultInfo;
            }
        }else{
            Integer result = userRoleDao.deleteAllRolesOfUser(userId);
            ResultInfo<Integer> resultInfo = new ResultInfo<>(200, "success", result);
            return resultInfo;
        }
        ResultInfo<Integer> resultInfo = new ResultInfo<>(200, "success", null);
        return resultInfo;
    }
}
