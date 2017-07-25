package com.example.wzh.mycombat.controller.fragment;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatDelegate;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseFragment;
import com.example.wzh.mycombat.controller.bilibilifragent.HomePageFragment;
import com.example.wzh.mycombat.view.CircleImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WZH on 2017/7/5.
 */

public class SelfFragment extends BaseFragment implements NavigationView.OnNavigationItemSelectedListener {

    @InjectView(R.id.rl_main)
    FrameLayout rlMain;
    @InjectView(R.id.navigation_view)
    NavigationView navigationView;
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;


    private Fragment mHomePageFragment;

    //private HomePageFragment mHomePageFragment;


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.bilibili_layout, null);
        ButterKnife.inject(this, view);
        navigationView.setItemIconTintList(null);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //初始化Fragment
        initFragments();
        //初始化侧滑菜单
        initNavigationView();
    }

    /**
     * 初始化Fragments
     */
    private void initFragments() {
        mHomePageFragment = new HomePageFragment();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.rl_main, mHomePageFragment)
                .show(mHomePageFragment).commit();
    }


    /**
     * 初始化NavigationView
     */
    private void initNavigationView() {

        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        CircleImageView mUserAvatarView = (CircleImageView) headerView.findViewById(
                R.id.user_avatar_view);
        TextView mUserName = (TextView) headerView.findViewById(R.id.user_name);
        TextView mUserSign = (TextView) headerView.findViewById(R.id.user_other_info);
        ImageView mSwitchMode = (ImageView) headerView.findViewById(R.id.iv_head_switch_mode);
        //设置头像
        mUserAvatarView.setImageResource(R.drawable.ic_hotbitmapgg_avatar);
        //设置用户名 签名
        mUserName.setText(getResources().getText(R.string.hotbitmapgg));
        mUserSign.setText(getResources().getText(R.string.about_user_head_layout));
        //设置日夜间模式切换
        mSwitchMode.setOnClickListener(v -> switchNightMode());

//        boolean flag = CacheUtils.getBoolean(ConstantUtil.SWITCH_MODE_KEY, false);
        boolean flag = true;
        if (flag) {
            mSwitchMode.setImageResource(R.drawable.ic_switch_daily);
        } else {
            mSwitchMode.setImageResource(R.drawable.ic_switch_night);
        }
    }


    /**
     * 日夜间模式切换
     */
    private void switchNightMode() {

        boolean isNight = false;
       // boolean isNight = CacheUtils.getBoolean(ConstantUtil.SWITCH_MODE_KEY, false);
        if (isNight) {
            // 日间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
           // CacheUtils.putBoolean(ConstantUtil.SWITCH_MODE_KEY, false);
        } else {
            // 夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
           // CacheUtils.putBoolean(ConstantUtil.SWITCH_MODE_KEY, true);
        }

        getActivity().recreate();
    }





    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.item_home:
                // 主页
                initFragments();
                return true;

            case R.id.item_download:
                // 离线缓存
               // startActivity(new Intent(mContext, OffLineDownloadActivity.class));
                return true;

            case R.id.item_vip:
                //大会员
             //   startActivity(new Intent(mContext, VipActivity.class));
                return true;

            case R.id.item_favourite:
                // 我的收藏
                return true;

            case R.id.item_history:
                // 历史记录
                return true;

            case R.id.item_group:
                // 关注的人
                return true;

            case R.id.item_tracker:
                // 我的钱包
                return true;

            case R.id.item_theme:
                // 主题选择
                return true;

            case R.id.item_app:
                // 应用推荐
                return true;

            case R.id.item_settings:
                // 设置中心
                return true;
        }

        return false;
    }

    /**
     * DrawerLayout侧滑菜单开关
     */
    public void toggleDrawer() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

//    /**
//     * 监听back键处理DrawerLayout和SearchView
//     */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (mDrawerLayout.isDrawerOpen(mDrawerLayout.getChildAt(1))) {
//                mDrawerLayout.closeDrawers();
//            } else {
//                if (mHomePageFragment != null) {
//                    if (mHomePageFragment.isOpenSearchView()) {
//                        mHomePageFragment.closeSearchView();
//                    } else {
//                        exitApp();
//                    }
//                } else {
//                    exitApp();
//                }
//            }
//        }
//
//        return true;
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
