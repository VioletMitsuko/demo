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
        List<Integer> menuIds = new ArrayList<Integer>(Arrays.asList(menuId));
        if(menuIds.size() > 0){
            //如果用户原本拥有的角色不为空
            if(menuIDOfRole.size() > 0){
                if(menuIDOfRole.containsAll(menuIds)){
                    menuIDOfRole.removeAll(menuIds);
                    roleMenuDao.deleteMenusOfRole(roleId, menuIDOfRole.toArray(new Integer[menuIDOfRole.size()]));
                }else{
                    List<Integer> menuIDofRoleBackUp = new ArrayList<Integer>();
                    menuIDofRoleBackUp.addAll(menuIDOfRole);
                    List<Integer> menuIdsBackUp = new ArrayList<Integer>();
                    menuIdsBackUp.addAll(menuIds);
                    //删除角色
                    menuIds.removeAll(menuIDOfRole);
                    roleMenuDao.deleteMenusOfRole(roleId, menuIds.toArray(new Integer[menuIds.size()]));
                    //添加角色
                    System.out.println("添加角色");
                    menuIDOfRole.removeAll(menuIdsBackUp);
                    menuIDOfRole.addAll(menuIdsBackUp);
                    menuIDOfRole.removeAll(menuIDofRoleBackUp);
                    Integer result = 0;
                    for (Integer integer : menuIDOfRole) {
                        roleMenuDao.insertMenusForRole(roleId, integer);
                        result++;
                    }
                    ResultInfo<Integer> resultInfo = new ResultInfo<>(200, "success", result);
                    return resultInfo;
                }
            }else{
                Integer result = 0;
                for (Integer mId : menuIds) {
                    roleMenuDao.insertMenusForRole(roleId,mId);
                    result ++;
                }
                ResultInfo<Integer> resultInfo = new ResultInfo<>(200, "success", result);
                return resultInfo;
            }
        }else{
            Integer result = roleMenuDao.deleteAllMenusOfRole(roleId);
            ResultInfo<Integer> resultInfo = new ResultInfo<>(200, "success", result);
            return resultInfo;
        }
        ResultInfo<Integer> resultInfo = new ResultInfo<>(200, "success", null);
        return resultInfo;
    }
}
