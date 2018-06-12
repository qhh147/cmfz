package com.baizhi.dao1;

import com.baizhi.entity.Zhuanji;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/5/31.
 */
public interface ZhuanjiDAO {
    List<Zhuanji> queryAll(@Param("begin") Integer begin, @Param("end") Integer end);
    Integer queryCount();
    void insert(Zhuanji zhuanji);
    void update(@Param("id") int id, @Param("zhuanji") Zhuanji zhuanji);
    void delete(int id);
    List<Zhuanji> queryNew();
    List<Zhuanji> queryNews();
    List<Zhuanji> queryById(Integer id);
}
