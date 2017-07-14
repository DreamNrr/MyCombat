package com.example.wzh.mycombat.controller.baisifragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseFragment;
import com.example.wzh.mycombat.controller.adapter.GoodFragmentAdapter;
import com.example.wzh.mycombat.modle.bean.GoodBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WZH on 2017/7/14.
 */

public class DuanziFragment extends BaseFragment {
    @InjectView(R.id.listview)
    ListView listview;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;
    @InjectView(R.id.progressbar)
    ProgressBar progressbar;
    @InjectView(R.id.tv_nomedia)
    TextView tvNomedia;


    private String GoodDUrl = "http://s.budejie.com/topic/tag-topic/64/hot/budejie-android-6.6.3/0-20.json";
    private GoodFragmentAdapter adapter;
    private List<GoodBean.ListBean> datas;
    private final static String NEXT_URL = "http://s.budejie.com/topic/tag-topic/64/hot/budejie-android-6.6.3/0-";
    private int count = 20;
    private boolean isLoadMore = false;
    private List<GoodBean.ListBean> trailers;



    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_baisi_tuijian, null);
        ButterKnife.inject(this, view);
        adapter = new GoodFragmentAdapter(mContext);
        listview.setAdapter(adapter);
//        refresh = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                isLoadMore = false;
                getDataFromNet();

            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);

                isLoadMore = true;
                getMoreData();

            }

        });
        return view;
    }


    private void getMoreData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String newUrl = NEXT_URL + count + ".json";
                Log.e("AAA", "newUrl===" + newUrl);
                final RequestParams request = new RequestParams(newUrl);
                x.http().get(request, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("TAG", result);
                        processData(result);
                        refresh.finishRefreshLoadMore();
                        count += 20;
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Toast.makeText(mContext, "onError--", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
            }
        }).start();
    }

    @Override
    public void initData() {
        super.initData();


//        String saveJson = CacheUtils.getString(mContext, GoodDUrl);
//        if (!TextUtils.isEmpty(saveJson)) {
//            processData(saveJson);
//        }

        getDataFromNet();

    }

//    //解析
//    private List<GoodBean.ListBean> parsedJson(String json) {
//        GoodBean GoodBean = new Gson().fromJson(json, GoodBean.class);
//        return GoodBean.getList();
//    }

    private void processData(String result) {
        Gson gson = new Gson();
        GoodBean netAudioBean = gson.fromJson(result, GoodBean.class);
        if (!isLoadMore) {
            datas = netAudioBean.getList();

            if (datas != null && datas.size() > 0) {
                tvNomedia.setVisibility(View.GONE);

                adapter.setDatas(isLoadMore, datas);
                progressbar.setVisibility(View.GONE);

            } else {
                tvNomedia.setVisibility(View.INVISIBLE);
            }
        } else {
            trailers = netAudioBean.getList();
            if (trailers != null && trailers.size() > 0) {
                tvNomedia.setVisibility(View.GONE);
                listview.setSelection(adapter.getCount());
                adapter.setDatas(isLoadMore, trailers);
                progressbar.setVisibility(View.GONE);
            } else {
                tvNomedia.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void getDataFromNet() {
        RequestParams reques = new RequestParams(GoodDUrl);
        Log.e("AAA", "reques=====" + reques);
        x.http().get(reques, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

//                CacheUtils.putString(mContext, GoodDUrl, result);
                Log.e("AAA", "onSuccess==" + result);
                processData(result);
                refresh.finishRefresh();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("AAA", "onError==" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("AAA", "onCancelled==" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                LogUtil.e("onFinished==");
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
 }

