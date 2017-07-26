package com.example.wzh.mycombat.controller.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.controller.bilibilifragent.HomeAttentionFragment;
import com.example.wzh.mycombat.controller.bilibilifragent.HomeBangumiFragment;
import com.example.wzh.mycombat.controller.bilibilifragent.HomeDiscoverFragment;
import com.example.wzh.mycombat.controller.bilibilifragent.HomeLiveFragment;
import com.example.wzh.mycombat.controller.bilibilifragent.HomeRecommendedFragment;
import com.example.wzh.mycombat.controller.bilibilifragent.HomeRegionFragment;

/**
 * 主界面Fragment模块Adapter
 */
public class HomePagerAdapter extends FragmentPagerAdapter {
  private final String[] TITLES;

  private Fragment[] fragments;


  public HomePagerAdapter(FragmentManager fm, Context context) {

    super(fm);
    TITLES = context.getResources().getStringArray(R.array.sections);
    fragments = new Fragment[TITLES.length];
  }


  @Override
  public Fragment getItem(int position) {

    if (fragments[position] == null) {
      switch (position) {
        case 0:
          fragments[position] = new HomeLiveFragment();
          break;
        case 1:
          fragments[position] = new HomeRecommendedFragment();
          break;
        case 2:
          fragments[position] = new HomeBangumiFragment();
          break;
        case 3:
          fragments[position] = new HomeRegionFragment();
          break;
        case 4:
          fragments[position] = new HomeAttentionFragment();
          break;
        case 5:
          fragments[position] = new HomeDiscoverFragment();
          break;
        default:
          break;
      }
    }
    return fragments[position];
  }


  @Override
  public int getCount() {

    return TITLES.length;
  }


  @Override
  public CharSequence getPageTitle(int position) {

    return TITLES[position];
  }
}
