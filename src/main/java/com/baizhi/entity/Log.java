package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/6/5.
 */
public class Log implements Serializable{
    private Integer id;
    private String username;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format ="yyyy-MM-dd")
    private Date dodate;
    private String things;
    private String result;

    public Log(Integer id, String username, Date dodate, String things, String result) {
        this.id = id;
        this.username = username;
        this.dodate = dodate;
        this.things = things;
        this.result = result;
    }

    public Log() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Date getDodate() {
        return dodate;
    }

    public void setDodate(Date dodate) {
        this.dodate = dodate;
    }


    public String getThings() {
        return things;
    }

    public void setThings(String things) {
        this.things = things;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String toString() {
        return "Log{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", dodate=" + dodate +
                ", things='" + things + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
