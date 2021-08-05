package com.example.system.service;

import com.example.system.domain.User;
import com.github.pagehelper.PageInfo;

import java.sql.Date;
import java.util.List;

/**
 * @author VioletMitsuko
 */
public interface UserService {

    /**
     * 分页查询
     * @param pageNum 当前页
     * @param pageSize 每页个数
     * @return PageInfo
     */
    PageInfo<User> findAllByPage(int pageNum, int pageSize);

    /**
     * 根据用户id查询用户
     * @param id 用户id
     * @return DemoUser
     */
    User findDemoUserById(Integer id);

    /**
     * 根据用户名和密码查询用户，用于登录
     * @param userName 用户名
     * @param password 密码
     * @return DemoUser
     */
    User demoUserLogin(String userName, String password);


    /**
     * 模糊查询
     * @param pageNum 当前页
     * @param pageSize 每页个数
     * @param userName 用户名
     * @param name 姓名
     * @param state 状态
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return PageInfo
     */
    PageInfo<User> findUserByKeyWords(Integer pageNum, Integer pageSize,String userName,String name,String state,Date startDate,Date endDate);

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
    int deleteDemoUser(List<Integer> id);

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

    /**
     * 设置用户角色
     * @param roleId 角色id
     * @param userId 用户id
     * @return int
     */
    int setUserRole(List<Integer> roleId,Integer userId);

}
