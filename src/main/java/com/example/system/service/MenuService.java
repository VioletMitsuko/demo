package com.example.system.service;

import com.example.system.domain.Menu;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author VioletMitsuko
 */
public interface MenuService {

    /**
     * 分页查询
     * @param pageNum 当前页
     * @param pageSize 每页个数
     * @return PageInfo
     */
    PageInfo<Menu> findAllByPage(int pageNum, int pageSize);

    List<Menu> findAll();

    /**
     * 根据菜单id查询菜单
     * @param id 菜单id
     * @return Menu
     */
    Menu findDemoMenuById(Integer id);

    /**
     * 模糊查询
     * @param pageNum 当前页
     * @param pageSize 每页个数
     * @param name 菜单名称
     * @param state 状态
     * @param type 类型
     * @return PageInfo
     */
    PageInfo<Menu> findMenuByKeyWords(int pageNum,int pageSize,String name, String state, String type);

    /**
     * 查找上级菜单
     * @return list
     */
    List<Menu> findFatherMenu();

    /**
     * 添加菜单
     * @param menu 菜单信息
     * @return int
     */
    int addDemoMenu(Menu menu);

    /**
     * 根据菜单id删除菜单
     * @param id 菜单id
     * @return int
     */
    int deleteDemoMenu(List<Integer> id);

    /**
     * 修改菜单信息
     * @param menu 菜单信息
     * @return int
     */
    int updateDemoMenu(Menu menu);

    /**
     * 修改菜单状态
     * @param state 状态
     * @param id 菜单id
     * @return int
     */
    int changeState(Integer state,Integer id);
}
