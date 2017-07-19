package com.example.wzh.mycombat.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yingchen on 16-7-22.
 */
public class CacheUtils {

    public static void putBoolean(Context context,String key,boolean b){
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,b).commit();
    }
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        return sp.getBoolean(key,false);
    }

    public static  void putString(Context context, String key, String values){
        SharedPreferences sharedPreferences = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key,values).apply();
    }


    public static String getString(Context context,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        return  sharedPreferences.getString(key,"");
    }
}