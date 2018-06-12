package com.baizhi.service;

import com.baizhi.entity.Zhuanji;

import java.util.List;

/**
 * Created by Administrator on 2018/5/31.
 */
public interface ZhuanjiService {
    //管理员操作
    List<Zhuanji> queryAll(Integer begin, Integer end);
    Integer queryCount();
    void insert(Zhuanji zhuanji);
    void update(int id, Zhuanji zhuanji);
    void delete(int id);
    //用户操作
    List<Zhuanji> queryNew();
    List<Zhuanji> queryNews();
    List<Zhuanji> queryById(Integer id);
}
