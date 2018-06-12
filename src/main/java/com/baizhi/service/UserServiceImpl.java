package com.baizhi.service;

import com.baizhi.annotation.LogAnnotation;
import com.baizhi.dao1.UserDAO;
import com.baizhi.entity.AddressSort;
import com.baizhi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/6/1.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @LogAnnotation(name = "查询所有地址分布")
    public List<AddressSort> queryAll() {
        List<AddressSort> addressSort = userDAO.queryAll();
        return addressSort;
    }
    @LogAnnotation(name = "登录")
    public String queryByName(String username,String passwrd) {
        String s="";
        User user = userDAO.queryByName(username);
        if (user!=null){
            if(user.getPassword().equals(passwrd)){
                s="登录成功";
            }else s="密码错误";
        }else s="用户不存在";
        return s;
    }
    @LogAnnotation(name = "修改密码")
    public void updatePass(String username, String password) {
        userDAO.updatePass(username, password);
    }
    @LogAnnotation(name = "查所有用户")
    public List<User> query() {
        List<User> list = userDAO.query();
        return list;
    }
    @LogAnnotation(name = "分页查询所有用户")
    public List<User> selectUser(Integer begin, Integer end) {
        List<User> users = userDAO.selectUser(begin,end);
        return users;
    }
    @LogAnnotation(name = "查询用户数量")
    public Integer queryCount() {
        Integer i = userDAO.queryCount();
        return i;
    }
    @LogAnnotation(name = "修改用户状态")
    public void update(int id, String status) {
        userDAO.update(id, status);
    }
    @LogAnnotation(name = "添加用户")
    public void insertUser(User user) {
        userDAO.insertUser(user);
    }
    @LogAnnotation(name = "按电话号码查询用户")
    public User queryByPhone(String phone) {
        User user = userDAO.queryByPhone(phone);
        return user;
    }
    @LogAnnotation(name = "根据ID查询用户")
    public User queryById(Integer id) {
        User user = userDAO.queryById(id);
        return user;
    }
    @LogAnnotation(name = "发送验证信息")
    public void updateUseMSG(User user) {
        userDAO.updateUseMSG(user);
    }
}
