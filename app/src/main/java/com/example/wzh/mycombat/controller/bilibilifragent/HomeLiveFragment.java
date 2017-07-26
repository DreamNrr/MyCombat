package com.example.wzh.mycombat.controller.bilibilifragent;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseFragment;
import com.example.wzh.mycombat.controller.adapter.LiveAppIndexAdapter;
import com.example.wzh.mycombat.modle.bean.LiveAppIndexInfo;
import com.example.wzh.mycombat.utils.SnackbarUtil;
import com.example.wzh.mycombat.view.CustomEmptyView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by WZH on 2017/7/26.
 */
public class HomeLiveFragment extends BaseFragment {


    @InjectView(R.id.recycle)
    RecyclerView recycle;
    @InjectView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @InjectView(R.id.empty_layout)
    CustomEmptyView emptyLayout;
    private LiveAppIndexAdapter mLiveAppIndexAdapter;

    private String url = "http://live.bilibili.com/AppNewIndex/common?_device=android&appkey=1d8b6e7d45233436&build=501000&mobi_app=android&platform=android&scale=hdpi&ts=1490013188000&sign=92541a11ed62841120e786e637b9db3";

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home_live, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getFromNet();
        initRefreshLayout();
    }


    private void getFromNet() {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败==" + e.getMessage());
                        initEmptyView();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        processData(response);
                    }
                });
    }

    private void processData(String response) {
        LiveAppIndexInfo bean = new Gson().fromJson(response, LiveAppIndexInfo.class);
        mLiveAppIndexAdapter = new LiveAppIndexAdapter(getActivity());
        mLiveAppIndexAdapter.setLiveInfo(bean);
        finishTask();

        recycle.setAdapter(mLiveAppIndexAdapter);
        GridLayoutManager layout = new GridLayoutManager(getActivity(), 12);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        layout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

            @Override
            public int getSpanSize(int position) {

                return mLiveAppIndexAdapter.getSpanSize(position);
            }
        });

        recycle.setLayoutManager(layout);
    }

    protected void initRefreshLayout() {

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(this::getFromNet);
        swipeRefreshLayout.post(() -> {

            swipeRefreshLayout.setRefreshing(true);
            getFromNet();
        });
    }

    private void initEmptyView() {

        swipeRefreshLayout.setRefreshing(false);
        emptyLayout.setVisibility(View.VISIBLE);
        recycle.setVisibility(View.GONE);
        emptyLayout.setEmptyImage(R.drawable.img_tips_error_load_error);
        emptyLayout.setEmptyText("加载失败~(≧▽≦)~啦啦啦.");
        SnackbarUtil.showMessage(recycle, "数据加载失败,请重新加载或者检查网络是否链接");
    }


    public void hideEmptyView() {

        emptyLayout.setVisibility(View.GONE);
        recycle.setVisibility(View.VISIBLE);
    }


    protected void finishTask() {

        hideEmptyView();
        swipeRefreshLayout.setRefreshing(false);
        mLiveAppIndexAdapter.notifyDataSetChanged();
        recycle.scrollToPosition(0);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
