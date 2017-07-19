package com.example.wzh.mycombat.modle.table;

/**
 * Created by WZH on 2017/7/3.
 */

public class ContactTable {
    public static final String TABLE_NAME = "contact";
    public static final String COL_USER_NAME = "username";

    public static final String COL_USER_PASSWORD = "password";

    public static final String CREATE_TABLE = "create table " +
            TABLE_NAME + "("  + COL_USER_NAME +
            " text,"  + COL_USER_PASSWORD + " text)";


}
