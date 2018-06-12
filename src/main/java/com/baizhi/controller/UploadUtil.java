package com.baizhi.controller;

import com.baizhi.entity.FileParam;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Administrator on 2018/5/31.
 */
public class UploadUtil {
    public static final String File = "FILE";
    public static final String IMG = "IMG";
    public static final String IMGES="IMGES";

    public static FileParam addFile(MultipartFile multipartFile, HttpServletRequest request, String type) {
        //文件位置

        String projetPath = request.getSession().getServletContext().getRealPath("/");

        File file = new File(projetPath);
        //web项目的路径
        String webappsPath = file.getParent();

        //创建上传文件夹
        File uploadPath;
        String url;
        if (type.equals(File)) {
            //上传文件夹的路径
            uploadPath = new File(webappsPath + "/audio");
            url = "/audio";
        } else if(type.equals(IMG)){
            uploadPath = new File(webappsPath + "/upload");
            url = "/upload";
        }else {
            uploadPath = new File(webappsPath + "/imges");
            url = "/imges";
        }
        if (!uploadPath.exists()) {
            uploadPath.mkdir();
        }
        //获取原始文件名  1.jpg
        String oldFilename = multipartFile.getOriginalFilename();
        //获取后缀名
        String extension = FilenameUtils.getExtension(oldFilename);

        UUID uuid = UUID.randomUUID();
        String newName = uuid.toString();
        newName = newName + "." + extension;
        try {
            //上传到指定的文件夹
            multipartFile.transferTo(new File(uploadPath.getPath(), newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new FileParam(url + "/" + newName, oldFilename);
    }
}
