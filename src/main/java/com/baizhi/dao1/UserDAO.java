package com.baizhi.dao1;

import com.baizhi.entity.AddressSort;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/6/1.
 */
public interface UserDAO {
    //所有用户地区分布查询
    List<AddressSort> queryAll();
    User queryByName(String username);
    void updatePass(@Param("username") String username, @Param("password") String password);
    //所有用户查询
    List<User> query();
    //所有用户的分页查询
    List<User> selectUser(@Param("begin") Integer begin, @Param("end") Integer end);
    //用户数量
    Integer queryCount();
    //修改用户状态
    void update(@Param("id") int id, @Param("status") String status);
    void insertUser(User user);
    //客户登录
    User queryByPhone(String phone);
    User queryById(Integer id);
    //修改用户信息
    void updateUseMSG(User user);
}
