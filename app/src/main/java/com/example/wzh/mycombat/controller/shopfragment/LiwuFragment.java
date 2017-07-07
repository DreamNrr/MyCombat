package com.example.wzh.mycombat.controller.shopfragment;

import android.view.View;
import android.widget.TextView;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseFragment;

/**
 * Created by WZH on 2017/7/6.
 */

public class LiwuFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.liwu_fragment, null);
        return view;
    }

}
