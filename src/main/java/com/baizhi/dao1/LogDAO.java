package com.baizhi.dao1;

import com.baizhi.entity.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/6/5.
 */
public interface LogDAO {
    void insert(Log log);
    List<Log> queryAll(@Param("begin") Integer begin, @Param("end") Integer end);
    Integer queryCount();
}
