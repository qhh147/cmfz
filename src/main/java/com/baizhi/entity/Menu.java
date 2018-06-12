package com.baizhi.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/30.
 */

public class Menu implements Serializable{
    private int id;
    private String title;
    private String iconCls;
    private int parentId;
    private String hrefPath;
    private List<Menu> child;

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

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getHrefPath() {
        return hrefPath;
    }

    public void setHrefPath(String hrefPath) {
        this.hrefPath = hrefPath;
    }

    public List<Menu> getChild() {
        return child;
    }

    public void setChild(List<Menu> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", iconCls='" + iconCls + '\'' +
                ", parentId=" + parentId +
                ", hrefPath='" + hrefPath + '\'' +
                ", child=" + child +
                '}';
    }
}
