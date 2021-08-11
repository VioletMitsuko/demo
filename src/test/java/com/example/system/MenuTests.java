package com.example.system;

import com.example.system.dao.MenuDao;
import com.example.system.domain.Menu;
import com.example.system.service.MenuService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MenuTests {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private MenuService menuService;

    @Test
    void testFindAllByPage(){
        List<Menu> fatherMenu = menuService.findFatherMenu();
        System.out.println(fatherMenu);
    }

    @Test
    public void testUpdateMenu(){
        Menu menu = new Menu();
        menu.setId(10);
        menu.setSortNum(2);
        int i = menuService.updateDemoMenu(menu);
        System.out.println(i);
    }
}
