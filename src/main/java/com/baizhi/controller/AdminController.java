package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.service.AdminServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/5/29.
 */
@Controller
public class AdminController {
    Logger logger= LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private AdminService adminService;
    @RequestMapping(value="/loginAdmin")
    public String login(String name, String password,HttpSession session){
        String s = adminService.queryByName(name,password);
        session.setAttribute("Aname",name);
        if(s.equals("登录成功")) return "redirect:/main/main.jsp";
        else return "redirect:/index.jsp";
    }

    @RequestMapping(value="/checkCode")
    @ResponseBody
    public String checkCode(String code,HttpSession session){
        String code1 = (String) session.getAttribute("code");
        if(code1.equals(code)) return "yes";
        else return "验证码错误";
    }
    @RequestMapping(value="/exists")
    public String exists(HttpSession session){
        session.removeAttribute("Aname");
        return "redirect:/main/main.jsp";
    }
    @RequestMapping(value="/updatePass",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String updatePass(String password1, String password,String repass,HttpSession session){
        if(!password.equals(repass)){
            return "两次输入密码不一致";
        }else{
            String aname = (String) session.getAttribute("Aname");
            Admin admin = adminService.queryByN(aname);
            if(admin.getPassword().equals(password1)){
                adminService.updatePass(aname,password);
                logger.debug("修改密码成功");
                return "密码修改成功";
            }else {
                return "旧密码输入不正确，密码修改失败";
            }
        }
    }

}

























