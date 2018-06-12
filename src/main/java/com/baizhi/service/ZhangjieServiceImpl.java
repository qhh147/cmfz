package com.baizhi.service;

import com.baizhi.annotation.LogAnnotation;
import com.baizhi.dao1.ZhangjieDAO;
import com.baizhi.entity.Zhangjie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/5/31.
 */
@Service
@Transactional
public class ZhangjieServiceImpl implements ZhangjieService {
    @Autowired
    private ZhangjieDAO zhangjieDAO;
    @LogAnnotation(name = "添加章节")
    public void insertZE(Zhangjie zhangjie) {
        zhangjieDAO.insertZE(zhangjie);
    }
    @LogAnnotation(name = "查询章节")
    public Integer queryCount() {
        Integer i = zhangjieDAO.queryCount();
        return i;
    }
    @LogAnnotation(name = "查询专辑相同的章节")
    public List<Zhangjie> queryAll(int pid) {
        List<Zhangjie> zhangjies = zhangjieDAO.queryAll(pid);
        return zhangjies;
    }
}
