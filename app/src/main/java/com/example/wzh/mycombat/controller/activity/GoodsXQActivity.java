package com.example.wzh.mycombat.controller.activity;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
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
import com.example.wzh.mycombat.utils.DensityUtil;
import com.example.wzh.mycombat.view.FlowRadioGroup;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.InjectView;
import okhttp3.Call;

import static com.example.wzh.mycombat.R.id.center;
import static com.example.wzh.mycombat.R.id.sku_ll;

public class GoodsXQActivity extends BaseActivity {

    @InjectView(R.id.brand_name_tv)
    TextView brandNameTv;
    @InjectView(R.id.good_name_tv)
    TextView goodNameTv;
    @InjectView(R.id.price_tv)
    TextView priceTv;
    @InjectView(R.id.divider)
    View divider;
    @InjectView(sku_ll)
    LinearLayout skuLl;
    @InjectView(R.id.amount_title_tv)
    TextView amountTitleTv;
    @InjectView(R.id.minus_iv)
    ImageView minusIv;
    @InjectView(R.id.amount_tv)
    TextView amountTv;
    @InjectView(R.id.plus_iv)
    ImageView plusIv;
    @InjectView(R.id.dark_rl)
    RelativeLayout darkRl;
    @InjectView(R.id.sku_iv)
    ImageView skuIv;
    @InjectView(R.id.close)
    ImageView close;
    @InjectView(R.id.confirm_tv)
    TextView confirmTv;
    @InjectView(R.id.bottom_action_bar)
    LinearLayout bottomActionBar;
    @InjectView(R.id.cart_pupmenu)
    RelativeLayout cartPupmenu;

    private List<GoogsBean.DataBean.ItemsBean.SkuInfoBean> datas;
    private String url;
    private GoogsBean bean;
    private List<GoogsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> listdatas;

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_xq;
    }

    @Override
    public void initData() {
        String image = getIntent().getStringExtra("image");
        String brand_name = getIntent().getStringExtra("brand_name");
        String goods_name = getIntent().getStringExtra("goods_name");
        String price = getIntent().getStringExtra("price");
        url = getIntent().getStringExtra("url");

        Glide.with(this).load(image).asBitmap().into(skuIv);
        brandNameTv.setText(brand_name);
        goodNameTv.setText(goods_name);
        priceTv.setText(price);

//        intent.putExtra("type_id",bean.getData().getItems().getSku_info().get(0).getType_id());
//        //颜色：---type_id==1
//        intent.putExtra("color",
//                bean.getData().getItems().getSku_info().get(0).getAttrList().get(0).getAttr_name());
//        //尺寸：---type_id==8
//        intent.putExtra("size",
//                bean.getData().getItems().getSku_info().get(0).getAttrList().get(0).getAttr_name());
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
        bean = new Gson().fromJson(response, GoogsBean.class);
        datas = bean.getData().getItems().getSku_info();

        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                String type_name = datas.get(i).getType_name();
                TextView textView = new TextView(this);
                textView.setText(type_name);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(DensityUtil.dip2px(this, 6));

//                RadioGroup radioGroup = new RadioGroup(this);
//                radioGroup.setOrientation(LinearLayout.HORIZONTAL);

                FlowRadioGroup flowRadioGroup = new FlowRadioGroup(this);
                flowRadioGroup.setOrientation(LinearLayout.HORIZONTAL);

                listdatas = datas.get(i).getAttrList();

                for (int j = 0; j < listdatas.size(); j++) {
                    String attr_name = listdatas.get(j).getAttr_name();
                    final RadioButton radioButton = new RadioButton(this);
                    radioButton.setText(attr_name);
                    radioButton.setBackgroundColor(Color.GRAY);
                    radioButton.setTextSize(DensityUtil.dip2px(this, 7));
                    radioButton.setTextColor(Color.WHITE);
                    radioButton.setHeight(DensityUtil.dip2px(this, 40));
                    radioButton.setGravity(center);
                    radioButton.setButtonDrawable(R.color.translucent);//隐藏button
                    radioButton.setId(j);
                    radioButton.setTag(listdatas.get(j).getImg_path());
                    radioButton.setPadding(DensityUtil.dip2px(this, 3), DensityUtil.dip2px(this, 3), DensityUtil.dip2px(this, 3), DensityUtil.dip2px(this, 3));
                    RadioGroup.LayoutParams layoutParams =
                            new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(10, 10, 10, 10);//4个参数按顺序分别是左上右下
                    radioButton.setLayoutParams(layoutParams);
                    flowRadioGroup.addView(radioButton);


                    radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if(b) {
                                String imagePath = (String) radioButton.getTag();
                                if(!TextUtils.isEmpty(imagePath)) {
                                    Glide.with(GoodsXQActivity.this).load(imagePath).asBitmap().into(skuIv);
                                }
                            }
                        }
                    });

                }
                skuLl.addView(textView);
                skuLl.addView(flowRadioGroup);

            }
        }

    }


    @Override
    public void initListener() {
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoodsXQActivity.this.finish();
            }
        });
    }

}
