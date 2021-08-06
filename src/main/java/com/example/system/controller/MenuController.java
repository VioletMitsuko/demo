package com.example.system.controller;

import com.example.system.domain.Menu;
import com.example.system.domain.Role;
import com.example.system.service.MenuService;
import com.example.system.utils.ResultInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

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
    public ResultInfo<PageInfo<Menu>> findMenuByKeyWords(Integer page, Integer limit, @RequestParam Map params){
        String name = (String) params.get("name");
        String state = (String) params.get("state");
        String type = (String) params.get("type");
        PageInfo<Menu> menuByKeyWords = menuService.findMenuByKeyWords(page, limit, name, state,type);
        return new ResultInfo<>(200,"success",menuByKeyWords);
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

    @RequestMapping("/changeState")
    public ResultInfo<Integer> changeState(Integer state,int id){
        int i = menuService.changeState(state,id);
        if (i > 0){
            return new ResultInfo<>(200, "success", i);
        }
        return new ResultInfo<>(405, "状态修改失败", i);
    }
}
