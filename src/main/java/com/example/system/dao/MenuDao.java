package com.example.system.dao;

import com.example.system.domain.Menu;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author VioletMitsuko
 */
@Mapper
public interface MenuDao {

    /**
     * 查询所有菜单
     * @return list
     */
    List<Menu> findAllDemoMenu();

    /**
     * 根据菜单id查询菜单
     * @param id 菜单id
     * @return Menu
     */
    Menu findDemoMenuById(Integer id);

    /**
     * 模糊查询
     * @param name 菜单名称
     * @param state 状态
     * @param type 类型
     * @return
     */
    List<Menu> findMenuByKeyWords(String name,String state,String type);

    /**
     * 根据用户查找菜单
     * @param id 用户id
     * @return list
     */
    List<String> findMenuByUser(Integer id);


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
