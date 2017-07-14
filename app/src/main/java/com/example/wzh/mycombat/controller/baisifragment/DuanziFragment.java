package com.example.wzh.mycombat.controller.baisifragment;

import android.view.View;
import android.widget.TextView;

import com.example.wzh.mycombat.base.BaseFragment;

/**
 * Created by WZH on 2017/7/14.
 */

public class DuanziFragment extends BaseFragment{
    @Override
    public View initView() {
        TextView textView = new TextView(mContext);
        textView.setText("段子页面");
        textView.setTextSize(50);
        return textView;
    }
}
