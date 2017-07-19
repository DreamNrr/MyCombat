package com.example.wzh.mycombat.modle.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.wzh.mycombat.modle.table.ContactTable;


/**
 * Created by WZH on 2017/7/3.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
            super(context,"account.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ContactTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
