package com.example.wzh.mycombat.controller.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseActivity;
import com.example.wzh.mycombat.modle.bean.GoogsBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.example.wzh.mycombat.R.id.ll_goods_details_iv;
import static com.example.wzh.mycombat.R.id.rg_goods_details;
import static com.example.wzh.mycombat.R.id.tv_buy_know;
import static com.example.wzh.mycombat.utils.BaseUrl.BRAND_GOODS_DETAILS_URL;

public class GoodsInfoActivity extends BaseActivity {

    @InjectView(R.id.viewPager)
    ViewPager viewPager;
    @InjectView(R.id.ll_point)
    LinearLayout llPoint;
    @InjectView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @InjectView(R.id.tv_like_count)
    TextView tvLikeCount;
    @InjectView(R.id.tv_desc)
    TextView tvDesc;
    @InjectView(R.id.iv_share)
    ImageView ivShare;
    @InjectView(R.id.discount_price)
    TextView discountPrice;
    @InjectView(R.id.ll_select_size)
    LinearLayout llSelectSize;
    @InjectView(R.id.iv_logo)
    ImageView ivLogo;
    @InjectView(R.id.tv_brand_name)
    TextView tvBrandName;
    @InjectView(R.id.ll_logo)
    LinearLayout llLogo;
    @InjectView(R.id.rb_goods_details)
    RadioButton rbGoodsDetails;
    @InjectView(R.id.rb_buy_note)
    RadioButton rbBuyNote;
    @InjectView(rg_goods_details)
    RadioGroup rgGoodsDetails;
    @InjectView(tv_buy_know)
    TextView tvBuyKnow;
    @InjectView(ll_goods_details_iv)
    LinearLayout llGoodsDetailsIv;
    @InjectView(R.id.ib_back)
    ImageButton ibBack;
    @InjectView(R.id.ib_shopping)
    ImageButton ibShopping;
    @InjectView(R.id.rb_service)
    RadioButton rbService;
    @InjectView(R.id.rb_add_cart)
    RadioButton rbAddCart;
    @InjectView(R.id.rb_buy)
    RadioButton rbBuy;
    @InjectView(R.id.rg_buy_cart)
    RadioGroup rgBuyCart;
    @InjectView(R.id.activity_goods_detail)
    RelativeLayout activityGoodsDetail;
    private String goods_id;
    private GoogsBean bean;
    private List<String> images_item;

//    private List<GoogsBean.DataBean.ItemsBean.BrandInfoBean> datas;


    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_info;
    }


    @Override
    public void initData() {

        goods_id = getIntent().getStringExtra("goods_id");
        getFromNet();
    }

    private void getFromNet() {
        OkHttpUtils
                .get()
                .url(BRAND_GOODS_DETAILS_URL + goods_id)
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
        bean = new Gson().fromJson(response, GoogsBean.class);
        //datas = bean.getData().getItems().getBrand_info().;
        tvGoodsName.setText(bean.getData().getItems().getOwner_name());
        tvDesc.setText(bean.getData().getItems().getGoods_name());
        discountPrice.setText(bean.getData().getItems().getPrice());
        tvLikeCount.setText(bean.getData().getItems().getLike_count());
        //logo图片
        Glide.with(this).asBitmap().load(bean.getData().getItems().getBrand_info().getBrand_logo()).into(ivLogo);
        //logo名字
        tvBrandName.setText(bean.getData().getItems().getBrand_info().getBrand_name());
        images_item = bean.getData().getItems().getImages_item();
        ;
//        Log.e("TAA",images_item + "+++++++images_item");

        List<GoogsBean.DataBean.ItemsBean.GoodsInfoBean> goods_info = bean.getData().getItems().getGoods_info();

        Log.e("TAA", "good_info===========" + goods_info);

        if (goods_info != null && goods_info.size() > 0) {
            for (int i = 0; i < goods_info.size(); i++) {
                ImageView imageView = new ImageView(this);
                Glide.with(this).asBitmap().load(goods_info.get(i).getContent().getImg()).into(imageView);
                llGoodsDetailsIv.addView(imageView);
            }
        }

        tvBuyKnow.setText(bean.getData().getItems().getGood_guide().getContent());

        VPAdapter adapter = new VPAdapter();
        viewPager.setAdapter(adapter);
    }

    @Override
    public void initListener() {
        rgGoodsDetails.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_goods_details:
                        llGoodsDetailsIv.setVisibility(View.VISIBLE);
                        tvBuyKnow.setVisibility(View.GONE);
                        break;
                    case R.id.rb_buy_note:
                        llGoodsDetailsIv.setVisibility(View.GONE);
                        tvBuyKnow.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        rgGoodsDetails.check(R.id.rb_goods_details);

    }


    @OnClick({R.id.ib_back, R.id.ib_shopping})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                GoodsInfoActivity.this.finish();
                break;
            case R.id.ib_shopping:
                break;
        }
    }


    public class VPAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images_item.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(GoodsInfoActivity.this);
            Glide.with(GoodsInfoActivity.this).asBitmap().load(images_item.get(position)).into(imageView);
            Log.e("TAA", "图片====" + images_item.get(position));
            container.addView(imageView);
            return imageView;
        }
    }


}
