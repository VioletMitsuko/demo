package com.example.system.service.impl;

import com.example.system.dao.MenuDao;
import com.example.system.domain.Menu;
import com.example.system.service.MenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VioletMitsuko
 */
@Service
public class MenuServiceimpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public PageInfo<Menu> findAllByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Menu> lists = menuDao.findAllDemoMenu();
        PageInfo<Menu> pageInfo = new PageInfo<>(lists);
        return pageInfo;
    }

    @Override
    public List<Menu> findAll() {
        return menuDao.findAllDemoMenu();
    }

    @Override
    public Menu findDemoMenuById(Integer id) {
        return menuDao.findDemoMenuById(id);
    }

    @Override
    public PageInfo<Menu> findMenuByKeyWords(int pageNum, int pageSize, String name, String state, String type) {
        PageHelper.startPage(pageNum,pageSize);
        List<Menu> lists = menuDao.findMenuByKeyWords(name, state, type);
        PageInfo<Menu> pageInfo = new PageInfo<>(lists);
        return pageInfo;
    }

    @Override
    public List<Menu> findFatherMenu() {
        return menuDao.findFatherMenu();
    }

    @Override
    public int addDemoMenu(Menu menu) {
        return menuDao.addDemoMenu(menu);
    }

    @Override
    public int deleteDemoMenu(List<Integer> id) {
        return menuDao.deleteDemoMenu(id);
    }

    @Override
    public int updateDemoMenu(Menu menu) {
        return menuDao.updateDemoMenu(menu);
    }

    @Override
    public int changeState(Integer state, Integer id) {
        return menuDao.changeState(state, id);
    }
}
