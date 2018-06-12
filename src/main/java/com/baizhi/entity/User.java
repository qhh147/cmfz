package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baizhi.annotation.UserAnnotation;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/6/1.
 */
public class User implements Serializable{
    @UserAnnotation(name="编号")
    private int id;
    @UserAnnotation(name="名称")
    private String username;
    @UserAnnotation(name="密码")
    private String password;
    @UserAnnotation(name="性别")
    private String sex;
    @UserAnnotation(name="账号状态")
    private String status;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format ="yyyy-MM-dd")
    @UserAnnotation(name="注册日期")
    private Date bdate;
    @UserAnnotation(name="头像")
    private String headPic;
    @UserAnnotation(name="法号")
    private String farmington;
    @UserAnnotation(name="昵称")
    private String nickname;
    @UserAnnotation(name="省")
    private String province;
    @UserAnnotation(name="市")
    private String city;
    @UserAnnotation(name="详细地区")
    private String location;
    @UserAnnotation(name="个人签名")
    private String description;
    @UserAnnotation(name="电话")
    private String phone;
    @UserAnnotation(name="上师")
    private String shangshi;
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", status='" + status + '\'' +
                ", bdate=" + bdate +
                ", headPic='" + headPic + '\'' +
                ", farmington='" + farmington + '\'' +
                ", nickname='" + nickname + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", shangshi='" + shangshi + '\'' +
                '}';
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getFarmington() {
        return farmington;
    }

    public void setFarmington(String farmington) {
        this.farmington = farmington;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShangshi() {
        return shangshi;
    }

    public void setShangshi(String shangshi) {
        this.shangshi = shangshi;
    }
}
