package com.example.wzh.mycombat.modle.table;

/**
 * Created by WZH on 2017/7/1.
 */
public class GoodsTable {

    //商品图片，名称，现价，选项，数量


    public static final String TABLE_NAME = "goods";
    public static final String GOODS_IMURL = "imageUrl";
    public static final String GOODS_NAME = "goodsName";
    public static final String GOODS_PRICE = "price";
    public static final String GOODS_CONTENT = "content";
    public static final String GOODS_COUNT = "count";

    public static final String CREATE_TABLE = "create table "+TABLE_NAME+"("
            +GOODS_IMURL+" text ,"
            +GOODS_NAME +" text, "
            +GOODS_PRICE +" text, "
            +GOODS_CONTENT +" text, "
            +GOODS_COUNT +" integer)";
}
