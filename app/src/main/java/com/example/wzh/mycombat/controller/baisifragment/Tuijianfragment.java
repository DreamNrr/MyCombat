package com.example.wzh.mycombat.controller.baisifragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseFragment;
import com.example.wzh.mycombat.controller.activity.ShowImageAndGifActivity;
import com.example.wzh.mycombat.controller.adapter.GoodFragmentAdapter;
import com.example.wzh.mycombat.controller.fragment.GoodFragment;
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

public class Tuijianfragment extends BaseFragment {
    @InjectView(R.id.listview)
    ListView listview;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;
    @InjectView(R.id.progressbar)
    ProgressBar progressbar;
    @InjectView(R.id.tv_nomedia)
    TextView tvNomedia;
    private static final String TAG = GoodFragment.class.getSimpleName();
//    private String GoodUrl = "http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.2.8/0-20.json?market=baidu&udid=863425026599592&appname=baisibudejie&os=4.2.2&client=android&visiting=&mac=98%3A6c%3Af5%3A4b%3A72%3A6d&ver=6.2.8";

//    private String NET_AUDIO_URL = "http://s.budejie.com" +
//            "/topic/list/jingxuan/1/budejie-android-6.2.8" +
//            "/0-20.json?market=baidu&udid=863425026599592&" +
//            "appname=baisibudejie&os=4.2.2&client=androi" +
//            "d&visiting=&mac=98%3A6c%3Af5%3A4b%3A72%3A" +
//            "6d&ver=6.2.8";
//
//    private final static String LAST_URL = "http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.2.8/0-";
//    private final static String NEXT_URL = ".json?market=baidu&udid=863425026599592&appname=baisibudejie&os=4.2.2&client=android&visiting=&mac=98%3A6c%3Af5%3A4b%3A72%3A6d&ver=6.2.8\\";

    private String GoodUrl = "http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.6.3/0-20.json";
    private GoodFragmentAdapter adapter;
    private List<GoodBean.ListBean> datas;
//
    private final static String LAST_URL = "http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.6.3/0-20.json";
    private final static String NEXT_URL = "http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.6.3/0-";
    private int count = 20;
    private boolean isLoadMore = false;
    private List<GoodBean.ListBean> trailers;

    //重写视图
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
                Log.e("AAA","newUrl===" + newUrl);
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


//        String saveJson = CacheUtils.getString(mContext, GoodUrl);
//        if (!TextUtils.isEmpty(saveJson)) {
//            processData(saveJson);
//        }

        getDataFromNet();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                GoodBean.ListBean listEntity = datas.get(position);
                if (listEntity != null) {
                    //3.传递视频列表
                    Intent intent = new Intent(mContext, ShowImageAndGifActivity.class);
                    if (listEntity.getType().equals("gif")) {
                        String url = listEntity.getGif().getImages().get(0);
                        intent.putExtra("url", url);
                        mContext.startActivity(intent);
                    } else if (listEntity.getType().equals("image")) {
                        String url = listEntity.getImage().getBig().get(0);
                        intent.putExtra("url", url);
                        mContext.startActivity(intent);
                    }
                }
            }
        });
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

                adapter.setDatas(isLoadMore,datas);
                progressbar.setVisibility(View.GONE);

            } else {
                tvNomedia.setVisibility(View.INVISIBLE);
            }
        } else {
            trailers = netAudioBean.getList();
            if (trailers != null && trailers.size() > 0) {
                tvNomedia.setVisibility(View.GONE);
                adapter.setDatas(isLoadMore, trailers);
                progressbar.setVisibility(View.GONE);
            } else {
                tvNomedia.setVisibility(View.INVISIBLE);
            }

        }
    }




    public void getDataFromNet() {
        RequestParams reques = new RequestParams(GoodUrl);
        Log.e("AAA", "reques=====" + reques);
        x.http().get(reques, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

//                CacheUtils.putString(mContext, GoodUrl, result);
                Log.e("AAA","onSuccess==" + result);
                processData(result);
                refresh.finishRefresh();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("AAA","onError==" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("AAA","onCancelled==" + cex.getMessage());
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
