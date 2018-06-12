package com.baizhi.service;

import com.baizhi.annotation.LogAnnotation;
import com.baizhi.dao1.ZhuanjiDAO;
import com.baizhi.entity.Zhuanji;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/5/31.
 */
@Service
@Transactional
public class ZhuanjiServiceImpl implements ZhuanjiService {
    @Autowired
    private ZhuanjiDAO zhuanjiDAO;
    @LogAnnotation(name = "展示所有专辑")
    public List<Zhuanji> queryAll(Integer begin, Integer end) {
        List<Zhuanji> list = zhuanjiDAO.queryAll(begin, end);
        return list;
    }
    @LogAnnotation(name = "查询专辑数量")
    public Integer queryCount() {
        Integer i = zhuanjiDAO.queryCount();
        return i;
    }
    @LogAnnotation(name = "添加专辑")
    public void insert(Zhuanji zhuanji) {
        zhuanjiDAO.insert(zhuanji);

    }
    @LogAnnotation(name = "修改专辑")
    public void update(int id, Zhuanji zhuanji) {
        zhuanjiDAO.update(id, zhuanji);

    }
    @LogAnnotation(name = "删除专辑")
    public void delete(int id) {
        zhuanjiDAO.delete(id);
    }
    @LogAnnotation(name = "展示最新专辑")
    public List<Zhuanji> queryNew() {
        List<Zhuanji> zhuanjis = zhuanjiDAO.queryNew();
        return zhuanjis;
    }
    @LogAnnotation(name = "展示所有最新专辑")
    public List<Zhuanji> queryNews() {
        List<Zhuanji> zhuanjis = zhuanjiDAO.queryNews();
        return zhuanjis;
    }
    @LogAnnotation(name = "根据ID查询专辑")
    public List<Zhuanji> queryById(Integer id) {
        List<Zhuanji> zhuanjis = zhuanjiDAO.queryById(id);
        return zhuanjis;
    }
}
















