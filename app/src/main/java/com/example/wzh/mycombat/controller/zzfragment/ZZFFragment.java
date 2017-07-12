package com.example.wzh.mycombat.controller.zzfragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseFragment;
import com.example.wzh.mycombat.controller.adapter.ZZFLAdapter;
import com.example.wzh.mycombat.modle.bean.ZZFLBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by WZH on 2017/7/12.
 */
public class ZZFFragment extends BaseFragment {

    private static final String ZZ_FL_URL = "http://mobile.iliangcang.com/topic/magazineCatList?app_key=Android&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    private List<ZZFLBean.DataBean.ItemsBean> datas;
    private ZZFLAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_zuozhe_fl, null);
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
                .url(ZZ_FL_URL)
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
        ZZFLBean bean = new Gson().fromJson(response, ZZFLBean.class);
        datas = bean.getData().getItems();
        if (datas != null && datas.size() > 0) {
            //   progressbar.setVisibility(View.GONE);
            adapter = new ZZFLAdapter(mContext, datas);

            recyclerview.setAdapter(adapter);

            recyclerview.setLayoutManager(new GridLayoutManager(mContext, 2,
                    GridLayoutManager.VERTICAL, false));
//            listview.setOnItemClickListener(new OnItemClickListener() {
//                @Override
//                public void onItemClick(View view) {
//                    int childAdapterPosition = listview.getChildAdapterPosition(view);
//                    brand_id = datas.get(childAdapterPosition).getBrand_id();
//                    String brand_logo = datas.get(childAdapterPosition).getBrand_logo();
//                    String brand_name = datas.get(childAdapterPosition).getBrand_name();
//
//                    Log.e("TAG","brand_id===="+brand_id);
//                    Intent intent = new Intent(mContext, PinPaiActivity.class);
//                    intent.putExtra("BID",brand_id);
//                    intent.putExtra("brand_logo",brand_logo);
//                    intent.putExtra("brand_name",brand_name);
//                    startActivity(intent);
       //     Toast.makeText(mContext, "点击了", Toast.LENGTH_SHORT).show();

            //     }
            //   });

        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
