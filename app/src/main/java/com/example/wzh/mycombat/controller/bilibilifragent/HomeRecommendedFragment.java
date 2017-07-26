package com.example.wzh.mycombat.controller.bilibilifragent;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.wzh.mycombat.base.BaseFragment;

/**
 * Created by WZH on 2017/7/26.
 */
public class HomeRecommendedFragment extends BaseFragment{
    @Override
    public View initView() {
        TextView textView = new TextView(mContext);
        textView.setText("推荐");
        textView.setTextSize(50);
        textView.setTextColor(Color.BLACK);
        return textView;
    }
}
