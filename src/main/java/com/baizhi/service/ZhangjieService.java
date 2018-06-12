package com.baizhi.service;

import com.baizhi.entity.Zhangjie;

import java.util.List;


/**
 * Created by Administrator on 2018/5/31.
 */
public interface ZhangjieService {
    void insertZE(Zhangjie zhangjie);
    Integer queryCount();

    List<Zhangjie> queryAll(int pid);
}
