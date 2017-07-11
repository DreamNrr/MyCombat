package com.example.wzh.mycombat.utils;

/**
 * Created by WZH on 2017/7/11.
 */

public class DateChange {
    public static String dateFormat(String date){
        String a = "";

        if(date.equals("01")) {
            a = "JAN";
        }else if(date.equals("02")) {
            a = "FEB";
        }else if(date.equals("03")) {
            a = "MAR";
        }else if(date.equals("04")) {
            a = "APR";
        }else if(date.equals("05")) {
            a = "MAY";
        }else if(date.equals("06")) {
            a = "JUN";
        }else if(date.equals("07")) {
            a = "JUL";
        }else if(date.equals("08")) {
            a = "AUG";
        }else if(date.equals("09")) {
            a = "SEP";
        }else if(date.equals("10")) {
            a = "OCT";
        }else if(date.equals("11")) {
            a = "NOV";
        }else if(date.equals("12")) {
            a = "DEC";
        }

        return a;
    }
}
