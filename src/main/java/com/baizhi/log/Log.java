package com.baizhi.log;

import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2018/6/11.
 */
public class Log {
    public static Logger logger=Logger.getLogger(Log.class);
    public static void main(String[] args) {
        logger.error("error");
        logger.warn("warn");
        logger.debug("debug");
        logger.info("info");
        /*try {
            logger.info("info1");
        }catch(Exception e){
            logger.error("error1");
        }*/
    }
}
