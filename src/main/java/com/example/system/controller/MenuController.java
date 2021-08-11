package com.example.system.controller;

import com.example.system.domain.Menu;
import com.example.system.service.MenuService;
import com.example.system.utils.ResultInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author VioletMitsuko
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/getMenusOfRole")
    public ResultInfo<List<Menu>> getMenusOfRole(){
        List<Menu> allByPage = menuService.findAll();
        return new ResultInfo<>(200,"success",allByPage);
    }

    @RequestMapping("/findMenuByKeyWords")
    public ResultInfo<PageInfo<Menu>> findMenuByKeyWords(Integer page, Integer limit, @RequestParam Map params){
        String name = (String) params.get("name");
        String state = (String) params.get("state");
        String type = (String) params.get("type");
        PageInfo<Menu> menuByKeyWords = menuService.findMenuByKeyWords(page, limit, name, state,type);
        return new ResultInfo<>(200,"success",menuByKeyWords);
    }

    @RequestMapping("/addMenu")
    public ResultInfo<Integer> addMenu(Menu menu){
        int i = menuService.addDemoMenu(menu);
        if (i > 0){
            return new ResultInfo<>(200, "success", i);
        }
        return new ResultInfo<>(405, "添加失败", i);
    }

    @RequestMapping("/changeState")
    public ResultInfo<Integer> changeState(Integer state,int id){
        int i = menuService.changeState(state,id);
        if (i > 0){
            return new ResultInfo<>(200, "success", i);
        }
        return new ResultInfo<>(405, "状态修改失败", i);
    }

    @RequestMapping("/deleteMenu")
    public ResultInfo<Integer> deleteMenu(@RequestParam List<Integer> id){
        int i = menuService.deleteDemoMenu(id);
        if (i > 0){
            return new ResultInfo<>(200, "success", i);
        }
        return new ResultInfo<>(405, "删除失败", i);
    }

    @PostMapping("/updateMenu")
    public ResultInfo<Integer> updateMenu(Menu menu){
        int i = menuService.updateDemoMenu(menu);
        if (i > 0){
            return new ResultInfo<>(200, "success", i);
        }
        return new ResultInfo<>(405, "修改失败", i);
    }

    @RequestMapping("/nodes")
    public ResultInfo<List<Menu>> findFatherMenu(){
        List<Menu> list = menuService.findFatherMenu();
        List<Menu> fatherMenu = new ArrayList<>();
        for (Menu menu : list) {
            if (menu.getType() < 2){
                fatherMenu.add(menu);
            }
        }
        System.out.println(fatherMenu);
        return new ResultInfo<>(200,"success",fatherMenu);
    }
}
