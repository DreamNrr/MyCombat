package com.example.wzh.mycombat.controller.fragment;

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
import com.example.wzh.mycombat.modle.bean.GoodBean;
import com.example.wzh.mycombat.utils.CacheUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by WZH on 2017/7/5.
 */

public class GoodFragment extends BaseFragment {

    private static final String TAG = GoodFragment.class.getSimpleName();
    @InjectView(R.id.listview)
    ListView listview;
    @InjectView(R.id.progressbar)
    ProgressBar progressbar;
    @InjectView(R.id.tv_nomedia)
    TextView tvNomedia;
    private String GoodUrl = "http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.2.8/0-20.json?market=baidu&udid=863425026599592&appname=baisibudejie&os=4.2.2&client=android&visiting=&mac=98%3A6c%3Af5%3A4b%3A72%3A6d&ver=6.2.8";
//    private String NET_AUDIO_URL = " http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.6.3/0-20.json";
    private GoodFragmentAdapter adapter;
    private List<GoodBean.ListBean> datas;

    private final static String LAST_URL = "http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.2.8/0-";
    private final static String NEXT_URL = ".json?market=baidu&udid=863425026599592&appname=baisibudejie&os=4.2.2&client=android&visiting=&mac=98%3A6c%3Af5%3A4b%3A72%3A6d&ver=6.2.8\\";
    private int count = 30;
    private MaterialRefreshLayout refresh;
    private boolean isLoadMore = false;

    //重写视图
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_good, null);
        ButterKnife.inject(this, view);
        refresh = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
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
                String newUrl = LAST_URL + count + NEXT_URL;
                final RequestParams request = new RequestParams(newUrl);
                x.http().get(request, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("TAG", result);
                        processData(result);
                        refresh.finishRefreshLoadMore();
                        count += 10;
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
                if(listEntity !=null ) {
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

    //解析
    private  List<GoodBean.ListBean> parsedJson(String json) {
        GoodBean GoodBean = new Gson().fromJson(json, GoodBean.class);
        return GoodBean.getList();
    }

    private void processData(String saveJson) {

        datas = parsedJson(saveJson);
        //LogUtil.e(GoodBean.getList().get(0).getText()+"-----------");
        // datas = GoodBean.getList();

        if (datas != null && datas.size() > 0) {
            //有视频
            tvNomedia.setVisibility(View.GONE);
            //设置适配器
            adapter = new GoodFragmentAdapter(mContext, datas);
            listview.setAdapter(adapter);
        } else {
            //没有视频
            tvNomedia.setVisibility(View.VISIBLE);
        }

        progressbar.setVisibility(View.GONE);
    }
    public void getDataFromNet() {
        RequestParams reques = new RequestParams(GoodUrl);
        Log.e("AAA","reques====="+reques);
        x.http().get(reques, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                CacheUtils.putString(mContext,GoodUrl, result);
              //  LogUtil.e("onSuccess==" + result);
                processData(result);
                refresh.finishRefresh();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("onError==" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("onCancelled==" + cex.getMessage());
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
