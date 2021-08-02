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
    List<Menu> findMenuByKeyWords(String name,Integer state,Integer type);

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
}
