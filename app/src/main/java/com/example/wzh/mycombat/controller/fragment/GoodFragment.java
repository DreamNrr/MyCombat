package com.example.wzh.mycombat.controller.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseFragment;
import com.example.wzh.mycombat.controller.adapter.TitleFragmentPagerAdapter;
import com.example.wzh.mycombat.controller.baisifragment.DuanziFragment;
import com.example.wzh.mycombat.controller.baisifragment.Tuijianfragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by WZH on 2017/7/5.
 */

public class GoodFragment extends BaseFragment {


    @InjectView(R.id.tabLayout)
    TabLayout tabLayout;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;

    //重写视图
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_good, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Tuijianfragment());
        fragments.add(new DuanziFragment());

        TitleFragmentPagerAdapter adapter =
                new TitleFragmentPagerAdapter
                        (getChildFragmentManager(),
                                fragments, new String[]{"推荐", "段子"});
        viewpager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewpager);
//        tabLayout.getTabAt(0).select();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
