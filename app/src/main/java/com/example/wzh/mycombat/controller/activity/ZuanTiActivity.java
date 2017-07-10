package com.example.wzh.mycombat.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class ZuanTiActivity extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String hUrl = getIntent().getStringExtra("HUrl");

        //实例化WebView对象
        webview = new WebView(this);
        //设置WebView属性，能够执行Javascript脚本
        webview.getSettings().setJavaScriptEnabled(true);
        //加载需要显示的网页
        webview.loadUrl(hUrl);
        //设置Web视图
        setContentView(webview);


    }

}
