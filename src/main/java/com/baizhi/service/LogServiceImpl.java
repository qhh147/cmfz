package com.baizhi.service;

import com.baizhi.annotation.LogAnnotation;
import com.baizhi.dao1.LogDAO;
import com.baizhi.entity.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/6/5.
 */
@Service
@Transactional
public class LogServiceImpl implements LogService {
    Logger logger= LoggerFactory.getLogger(LogServiceImpl.class);
    @Autowired
    private LogDAO logDAO;
    @LogAnnotation(name = "添加日志")
    public void insert(Log log) {
        logDAO.insert(log);
    }
    @LogAnnotation(name = "展示所有日志")
    public List<Log> queryAll(Integer begin,Integer end) {
        List<Log> logs = logDAO.queryAll(begin,end);
        logger.debug("展示管理员日志");
        return logs;
    }
    @LogAnnotation(name = "查询日志数量")
    public Integer queryCount() {
        Integer i = logDAO.queryCount();
        return i;
    }
}
