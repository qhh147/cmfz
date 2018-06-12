package com.baizhi.service;

import com.baizhi.entity.Ringimg;

import java.util.List;

/**
 * Created by Administrator on 2018/5/29.
 */
public interface RingimgService {
    //管理员操作
    List<Ringimg> queryAll(Integer begin, Integer end);
    Integer queryCount();
    void insert(Ringimg ringimg);
    void update(int id, String rstatus);
    void delete(Integer id);
    //用户操作
    List<Ringimg> queryAllRimg();

}
