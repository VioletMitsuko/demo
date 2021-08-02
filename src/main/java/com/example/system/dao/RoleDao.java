package com.example.system.dao;

import com.example.system.domain.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author VioletMitsuko
 */
@Mapper
public interface RoleDao {

    /**
     * 查询所有角色信息
     * @return list
     */
    List<Role> findAllDemoRole();

    /**
     * 根据角色id查询角色
     * @param id 角色id
     * @return DemoRole
     */
    Role findDemoRoleById(Integer id);

    /**
     * 模糊查询
     * @param name 角色名称
     * @param state 状态
     * @return list
     */
    List<Role> findRoleByKeyWords(String name,Integer state);

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
}
