package com.example.wzh.mycombat.controller.activity;

import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseActivity;

import butterknife.InjectView;

import static com.example.wzh.mycombat.R.id.ib_back;

public class HTMLActivity extends BaseActivity {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.ib_search)
    ImageButton ibSearch;
    @InjectView(ib_back)
    ImageButton ibBack;
    @InjectView(R.id.ib_shopping)
    ImageButton ibShopping;
    @InjectView(R.id.ib_share)
    ImageButton ibShare;
    @InjectView(R.id.ib_setting)
    ImageButton ibSetting;
    @InjectView(R.id.webview)
    WebView webview;
    private String hUrl;
    private String topic_name;


    @Override
    public int getLayoutId() {
        return R.layout.activity_zuan_ti;
    }

    @Override
    public void initView() {
        super.initView();
        ibBack.setVisibility(View.VISIBLE);
        ibSearch.setVisibility(View.GONE);
        ibShopping.setVisibility(View.GONE);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HTMLActivity.this.finish();
            }
        });
    }

    @Override
    public void initData() {
        hUrl = getIntent().getStringExtra("HUrl");
        topic_name = getIntent().getStringExtra("topic_name");
        tvTitle.setText(topic_name);

    }

    @Override
    public void initListener() {
        //设置WebView属性，能够执行Javascript脚本
        webview.getSettings().setJavaScriptEnabled(true);
        //加载需要显示的网页
        webview.loadUrl(hUrl);
        //  webview.setWebViewClient(new HelloWebViewClient ());
    }


//    private class HelloWebViewClient extends WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
//            return true;
//        }
//    }
}
