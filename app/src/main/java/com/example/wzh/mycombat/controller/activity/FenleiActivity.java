package com.example.wzh.mycombat.controller.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseActivity;
import com.example.wzh.mycombat.controller.adapter.FenleiXqingAdapter;
import com.example.wzh.mycombat.modle.bean.FLXBean;
import com.example.wzh.mycombat.utils.OnItemClickListener;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.InjectView;
import okhttp3.Call;

public class FenleiActivity extends BaseActivity {
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    private List<FLXBean.DataBean.ItemsBean> datas;

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.ib_search)
    ImageButton ibSearch;
    @InjectView(R.id.ib_back)
    ImageButton ibBack;
    @InjectView(R.id.ib_shopping)
    ImageButton ibShopping;
    @InjectView(R.id.ib_share)
    ImageButton ibShare;
    @InjectView(R.id.ib_setting)
    ImageButton ibSetting;
    @InjectView(R.id.jiage_tv)
    TextView jiageTv;
    @InjectView(R.id.shaixuan_btn)
    ImageView shaixuanBtn;
    @InjectView(R.id.activity_fenlei)
    LinearLayout activityFenlei;
    private FenleiXqingAdapter adapter;
    private String url;

    @Override
    public int getLayoutId() {
        return R.layout.fenlei_xiangqing;
    }

    @Override
    public void initView() {
        super.initView();
        ibSearch.setVisibility(View.GONE);
        ibBack.setVisibility(View.VISIBLE);
        tvTitle.setText("商店");
    }

    @Override
    public void initData() {

         url = getIntent().getStringExtra("Url");
        Log.e("TTT", url);
        getFromNet();
    }

    @Override
    public void initListener() {

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
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        processData(response);
                    }
                });
    }

    private void processData(String response) {
        FLXBean bean = new Gson().fromJson(response, FLXBean.class);
        datas = bean.getData().getItems();
        if (datas != null && datas.size() > 0) {
            //   progressbar.setVisibility(View.GONE);
            adapter = new FenleiXqingAdapter(this, datas);
            recyclerview.setAdapter(adapter);
//            recyclerview.setLayoutManager(
//                    new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            recyclerview.setLayoutManager(new GridLayoutManager(this, 2,
                    GridLayoutManager.VERTICAL, false));
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view) {
//                    int childAdapterPosition = recyclerview.getChildAdapterPosition(view);
//                    String s = Furl[childAdapterPosition];
//                    Log.e("TAG", "s====" + s);
//                    Intent intent = new Intent(mContext, FenleiActivity.class);
//                    intent.putExtra("Url", s);
//                    startActivity(intent);
                    Toast.makeText(FenleiActivity.this, "点击了", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

}