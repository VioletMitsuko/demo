package com.example.system.controller;

import com.example.system.domain.Menu;
import com.example.system.service.MenuService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author VioletMitsuko
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/pageS")
    public PageInfo<Menu> findAllByPage(){
        PageInfo<Menu> allByPage = menuService.findAllByPage(1, 5);
        return allByPage;
    }

    @RequestMapping("/findMenuByKeyWords")
    public PageInfo<Menu> findMenuByKeyWords(String name,Integer state,Integer type){
        PageInfo<Menu> menuByKeyWords = menuService.findMenuByKeyWords(1, 5, name, state, type);
        return menuByKeyWords;
    }

    @RequestMapping("/addMenu")
    public String addMenu(String name,Integer fatherId, Integer type, String identifier, String url, String img, Integer state, Integer sortNum, Integer createBy, Date creationDate,Integer lastUpdateBy,  Date lastUpdatedDate,String description, String deleteFlag){
        Menu menu = new Menu(name,fatherId,type,identifier,url,img,state,sortNum,createBy, creationDate, lastUpdateBy, lastUpdatedDate, description,  deleteFlag);
        int i = menuService.addDemoMenu(menu);
        if (i > 0 ){
            return "ok";
        }
        return "no";
    }
}
