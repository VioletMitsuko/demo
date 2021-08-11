package com.example.system.service;

import com.example.system.utils.ResultInfo;

import java.util.Map;

/**
 * @author VioletMitsuko
 */
public interface RoleMenuService {

    public Map findMenusOfRoleByRoleId(Integer id);

    public ResultInfo updateMenusOfRole(Integer roleId, Integer[] menuId);

}
