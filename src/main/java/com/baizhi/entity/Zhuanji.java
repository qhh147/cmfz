package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Zhuanji implements Serializable{
    private int id;
    private String title;
    private Integer setCount;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format ="yyyy-MM-dd")
    private Date createDate;
    private Double score;
    private String author;
    private String broadcast;
    private String brief;
    private String imgPath;
    private List<Zhangjie> children;

    public Zhuanji(int id, String title, Integer setCount, Date createDate, Double score, String author, String broadcast, String brief, String imgPath, List<Zhangjie> children) {
        this.id = id;
        this.title = title;
        this.setCount = setCount;
        this.createDate = createDate;
        this.score = score;
        this.author = author;
        this.broadcast = broadcast;
        this.brief = brief;
        this.imgPath = imgPath;
        this.children = children;
    }

    public Zhuanji() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSetCount() {
        return setCount;
    }

    public void setSetCount(Integer setCount) {
        this.setCount = setCount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(String broadcast) {
        this.broadcast = broadcast;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public List<Zhangjie> getChildren() {
        return children;
    }

    public void setChildren(List<Zhangjie> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Zhuanji{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", setCount=" + setCount +
                ", createDate=" + createDate +
                ", score=" + score +
                ", author='" + author + '\'' +
                ", broadcast='" + broadcast + '\'' +
                ", brief='" + brief + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", children=" + children +
                '}';
    }
}