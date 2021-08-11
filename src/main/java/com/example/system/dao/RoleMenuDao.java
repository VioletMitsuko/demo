package com.example.system.dao;

import com.example.system.domain.RoleMenu;
import com.example.system.domain.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author VioletMitsuko
 */
@Mapper
public interface RoleMenuDao {

    List<RoleMenu> findMenusOfRoleByRoleId(Integer id);

    List<Integer> findMenusIDOfRole(Integer id);

    Integer deleteMenusOfRole(Integer roleId, Integer[] menuId);

    Integer insertMenusForRole(Integer roleId, Integer menuId);

    Integer deleteAllMenusOfRole(Integer roleId);
}
