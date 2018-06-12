package com.baizhi.service;

import com.baizhi.annotation.LogAnnotation;
import com.baizhi.dao1.AdminDAO;
import com.baizhi.entity.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Administrator on 2018/5/29.
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService{
    Logger logger= LoggerFactory.getLogger(AdminServiceImpl.class);
    @Autowired
    private AdminDAO adminDAO;
    @LogAnnotation(name = "登录")
    public String queryByName(String name,String password) {
        Admin admin = adminDAO.queryByName(name);
        if(admin !=null){
            String password1 = admin.getPassword();
            if(password1.equals(password)){
                logger.debug("登录成功");
                return "登录成功";
            }else{
                logger.error("登录失败");
                return "密码错误";
            }
        }else{
            return "用户不存在";
        }
    }

    @LogAnnotation(name = "修改密码")
    public void updatePass(String aname,String password) {
        adminDAO.updatePass(aname,password);
        logger.debug("修改密码成功");
    }
    @LogAnnotation(name = "根据名字查询")
    public Admin queryByN(String name) {
        Admin admin = adminDAO.queryByName(name);
        return admin;
    }
}
