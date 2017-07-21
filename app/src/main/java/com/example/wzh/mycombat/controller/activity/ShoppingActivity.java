package com.example.wzh.mycombat.controller.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseActivity;
import com.example.wzh.mycombat.modle.bean.DBBean;
import com.example.wzh.mycombat.modle.db.DBHelper;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;


/**
 * 1.连接数据库
 * 2.从数据库中获取数据
 * 3.设置适配器--加载子布局
 * 4.关闭数据库
 */

public class ShoppingActivity extends BaseActivity {


    @InjectView(R.id.reach_discount_price_tv)
    TextView reachDiscountPriceTv;
    @InjectView(R.id.reach_discount_fl)
    FrameLayout reachDiscountFl;
    @InjectView(R.id.discount_discount_price_tv)
    TextView discountDiscountPriceTv;
    @InjectView(R.id.discount_discount_fl)
    FrameLayout discountDiscountFl;
    @InjectView(R.id.pack_price_tv)
    TextView packPriceTv;
    @InjectView(R.id.pack_fl)
    FrameLayout packFl;
    @InjectView(R.id.ship_price_tv)
    TextView shipPriceTv;
    @InjectView(R.id.ship_fl)
    FrameLayout shipFl;
    @InjectView(R.id.pay_tv)
    TextView payTv;
    @InjectView(R.id.all_check)
    CheckBox allCheck;
    @InjectView(R.id.pay_fee_tv)
    TextView payFeeTv;
    @InjectView(R.id.save_fee_tv)
    TextView saveFeeTv;
    @InjectView(R.id.saving_ll)
    LinearLayout savingLl;
    @InjectView(R.id.bottom_bar_ll)
    LinearLayout bottomBarLl;
    @InjectView(R.id.ib_back)
    ImageButton ibBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.iv_edit)
    Button ivEdit;
    @InjectView(R.id.iv_complete)
    Button ivComplete;
    @InjectView(R.id.cart_lv)
    ListView cartLv;
    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private ArrayList<DBBean> dblist;
    private Cursor cursor;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shopping;
    }

    @Override
    public void initData() {
        //数据库
        dbHelper = new DBHelper(ShoppingActivity.this);
        //连接数据库
        database = dbHelper.getReadableDatabase();

        selectFromDatabase();

    }

    private void selectFromDatabase() {
        dblist = new ArrayList<>();
        //从数据库里面把数据取出来
/*
 public static final String TABLE_NAME = "goods";
 public static final String GOODS_IMURL = "imageUrl";
 public static final String GOODS_NAME = "goodsName";
 public static final String GOODS_PRICE = "price";
 public static final String GOODS_CONTENT = "content";
 public static final String GOODS_COUNT = "count";
 */

        cursor = database.query("goods", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String imageUrl = cursor.getString(cursor.getColumnIndex("imageUrl"));
            String goodsName = cursor.getString(cursor.getColumnIndex("goodsName"));
            String price = cursor.getString(cursor.getColumnIndex("price"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            int count = cursor.getInt(cursor.getColumnIndex("count"));
            DBBean p=new DBBean(imageUrl, goodsName, price, content,count);

            dblist.add(p);
        }
        Log.e("AAA","数据===" + dblist);
    }

    @Override
    public void initListener() {

    }


    @OnClick({R.id.ib_back, R.id.iv_edit, R.id.iv_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                ShoppingActivity.this.finish();
                break;
            case R.id.iv_edit:
                //编辑的点击事件
                break;
            case R.id.iv_complete:
                //完成的点击事件
                break;
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(database != null) {
            database.close();
        }
        if(cursor != null) {
            cursor.close();
        }
    }
}
