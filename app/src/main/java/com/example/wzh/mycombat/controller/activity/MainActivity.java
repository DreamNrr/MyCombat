package com.example.wzh.mycombat.controller.activity;

import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseActivity;
import com.example.wzh.mycombat.base.BaseFragment;
import com.example.wzh.mycombat.controller.fragment.DarenFragment;
import com.example.wzh.mycombat.controller.fragment.GoodFragment;
import com.example.wzh.mycombat.controller.fragment.MagazineFragment;
import com.example.wzh.mycombat.controller.fragment.SelfFragment;
import com.example.wzh.mycombat.controller.fragment.ShopFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class MainActivity extends BaseActivity {


    @InjectView(R.id.shop_rb)
    RadioButton shopRb;
    @InjectView(R.id.mgz_rb)
    RadioButton mgzRb;
    @InjectView(R.id.daren_rb)
    RadioButton darenRb;
    @InjectView(R.id.good_rb)
    RadioButton goodRb;
    @InjectView(R.id.self_rb)
    RadioButton selfRb;
    @InjectView(R.id.radioGroup)
    RadioGroup radioGroup;
    @InjectView(R.id.fraglayout)
    FrameLayout fraglayout;
    @InjectView(R.id.activity_main)
    LinearLayout activityMain;

    private List<BaseFragment> fragments;
    private BaseFragment tempFragment;
    private int position = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        initFragment();
        tempFragment = fragments.get(position);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fraglayout, tempFragment);
        ft.commit();
    }

    @Override
    public void initListener() {

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switchbuttonFragment(i);
            }
        });
        radioGroup.check(R.id.shop_rb);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new ShopFragment());
        fragments.add(new MagazineFragment());
        fragments.add(new DarenFragment());
        fragments.add(new GoodFragment());
        fragments.add(new SelfFragment());
    }

    private void switchbuttonFragment(int checked) {
        switch (checked) {
            case R.id.shop_rb:
                position = 0;
                break;
            case R.id.mgz_rb:
                position = 1;
                break;
            case R.id.daren_rb:
                position = 2;
                break;
            case R.id.good_rb:
                position = 3;
                break;
            case R.id.self_rb:
                position = 4;
                break;
        }
        BaseFragment currentFragment = getFragment(position);
        switchFragment(currentFragment);
    }

    private void switchFragment(BaseFragment currentFragment) {
        if (currentFragment != tempFragment) {

            if (currentFragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                if (!currentFragment.isAdded()) {
                    if (tempFragment != null) {
                        ft.hide(tempFragment);
                    }
                    ft.add(R.id.fraglayout, currentFragment);
                }
                //如果添加了就隐藏
                else {
                    //隐藏上次显示的
                    if (tempFragment != null) {
                        ft.hide(tempFragment);
                    }
                    //显示
                    ft.show(currentFragment);
                }
                //最后统一提交
                ft.commit();
                //重新赋值
                tempFragment = currentFragment;
            }

        }
    }

    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

}
