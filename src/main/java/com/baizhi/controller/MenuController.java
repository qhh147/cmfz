package com.baizhi.controller;

import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * Created by Administrator on 2018/5/29.
 */
@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;
    @RequestMapping(value = "/show")
    @ResponseBody
    public List<Menu> show(){
        List<Menu> list = menuService.queryAll();
        return list;
    }
}


















