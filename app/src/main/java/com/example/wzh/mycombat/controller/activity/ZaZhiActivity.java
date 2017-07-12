package com.example.wzh.mycombat.controller.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseActivity;
import com.example.wzh.mycombat.controller.adapter.TitleFragmentPagerAdapter;
import com.example.wzh.mycombat.controller.zzfragment.ZZFFragment;
import com.example.wzh.mycombat.controller.zzfragment.ZZZFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class ZaZhiActivity extends BaseActivity {

    @InjectView(R.id.left_view)
    View leftView;
    @InjectView(R.id.right_view)
    View rightView;
    @InjectView(R.id.tab)
    TabLayout tab;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;
    @InjectView(R.id.left_rl)
    RelativeLayout leftRl;
    @InjectView(R.id.right_rl)
    RelativeLayout rightRl;
    @InjectView(R.id.button)
    LinearLayout button;

    @Override
    public int getLayoutId() {
        return R.layout.activity_za_zhi;
    }

    @Override
    public void initData() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ZZFFragment());
        fragments.add(new ZZZFragment());

        TitleFragmentPagerAdapter adapter =
                new TitleFragmentPagerAdapter
                        (getSupportFragmentManager(),
                                fragments, new String[]{"分类", "作者"});
        viewpager.setAdapter(adapter);

        tab.setupWithViewPager(viewpager);
        tab.getTabAt(0).select();
    }

    @Override
    public void initListener() {


        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    leftView.setVisibility(View.GONE);
                    rightView.setVisibility(View.VISIBLE);
                } else {
                    leftView.setVisibility(View.VISIBLE);
                    rightView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    @OnClick({R.id.left_rl, R.id.right_rl,R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_rl:
                tab.getTabAt(0).select();
                break;
            case R.id.right_rl:
                tab.getTabAt(1).select();
                break;
            case R.id.button:
                ZaZhiActivity.this.finish();
                break;
        }
    }

}
