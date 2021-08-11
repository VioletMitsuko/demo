package com.example.system.dao;

import com.example.system.domain.User;
import com.example.system.domain.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * @author VioletMitsuko
 */

@Mapper
public interface UserDao {

    /**
     * 查询所有用户
     * @return list
     */
    List<User> findAllDemoUser();

    /**
     * 根据用户id查询用户
     * @param id 用户id
     * @return User
     */
    User findDemoUserById(Integer id);

    /**
     * 根据用户名和密码查询用户，用于登录
     * @param userName 用户名
     * @param password 密码
     * @return User
     */
    User demoUserLogin(String userName, String password);


    /**
     * 模糊查询
     * @param userName
     * @param name
     * @param state
     * @param startDate
     * @param endDate
     * @return
     */
    List<User> findUserByKeyWords(String userName,String name,String state,Date startDate,Date endDate);

    /**
     * 添加用户
     * @param user 用户信息
     * @return int
     */
    int addDemoUser(User user);

    /**
     * 根据用户id删除用户
     * @param id 用户id
     * @return int
     */
    int deleteDemoUser(Integer[] id);

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return int
     */
    int updateDemoUser(User user);

    /**
     * 修改用户状态
     * @param state 状态
     * @param id 用户id
     * @return int
     */
    int changeState(Integer state,Integer id);

//    /**
//     * 设置用户角色
//     * @param roleId 角色id
//     * @param userId 用户id
//     * @return int
//     */
//    int setUserRole(Integer roleId,Integer userId);
}
