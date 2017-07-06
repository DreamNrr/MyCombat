package com.example.wzh.mycombat.controller.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.wzh.mycombat.base.BaseFragment;

/**
 * Created by WZH on 2017/7/5.
 */

public class DarenFragment extends BaseFragment
{
    private TextView textView;

    @Override
    public View initView() {
       textView = new TextView(mContext);
        textView.setText("我是达人页面");
        textView.setTextColor(Color.BLUE);
        textView.setTextSize(30);
        return textView;
    }
}
