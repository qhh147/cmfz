package com.baizhi.service;

import com.baizhi.entity.Admin;

/**
 * Created by Administrator on 2018/5/29.
 */
public interface AdminService {

    String queryByName(String name, String password);
    void updatePass(String aname, String password);
    Admin queryByN(String name);
}
