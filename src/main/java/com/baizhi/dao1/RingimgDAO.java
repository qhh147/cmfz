package com.baizhi.dao1;

import com.baizhi.entity.Ringimg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/5/29.
 */
public interface RingimgDAO {
    List<Ringimg> queryAll(@Param("begin") Integer begin, @Param("end") Integer end);
    Integer queryCount();
    void insert(Ringimg ringimg);
    void update(@Param("id") int id, @Param("rstatus") String rstatus);
    void delete(Integer id);
    List<Ringimg> queryAllRimg();
}
