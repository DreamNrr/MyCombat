package com.example.wzh.mycombat.controller.shopfragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.wzh.mycombat.base.BaseFragment;

/**
 * Created by WZH on 2017/7/6.
 */

public class FenleiFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
         textView = new TextView(mContext);
        textView.setText("分类");
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(80);
        return textView;
    }

}
