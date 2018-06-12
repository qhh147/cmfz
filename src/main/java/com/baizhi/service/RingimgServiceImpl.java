package com.baizhi.service;

import com.baizhi.annotation.LogAnnotation;
import com.baizhi.dao1.RingimgDAO;
import com.baizhi.entity.Ringimg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/5/29.
 */
@Service
@Transactional
public class RingimgServiceImpl implements RingimgService{
    @Autowired
    private RingimgDAO ringimgDAO;
    @LogAnnotation(name = "分页展示所有轮播图")
    public List<Ringimg> queryAll(Integer begin,Integer end) {
        List<Ringimg> list = ringimgDAO.queryAll(begin,end);
        return list;
    }
    @LogAnnotation(name = "查询轮播图数量")
    public Integer queryCount() {
        Integer i = ringimgDAO.queryCount();
        return i;
    }
    @LogAnnotation(name = "添加轮播图")
    public void insert(Ringimg ringimg) {
        ringimgDAO.insert(ringimg);
    }
    @LogAnnotation(name = "修改轮播图状态")
    public void update(int id,String rstatus) {
        ringimgDAO.update(id,rstatus);
    }
    @LogAnnotation(name = "删除轮播图")
    public void delete(Integer id) {
        ringimgDAO.delete(id);
    }
    @LogAnnotation(name = "展示所有轮播图")
    public List<Ringimg> queryAllRimg() {
        List<Ringimg> list = ringimgDAO.queryAllRimg();
        return list;
    }

}
