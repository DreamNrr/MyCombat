package com.example.wzh.mycombat.utils;

import android.util.SparseArray;

import com.example.wzh.mycombat.modle.bean.GoogsBean;

import java.util.ArrayList;

/**
 * Created by WZH on 2017/7/19.
 */

public class CartStorage {

    public static CartStorage instance;
    private SparseArray<GoogsBean> sparseArray;
    private CartStorage(){
        //初始化集合
        sparseArray = new SparseArray<>();
        listTosparseArray();
    }

    private void listTosparseArray() {
        //得到所有数据
        ArrayList<GoogsBean> datas = getAllData();
    }
    /**
     * 得到所有数据---外界用的
     * */
    public ArrayList<GoogsBean> getAllData() {
        return getDBHelperData();
    }
    /*
    * 得到数据库中的数据---内部用
    * */
    private ArrayList<GoogsBean> getDBHelperData() {

        return null;
    }

    public static CartStorage getInstance(){
        if(instance == null) {
            //同步，，，防止有多个线程进来
            //加上锁---单例
            synchronized (CartStorage.class){

                if(instance == null) {
                    instance =  new CartStorage();
                }
            }
        }
        return instance;
    }
}
