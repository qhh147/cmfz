package com.baizhi.controller;

import com.baizhi.entity.FileParam;
import com.baizhi.entity.Ringimg;
import com.baizhi.service.RingimgService;
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
 * Created by Administrator on 2018/5/29.
 */
@Controller
public class RingimgController {
    @Autowired
    private RingimgService ringimgService;
    @RequestMapping(value="/showRingimg")
    @ResponseBody
    public Map showRingimg(Integer page, String rows){
        Integer total = ringimgService.queryCount();
        if(page==null){
            page=1;
        }
        Integer row = Integer.valueOf(rows);
        if(rows==null) row=5;
        Integer begin=(page-1)*row;
        Integer end=page*row;
        List<Ringimg> list = ringimgService.queryAll(begin,end);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("total",total);
        map.put("rows",list);
        return map;
    }
    @RequestMapping(value="/insertRingimg",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String insertRintimg(MultipartFile img,String status, String desc, String title, HttpServletRequest request){
        /*String realPath = request.getSession().getServletContext().getRealPath("/");
        File file = new File(realPath);
        String webappsPath = file.getParent();
        File uploadPath = new File(webappsPath + "/upload");
        if(!uploadPath.exists()){
            uploadPath.mkdir();
        }
        String originalFilename = img.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);
        UUID uuid=UUID.randomUUID();
        String s = uuid.toString();
        String newName=s+"."+extension;*/
        //img.transferTo(new File(uploadPath.getPath(),newName));
        FileParam fileParam = UploadUtil.addFile(img, request, UploadUtil.IMGES);
        Ringimg ringimg=new Ringimg();
        ringimg.setRstatus(status);
        ringimg.setTitle(title);
        ringimg.setRdesc(desc);
        ringimg.setImgPath(fileParam.getUrl());
        ringimg.setCreate_date(new Date());
        ringimgService.insert(ringimg);
        return "添加成功";
    }
    @RequestMapping(value="/update",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String update(int id,String rstatus){
        ringimgService.update(id,rstatus);
        return "修改成功";
    }
    @RequestMapping(value="/delete",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String delete(Integer id){
        ringimgService.delete(id);
        return "删除成功";
    }
}


























