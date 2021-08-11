package com.example.system.service.impl;

import com.example.system.dao.MenuDao;
import com.example.system.dao.RoleMenuDao;
import com.example.system.domain.Menu;
import com.example.system.domain.RoleMenu;
import com.example.system.service.RoleMenuService;
import com.example.system.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author VioletMitsuko
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuDao roleMenuDao;
    @Autowired
    private MenuDao menuDao;

    @Override
    public Map findMenusOfRoleByRoleId(Integer id) {
        Map map = new HashMap();
        //获取该角色拥有的所有菜单
        List<RoleMenu> roleMenulist = roleMenuDao.findMenusOfRoleByRoleId(id);
        //获取该角色拥有的所有菜单的id
        ArrayList<Integer> roleMenuIDlist = new ArrayList<>();
        if(roleMenuIDlist != null){
            for (RoleMenu roleMenu : roleMenulist) {
                roleMenuIDlist.add(roleMenu.getMenuId());
            }
        }
        //获取数据库中所有角色
        List<Menu> menuList = menuDao.findAllDemoMenu();
        map.put("roleMenuIDList",roleMenuIDlist);
        map.put("menuList", menuList);
        return map;
    }

    @Override
    public ResultInfo<Integer> updateMenusOfRole(Integer roleId, Integer[] menuId) {
        //获取该用户拥有的所有角色的主键
        List<Integer> menuIDOfRole = roleMenuDao.findMenusIDOfRole(roleId);
        //将该用户拥有的所有角色的主键与用户传入的角色主键数组进行对比
        if(menuId != null && menuId.length > 0){
            List<Integer> menus = Arrays.asList(menuId);
            ArrayList<Integer> menusToDelete = new ArrayList<>();
            for (Integer menuOfRole : menuIDOfRole) {
                //如果当前拥有的角色主键集合和用户传进来的主键集合不同，则需要将该记录删除
                if(!menus.contains(menuOfRole)){
                    menusToDelete.add(menuOfRole);
                }
            }
            menuIDOfRole.remove(menusToDelete);
            //获取用来添加的角色id数组
            List<Integer> menusToInsert = Arrays.asList(menuId);
            menusToInsert.remove(menuIDOfRole);

            Integer[] menu = menusToInsert.toArray(new Integer[menusToInsert.size()]);
            roleMenuDao.deleteMenusOfRole(roleId, menu);

            //为角色添加菜单
            Integer result = 0;
            for (Integer integer : menusToInsert) {
                roleMenuDao.insertMenusForRole(roleId, integer);
                result++;
            }
            ResultInfo<Integer> resultInfo = new ResultInfo<>(200, "success", result);
            return resultInfo;
        }else{
            //清空用户身上的所有角色
            Integer result = roleMenuDao.deleteAllMenusOfRole(roleId);
            ResultInfo<Integer> resultInfo = new ResultInfo<>(200, "success", result);
            return resultInfo;
        }


    }
}
