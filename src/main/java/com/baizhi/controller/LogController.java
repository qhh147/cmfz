package com.baizhi.controller;

import com.baizhi.entity.Log;
import com.baizhi.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/5.
 */
@Controller
public class LogController {
    @Autowired
    private LogService logService;
    @RequestMapping(value = "/showLog",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map showLog(Integer page, String rows){
        Integer total = logService.queryCount();
        if(page==null){
            page=1;
        }
        Integer row = Integer.valueOf(rows);
        if(rows==null) row=10;
        Integer begin=(page-1)*row;
        Integer end=page*row;
        List<Log> logs = logService.queryAll(begin, end);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("total",total);
        map.put("rows",logs);
        return map;
    }

}
