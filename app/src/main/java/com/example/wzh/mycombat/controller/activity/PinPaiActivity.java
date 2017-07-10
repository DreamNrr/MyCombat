package com.example.wzh.mycombat.controller.activity;

import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseActivity;
import com.example.wzh.mycombat.controller.adapter.PinPaiXQAdapter;
import com.example.wzh.mycombat.modle.bean.PPXQBean;
import com.example.wzh.mycombat.utils.ImageUtils;
import com.example.wzh.mycombat.utils.OnItemClickListener;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.example.wzh.mycombat.R.id.ib_back;
import static com.example.wzh.mycombat.R.id.product_btn;
import static com.example.wzh.mycombat.R.id.story_btn;
import static com.example.wzh.mycombat.utils.BaseUrl.BRAND_DETAILS_URL;

public class PinPaiActivity extends BaseActivity {
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.ib_search)
    ImageButton ibSearch;
    @InjectView(ib_back)
    ImageButton ibBack;
    @InjectView(R.id.ib_shopping)
    ImageButton ibShopping;
    @InjectView(R.id.logo_iv)
    ImageView logoIv;
    @InjectView(R.id.logo_tv)
    TextView logoTv;
    @InjectView(story_btn)
    RadioButton storyBtn;
    @InjectView(R.id.product_btn)
    RadioButton productBtn;
    @InjectView(R.id.story_tv)
    TextView storyTv;
    @InjectView(R.id.product_recyview)
    RecyclerView productRecyview;
    @InjectView(R.id.activity_pin_pai)
    LinearLayout activityPinPai;
    @InjectView(R.id.rg)
    RadioGroup rg;
    private int bid;
    private List<PPXQBean.DataBean.ItemsBean> datas;
    private PinPaiXQAdapter adapter;
    private String brand_logo;
    private String brand_name;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pin_pai;
    }

    @Override
    public void initView() {
        super.initView();
        tvTitle.setText("品牌产品");
        ibBack.setVisibility(View.VISIBLE);
        ibSearch.setVisibility(View.GONE);
        ibShopping.setVisibility(View.GONE);
        rg.check(product_btn);
    }

    @Override
    public void initData() {
        bid = getIntent().getIntExtra("BID", -1);
        brand_logo = getIntent().getStringExtra("brand_logo");
        brand_name = getIntent().getStringExtra("brand_name");
        Glide.with(this).asBitmap().load(brand_logo).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                logoIv.setImageBitmap(ImageUtils.toRound(resource));
            }
        });
        logoTv.setText(brand_name);
        Log.e("TTT", "bid ===== " + bid);
        getFromNet(bid);

    }

    private void getFromNet(final int bdid) {
        OkHttpUtils
                .get()
                .url(BRAND_DETAILS_URL + "&brand_id=" + bdid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        processData(response);
                        Log.e("TAA", BRAND_DETAILS_URL + "&brand_id=" + bdid);
                    }
                });
    }

    private void processData(String response) {

        PPXQBean bean = new Gson().fromJson(response, PPXQBean.class);
        datas = bean.getData().getItems();
        if (datas != null && datas.size() > 0) {
            //   progressbar.setVisibility(View.GONE);

            storyTv.setText(datas.get(0).getBrand_info().getBrand_desc());

            adapter = new PinPaiXQAdapter(this, datas);
            productRecyview.setAdapter(adapter);
//            recyclerview.setLayoutManager(
//                    new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            productRecyview.setLayoutManager(new GridLayoutManager(this, 2,
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
                    Toast.makeText(PinPaiActivity.this, "点击了", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

    @Override
    public void initListener() {

    }


    @OnClick({R.id.story_btn, R.id.product_btn,R.id.ib_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case story_btn:
                storyTv.setVisibility(View.VISIBLE);
                productRecyview.setVisibility(View.GONE);
                break;
            case R.id.product_btn:
                storyTv.setVisibility(View.GONE);
                productRecyview.setVisibility(View.VISIBLE);
                break;
            case R.id.ib_back:
                PinPaiActivity.this.finish();
                break;
        }
    }


}

