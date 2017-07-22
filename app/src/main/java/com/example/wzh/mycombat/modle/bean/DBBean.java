package com.example.wzh.mycombat.modle.bean;

/**
 * Created by WZH on 2017/7/21.
 */

/*
 public static final String TABLE_NAME = "goods";
 public static final String GOODS_IMURL = "imageUrl";
 public static final String GOODS_NAME = "goodsName";
 public static final String GOODS_PRICE = "price";
 public static final String GOODS_CONTENT = "content";
 public static final String GOODS_COUNT = "count";
 */

public class DBBean {
    private boolean isChicked;
    private String did;
    private String imageUrl;
    private String goodsName;
    private String price;
    private String content;
    private int count;

    public DBBean( String did,String imageUrl, String goodsName, String price, String content, int count) {
        this.did = did;
        this.imageUrl = imageUrl;
        this.goodsName = goodsName;
        this.price = price;
        this.content = content;
        this.count = count;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public boolean isChicked() {
        return isChicked;
    }

    public void setChicked(boolean chicked) {
        isChicked = chicked;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "DBBean{" +
                "imageUrl='" + imageUrl + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", price='" + price + '\'' +
                ", content='" + content + '\'' +
                ", count=" + count +
                '}';
    }
}
