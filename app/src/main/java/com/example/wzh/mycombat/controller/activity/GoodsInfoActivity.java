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
import okhttp3.Call;

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
    @InjectView(R.id.rg_goods_details)
    RadioGroup rgGoodsDetails;
    @InjectView(R.id.tv_buy_know)
    TextView tvBuyKnow;
    @InjectView(R.id.ll_goods_details_iv)
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
        Glide.with(this).asBitmap().load( bean.getData().getItems().getBrand_info().getBrand_logo()).into(ivLogo);
        //logo名字
        tvBrandName.setText(bean.getData().getItems().getBrand_info().getBrand_name());
        images_item  = bean.getData().getItems().getImages_item();;
//        Log.e("TAA",images_item + "+++++++images_item");

        VPAdapter adapter = new VPAdapter();
        viewPager.setAdapter(adapter);
    }

    @Override
    public void initListener() {

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
