package com.example.system.service;

import com.example.system.domain.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author VioletMitsuko
 */
public interface RoleService {

    /**
     * 分页查询
     * @param pageNum 当前页
     * @param pageSize 每页个数
     * @return PageInfo
     */
    PageInfo<Role> findAllByPage(int pageNum, int pageSize);

    List<Role> findAll();

    /**
     * 根据角色id查询角色
     * @param id 角色id
     * @return DemoRole
     */
    Role findDemoRoleById(Integer id);

    /**
     * 模糊查询
     * @param pageNum 当前页
     * @param pageSize 每页个数
     * @param name 角色名称
     * @param state 状态
     * @return PageInfo
     */
    PageInfo<Role> findRoleByKeyWords(int pageNum, int pageSize,String name,String state);

    /**
     * 添加角色
     * @param role 角色信息
     * @return int
     */
    int addDemoRole(Role role);

    /**
     * 根据角色id删除角色
     * @param id 角色id
     * @return int
     */
    int deleteDemoRole(List<Integer> id);

    /**
     * 修改角色信息
     * @param role 角色信息
     * @return int
     */
    int updateDemoRole(Role role);

    /**
     * 修改角色状态
     * @param state 状态
     * @param id 角色id
     * @return int
     */
    int changeState(Integer state,Integer id);

    /**
     * 设置角色菜单
     * @param roleId 角色id
     * @param menuId 菜单id
     * @return int
     */
    int setRoleMenu(Integer roleId,Integer menuId);
}
