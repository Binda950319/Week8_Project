package com.wbh.week8_project.javabean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Administrator on 2016/10/21.
 */
@Entity
public class Component {
    @Id(autoincrement = true)
    long id;
    String picUrl;
    String price;
    String origin_price;
    String description;

    public Component() {
    }
    @Generated(hash = 1483819567)
    public Component(long id, String picUrl, String price, String origin_price, String description) {
        this.id = id;
        this.picUrl = picUrl;
        this.price = price;
        this.origin_price = origin_price;
        this.description = description;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrigin_price() {
        return origin_price;
    }

    public void setOrigin_price(String origin_price) {
        this.origin_price = origin_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
