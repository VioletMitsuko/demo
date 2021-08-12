package com.example.system.dao;

import com.example.system.domain.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author VioletMitsuko
 */

@Mapper
public interface UserRoleDao {

    List<UserRole> findRolesOfUserByUserId(Integer id);

    List<Integer> findRoleIDOfUser(Integer id);

    Integer deleteRolesOfUser(Integer userId, Integer[] roleId);

    Integer insertRoleForUser( Integer userId,Integer roleId);

    Integer deleteAllRolesOfUser(Integer userId);
}
