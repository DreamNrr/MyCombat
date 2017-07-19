package com.example.wzh.mycombat.controller.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseFragment;
import com.example.wzh.mycombat.controller.adapter.TitleFragmentPagerAdapter;
import com.example.wzh.mycombat.controller.shopfragment.FenleiFragment;
import com.example.wzh.mycombat.controller.shopfragment.LiwuFragment;
import com.example.wzh.mycombat.controller.shopfragment.PinpaiFragment;
import com.example.wzh.mycombat.controller.shopfragment.ShouyeFragment;
import com.example.wzh.mycombat.controller.shopfragment.ZhuantiFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WZH on 2017/7/5.
 */

public class ShopFragment extends BaseFragment {
    @InjectView(R.id.tab)
    TabLayout tab;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    public View initView() {

        View view = View.inflate(mContext, R.layout.shop_fragment, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FenleiFragment());
        fragments.add(new PinpaiFragment());
        fragments.add(new ShouyeFragment());
        fragments.add(new ZhuantiFragment());
        fragments.add(new LiwuFragment());

        TitleFragmentPagerAdapter adapter =
                new TitleFragmentPagerAdapter
                        (getFragmentManager(),
                                fragments, new String[]{"分类", "品牌", "首页", "专题", "礼物"});
        viewpager.setAdapter(adapter);

        tab.setupWithViewPager(viewpager);
        tab.getTabAt(0).select();
    }

    @Override
    public void initListener() {
        super.initListener();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
