package com.example.wzh.mycombat.controller.bilibilifragent;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseFragment;
import com.example.wzh.mycombat.controller.activity.HTMLActivity;
import com.example.wzh.mycombat.controller.adapter.HomePagerAdapter;
import com.example.wzh.mycombat.controller.adapter.MagnizeAdapter;
import com.example.wzh.mycombat.modle.bean.DrBean;
import com.example.wzh.mycombat.modle.bean.LiveAppIndexInfo;
import com.example.wzh.mycombat.utils.OnItemClickListener;
import com.example.wzh.mycombat.view.CircleImageView;
import com.example.wzh.mycombat.view.NoScrollViewPager;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.Gson;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

import static com.example.wzh.mycombat.R.id.recyclerview;

/**
 * Created by WZH on 2017/7/25.
 */
public class HomePageFragment extends BaseFragment {


    @InjectView(R.id.toolbar_user_avatar)
    CircleImageView toolbarUserAvatar;
    @InjectView(R.id.navigation_layout)
    LinearLayout navigationLayout;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.sliding_tabs)
    SlidingTabLayout slidingTabs;
    @InjectView(R.id.view_pager)
    NoScrollViewPager viewPager;
    @InjectView(R.id.search_view)
    MaterialSearchView searchView;
    private HomePagerAdapter mHomeAdapter;


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home_pager, null);
        ButterKnife.inject(this, view);


        return view;
    }


    private void initViewPager() {
        mHomeAdapter = new HomePagerAdapter(getChildFragmentManager(),
                mContext) ;
        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(mHomeAdapter);
        slidingTabs.setViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }

    @Override
    public void initData() {
        super.initData();
        initViewPager();
    }



    @Override
    public void initListener() {
        super.initListener();

    }

//    private void initSearchView() {
//
//        //初始化SearchBar
//        searchView.setVoiceSearch(false);
//        searchView.setCursorDrawable(R.drawable.custom_cursor);
//        searchView.setEllipsize(true);
//        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
//        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                TotalStationSearchActivity.launch(getActivity(), query);
//                return false;
//            }
//
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                return false;
//            }
//        });
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
