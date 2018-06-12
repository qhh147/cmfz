package com.baizhi.service;

import com.baizhi.entity.AddressSort;
import com.baizhi.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2018/6/1.
 */
public interface UserService {
    //1.管理员操作
    List<AddressSort> queryAll();
    String queryByName(String username, String password);
    void updatePass(String username, String password);
    List<User> query();
    //所有用户的分页查询
    List<User> selectUser(Integer begin, Integer end);
    //用户数量
    Integer queryCount();
    void update(int id, String status);
    //2.用户操作
    void insertUser(User user);
    //客户登录
    User queryByPhone(String phone);
    User queryById(Integer id);
    void updateUseMSG(User user);
}
