package com.example.wzh.mycombat.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by WZH on 2017/7/4.
 */

public class SPUtils {
    public static final String NEW_INVITE = "newInvite";
    private SharedPreferences sp;
    private SPUtils(){}

    private static SPUtils spUtils = new SPUtils();

    public static SPUtils getSpUtils(){
        return spUtils;
    }
    public void init(Context context, String name){
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }
    public void save(String key,Object value){
        SharedPreferences.Editor edit = sp.edit();
        if (value instanceof Boolean){
            edit.putBoolean(key, (Boolean) value).commit();
        }
        if (value instanceof String){
            edit.putString(key, (String) value).commit();
        }

    }
    public Boolean getBolValue(String key){
        return sp.getBoolean(key,false);
    }
}
