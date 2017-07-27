package com.example.wzh.mycombat.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by WZH on 2017/7/5.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.inject(this);

        initView();

        initData();

        initListener();

    }

    public void initView() {

    }


    public abstract int getLayoutId();
    public abstract void initData();
    public abstract void initListener();

    public void showToast(String messages){
        Toast.makeText(this, messages, Toast.LENGTH_SHORT).show();
    }


}
