package com.baizhi.controller;

import com.baizhi.entity.FileParam;
import com.baizhi.entity.Zhangjie;
import com.baizhi.service.ZhangjieService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by Administrator on 2018/5/31.
 */
@Controller
public class ZhangjieController {
    @Autowired
    private ZhangjieService zhangjieService;
    @RequestMapping(value = "/insertZE",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String insertZE(MultipartFile addFile, HttpServletRequest request,int album_id){
        FileParam param = UploadUtil.addFile(addFile, request, UploadUtil.File);
        long size = addFile.getSize();
        size=(size/1024)/1024;
        String s=String.valueOf(size);
        Zhangjie zhangjie=new Zhangjie();
        zhangjie.setTitle(param.getOldName());
        int pid=album_id;
        zhangjie.setPid(pid);
        zhangjie.setDownPath(param.getUrl());
        zhangjie.setUploadDate(new Date());
        zhangjie.setSize(s+"M");
        zhangjie.setDuration("4.5min");
        zhangjieService.insertZE(zhangjie);
        return "添加成功";
    }
    @ResponseBody
    @RequestMapping("/down")
    public void down(String downPath, String title, HttpServletResponse response, HttpServletRequest request) {
        String projectPath = request.getSession().getServletContext().getRealPath("/");

        File file = new File(projectPath);

        String webappsPath = file.getParent();

        String filePath = webappsPath + downPath;

        File downFile = new File(filePath);

        //设置响应头 响应类型
        String fileName = null;
        try {
            fileName = new String(title.getBytes("UTF-8"), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("content-disposition", "attachment;fileName=" + fileName);
        response.setContentType("audio/mpeg");
        //响应出去
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(FileUtils.readFileToByteArray(downFile));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
























