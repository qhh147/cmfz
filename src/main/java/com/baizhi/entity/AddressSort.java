package com.baizhi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/6/1.
 */
public class AddressSort implements Serializable{
    private String province;
    private Integer counts;

    @Override
    public String toString() {
        return "AddressSort{" +
                "province='" + province + '\'' +
                ", counts=" + counts +
                '}';
    }

    public AddressSort() {
        super();
    }

    public AddressSort(String province, Integer counts) {
        this.province = province;
        this.counts = counts;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }
}
