package com.example.wzh.mycombat.controller.shopfragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseFragment;
import com.example.wzh.mycombat.controller.activity.PinPaiActivity;
import com.example.wzh.mycombat.controller.adapter.PinpaiAdapter;
import com.example.wzh.mycombat.modle.bean.PpBean;
import com.example.wzh.mycombat.utils.MyItemDecoration;
import com.example.wzh.mycombat.utils.OnItemClickListener;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

import static com.example.wzh.mycombat.utils.BaseUrl.BRAND_URL;

/**
 * Created by WZH on 2017/7/6.
 */

public class PinpaiFragment extends BaseFragment {
//    public final static String BRAND_URL = BASE_URL +
//            "brand/brandList?app_key=Android&count=20&page=1&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;

    private List<PpBean.DataBean.ItemsBean> datas;
    private PinpaiAdapter adapter;

    private int brand_id;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.pinpai_fragment, null);
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
                .url(BRAND_URL)
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
        PpBean bean = new Gson().fromJson(response, PpBean.class);
        datas = bean.getData().getItems();
        if (datas != null && datas.size() > 0) {
            //   progressbar.setVisibility(View.GONE);
            adapter = new PinpaiAdapter(mContext, datas);
            recyclerview.addItemDecoration(new MyItemDecoration());
            recyclerview.setAdapter(adapter);
            recyclerview.setLayoutManager(
                    new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
//            recyclerview.setLayoutManager(new GridLayoutManager(mContext, 2,
//                    GridLayoutManager.VERTICAL, false));

            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view) {
                    int childAdapterPosition = recyclerview.getChildAdapterPosition(view);
                    brand_id = datas.get(childAdapterPosition).getBrand_id();
                    String brand_logo = datas.get(childAdapterPosition).getBrand_logo();
                    String brand_name = datas.get(childAdapterPosition).getBrand_name();

                    Log.e("TAG","brand_id===="+brand_id);
                    Intent intent = new Intent(mContext, PinPaiActivity.class);
                    intent.putExtra("BID",brand_id);
                    intent.putExtra("brand_logo",brand_logo);
                    intent.putExtra("brand_name",brand_name);
                    startActivity(intent);
                    Toast.makeText(mContext, "点击了"+childAdapterPosition, Toast.LENGTH_SHORT).show();
                }
            });



        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


}