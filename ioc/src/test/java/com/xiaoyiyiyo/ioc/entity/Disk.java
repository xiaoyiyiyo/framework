package com.xiaoyiyiyo.ioc.entity;

/**
 * Created by xiaoyiyiyo on 2018/3/9.
 */
public class Disk {

    private String size;

    public Disk(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void read() {
        System.out.println("reading data...size = " + size);
    }
}
