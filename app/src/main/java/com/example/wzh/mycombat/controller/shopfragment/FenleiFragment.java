package com.example.wzh.mycombat.controller.shopfragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseFragment;
import com.example.wzh.mycombat.controller.adapter.FenleiAdapter;
import com.example.wzh.mycombat.modle.bean.FlBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

import static com.example.wzh.mycombat.utils.BaseUrl.CATEGORY_URL;

/**
 * Created by WZH on 2017/7/6.
 */

public class FenleiFragment extends BaseFragment {
//    public final static String CATEGORY_URL =
//            BASE_URL + "goods/goodsCategory?app_key=Android&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    @InjectView(R.id.linearlayout)
    LinearLayout linearlayout;
    private List<FlBean.DataBean.ItemsBean> datas;
    private FenleiAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fenlei_fragment, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getFromNet();
    }

    private void getFromNet() {
        OkHttpUtils
                .get()
                .url(CATEGORY_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败==" + e.getMessage());
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        processData(response);
                    }
                });
    }

    private void processData(String response) {
        FlBean bean = new Gson().fromJson(response, FlBean.class);
        datas = bean.getData().getItems();
        if (datas != null && datas.size() > 0) {
            //   progressbar.setVisibility(View.GONE);
            adapter = new FenleiAdapter(mContext, datas);
            recyclerview.setAdapter(adapter);
//            recyclerview.setLayoutManager(
//                    new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            recyclerview.setLayoutManager(new GridLayoutManager(mContext, 2,
                    GridLayoutManager.VERTICAL, false));
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
