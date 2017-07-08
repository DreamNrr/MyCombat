package com.example.wzh.mycombat.controller.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class FenleiActivity extends BaseActivity {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.ib_search)
    ImageButton ibSearch;
    @InjectView(R.id.ib_back)
    ImageButton ibBack;
    @InjectView(R.id.ib_shopping)
    ImageButton ibShopping;
    @InjectView(R.id.ib_share)
    ImageButton ibShare;
    @InjectView(R.id.ib_setting)
    ImageButton ibSetting;
    @InjectView(R.id.jiage_tv)
    TextView jiageTv;
    @InjectView(R.id.shaixuan_btn)
    ImageView shaixuanBtn;
    @InjectView(R.id.gridview)
    GridView gridview;
    @InjectView(R.id.activity_fenlei)
    LinearLayout activityFenlei;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fenlei;
    }

    @Override
    public void initData() {

        String url = getIntent().getStringExtra("Url");
        Log.e("TTT",url);
    }

    @Override
    public void initListener() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
