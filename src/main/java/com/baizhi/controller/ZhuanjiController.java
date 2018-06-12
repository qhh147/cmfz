package com.baizhi.controller;

import com.baizhi.entity.FileParam;
import com.baizhi.entity.Zhuanji;
import com.baizhi.service.ZhangjieService;
import com.baizhi.service.ZhuanjiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/31.
 */
@Controller
public class ZhuanjiController {
    @Autowired
    private ZhuanjiService zhuanjiService;
    @Autowired
    private ZhangjieService zhangjieService;

    @RequestMapping(value = "/showZJ")
    @ResponseBody
    public Map showZJ(Integer page, String rows,HttpServletRequest request){
        int pageNumber=0;
        Integer total=zhuanjiService.queryCount()+zhangjieService.queryCount();
        if(page==null){
            page=1;
        }
        int row=0;
        if(rows==null) row=100;
        row = Integer.valueOf(rows);
        int begin=(page-1)*row;
        int end=page*row;
        if(total%row==0){
            pageNumber=total/row;
        }else {
            pageNumber=total/row+1;
        }
        List<Zhuanji> list = zhuanjiService.queryAll(begin,end);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("total",total);
        map.put("rows",list);
        request.setAttribute("pageNumber",pageNumber);
        return map;
    }
    @RequestMapping(value = "/insertZJ",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String insertZJ(MultipartFile imgPath,String title,int setCount,Double score,String author,String broadcast,String brief, HttpServletRequest request){
        /*String realPath = request.getSession().getServletContext().getRealPath("/");
        File file = new File(realPath);
        String webappsPath = file.getParent();
        File uploadPath = new File(webappsPath + "/upload");
        if(!uploadPath.exists()){
            uploadPath.mkdir();
        }
        String originalFilename = imgPath.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);
        UUID uuid=UUID.randomUUID();
        String s = uuid.toString();
        String newName=s+"."+extension;*/
        //imgPath.transferTo(new File(uploadPath.getPath(),newName));
        FileParam fileParam = UploadUtil.addFile(imgPath, request, UploadUtil.IMGES);
        Zhuanji zhuanji=new Zhuanji();
        zhuanji.setTitle(title);
        zhuanji.setSetCount(setCount);
        zhuanji.setScore(score);
        zhuanji.setAuthor(author);
        zhuanji.setBrief(brief);
        zhuanji.setBroadcast(broadcast);
        zhuanji.setImgPath(fileParam.getUrl());
        zhuanji.setCreateDate(new Date());
        zhuanji.setChildren(null);
        zhuanjiService.insert(zhuanji);
        return "添加成功";


    }
}






















