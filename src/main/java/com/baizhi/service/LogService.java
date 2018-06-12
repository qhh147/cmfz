package com.baizhi.service;

import com.baizhi.entity.Log;

import java.util.List;

/**
 * Created by Administrator on 2018/6/5.
 */
public interface LogService {
    void insert(Log log);
    List<Log> queryAll(Integer begin, Integer end);
    Integer queryCount();
}
