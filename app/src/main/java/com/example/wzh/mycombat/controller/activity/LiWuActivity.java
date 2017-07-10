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
import com.example.wzh.mycombat.controller.adapter.LiwuAdapter;
import com.example.wzh.mycombat.modle.bean.LwuBean;
import com.example.wzh.mycombat.utils.OnItemClickListener;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

public class LiWuActivity extends BaseActivity{

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.ib_search)
    ImageButton ibSearch;
    @InjectView(R.id.ib_share)
    ImageButton ibShare;
    @InjectView(R.id.ib_setting)
    ImageButton ibSetting;
    @InjectView(R.id.jiage_tv)
    TextView jiageTv;
    @InjectView(R.id.shaixuan_btn)
    ImageView shaixuanBtn;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    @InjectView(R.id.activity_fenlei)
    LinearLayout activityFenlei;
    @InjectView(R.id.ib_back)
    ImageButton ibBack;
    @InjectView(R.id.ib_shopping)
    ImageButton ibShopping;
    private String url;
    private List<LwuBean.DataBean.ItemsBean> datas;
    private LiwuAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fenlei_xiangqing;
    }

    @Override
    public void initView() {
        super.initView();
        tvTitle.setText("商店");
        ibSearch.setVisibility(View.GONE);
        ibBack.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {
        url = getIntent().getStringExtra("Url");
        getFromNet();
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
        LwuBean bean = new Gson().fromJson(response, LwuBean.class);
        datas = bean.getData().getItems();
        if (datas != null && datas.size() > 0) {
            //   progressbar.setVisibility(View.GONE);

            adapter = new LiwuAdapter(this, datas);
            recyclerview.setAdapter(adapter);
//            recyclerview.setLayoutManager(
//                    new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerview.setLayoutManager(new GridLayoutManager(this, 2,
                    GridLayoutManager.VERTICAL, false));

            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view) {
                    int childAdapterPosition = recyclerview.getChildAdapterPosition(view);
//                    String h5url = datas.get(childAdapterPosition).getTopic_url();
//                    String topic_name = datas.get(childAdapterPosition).getTopic_name();
//                    Intent intent = new Intent(mContext, HTMLActivity.class);
//                    intent.putExtra("HUrl",h5url);
//                    intent.putExtra("topic_name",topic_name);
//                    startActivity(intent);

                    Toast.makeText(LiWuActivity.this, "点击了" + childAdapterPosition, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    @Override
    public void initListener() {

    }


    @OnClick({R.id.ib_back, R.id.ib_shopping})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                LiWuActivity.this.finish();
                break;
            case R.id.ib_shopping:
                break;
        }
    }
}
