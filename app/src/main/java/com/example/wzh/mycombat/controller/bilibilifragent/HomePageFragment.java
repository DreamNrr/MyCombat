package com.example.wzh.mycombat.controller.bilibilifragent;

import android.view.View;
import android.widget.TextView;

import com.example.wzh.mycombat.base.BaseFragment;

/**
 * Created by WZH on 2017/7/25.
 */
public class HomePageFragment extends BaseFragment {

private TextView textView;
    @Override
    public View initView() {

        textView = new TextView(mContext);
        textView.setText("hahahahahahahha");
        textView.setTextSize(60);

        return this.textView;
    }
}
