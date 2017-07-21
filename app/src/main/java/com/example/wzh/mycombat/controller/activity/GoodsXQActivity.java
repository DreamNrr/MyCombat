package com.example.wzh.mycombat.controller.activity;

import android.database.sqlite.SQLiteDatabase;
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
import com.example.wzh.mycombat.modle.db.DBHelper;
import com.example.wzh.mycombat.utils.DensityUtil;
import com.example.wzh.mycombat.view.FlowRadioGroup;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.example.wzh.mycombat.R.id.amount_tv;
import static com.example.wzh.mycombat.R.id.center;
import static com.example.wzh.mycombat.R.id.confirm_tv;
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
    @InjectView(amount_tv)
    TextView amountTv;
    @InjectView(R.id.plus_iv)
    ImageView plusIv;
    @InjectView(R.id.dark_rl)
    RelativeLayout darkRl;
    @InjectView(R.id.sku_iv)
    ImageView skuIv;
    @InjectView(R.id.close)
    ImageView close;
    @InjectView(confirm_tv)
    TextView confirmTv;
    @InjectView(R.id.bottom_action_bar)
    LinearLayout bottomActionBar;
    @InjectView(R.id.cart_pupmenu)
    RelativeLayout cartPupmenu;

    private List<GoogsBean.DataBean.ItemsBean.SkuInfoBean> datas;
    public List<GoogsBean.DataBean.ItemsBean.SkuInvBean> sku_inv;
    public GoogsBean.DataBean.ItemsBean.SkuInvBean skuInvBean;
    private String url;
    private GoogsBean bean;

    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private HashMap<String, String> sku_goods = new HashMap<>();
    private HashMap<String, String> attr_keys = new HashMap<>();
    public GoogsBean.DataBean.ItemsBean items;
    private String goods_name;
    private String price;
    private String imagePath;
    private FlowRadioGroup flowRadioGroup;
    public String attr_id = "";
    private int counts;

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_xq;
    }

    @Override
    public void initData() {

        //提交数据到数据库
        dbHelper = new DBHelper(GoodsXQActivity.this);
        //连接数据库
        database = dbHelper.getReadableDatabase();

        String image = getIntent().getStringExtra("image");
        String brand_name = getIntent().getStringExtra("brand_name");
        goods_name = getIntent().getStringExtra("goods_name");
        price = getIntent().getStringExtra("price");
        url = getIntent().getStringExtra("url");

        Glide.with(this).load(image).asBitmap().into(skuIv);
        brandNameTv.setText(brand_name);
        goodNameTv.setText(goods_name);
        priceTv.setText(price);

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
        items = bean.getData().getItems();
        sku_inv = items.getSku_inv();
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                String type_name = datas.get(i).getType_name();
                final TextView textView = new TextView(this);
                textView.setText(type_name);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(DensityUtil.dip2px(this, 6));
                sku_goods.put(textView.getText().toString(), "");
                attr_keys.put(String.valueOf(i), "");
//                RadioGroup radioGroup = new RadioGroup(this);
//                radioGroup.setOrientation(LinearLayout.HORIZONTAL);
                skuLl.addView(textView);

                flowRadioGroup = new FlowRadioGroup(this);


                for (int j = 0; j < datas.get(i).getAttrList().size(); j++) {
                    flowRadioGroup.setOrientation(FlowRadioGroup.HORIZONTAL);
                    final List<GoogsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> listdatas = datas.get(i).getAttrList();
                    final int size = listdatas.size();
                    String attr_name = listdatas.get(j).getAttr_name();
                    final RadioButton radioButton = new RadioButton(this);
                    radioButton.setText(attr_name);
                    Log.e("QQQ", "attr_name===" + attr_name);
                    radioButton.setBackgroundResource(R.drawable.goods_gobuy);
                    radioButton.setTextSize(DensityUtil.dip2px(this, 7));
                    radioButton.setTextColor(Color.WHITE);
                    radioButton.setHeight(DensityUtil.dip2px(this, 40));
                    radioButton.setGravity(center);
                    radioButton.setButtonDrawable(R.color.translucent);//隐藏button

                    radioButton.setId(j);

                    //radioButton.setTag(listdatas.get(j).getImg_path());

                    radioButton.setPadding(DensityUtil.dip2px(this, 3), DensityUtil.dip2px(this, 3), DensityUtil.dip2px(this, 3), DensityUtil.dip2px(this, 3));
                    RadioGroup.LayoutParams layoutParams =
                            new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(10, 10, 10, 10);//4个参数按顺序分别是左上右下
                    radioButton.setLayoutParams(layoutParams);
                    //flowRadioGroup.addView(radioButton);


                    final int finalI = i;
                    //skuLl.addView(flowRadioGroup);
                    final int finalJ = j;

//                    radioButton.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            sku_goods.put(textView.getText().toString(), radioButton.getText().toString());
//                            Log.e("QQQ", "sku_goods===" + sku_goods);
//                            Log.e("QQQ", "attr_keys===" + sku_goods);
//                        }
//                    });
                    radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if (b) {

                                sku_goods.put(textView.getText().toString(), compoundButton.getText().toString());
                                Log.e("QQQ", "sku_goods===" + sku_goods);
                                Log.e("QQQ", "compoundButton===" + compoundButton.getText().toString());
                                Log.e("QQQ", "attr_keys===" + sku_goods);
                                //设置种类组合，根据组合的attr_id获取价格
//                                listdatas.get(j).getAttr_id();
                                Log.e("TAG", "radiobttonid" + compoundButton.getId() + "");
                                Log.e("TAG", size + "");
                                attr_keys.put(String.valueOf(finalI), listdatas.get(compoundButton.getId()).getAttr_id());
                                for (int i2 = 0; i2 < attr_keys.size(); i2++) {
                                    String s = attr_keys.get(String.valueOf(i2));
                                    if (i2 == 0) {
                                        attr_id = s;
                                    } else {
                                        attr_id = attr_id + "," + s;
                                    }
                                }

                                Log.e("QQQ", ":" + attr_id.toString() + "");

                                //设置价格
                                for (int i2 = 0; i2 < sku_inv.size(); i2++) {
                                    // LogUtils.e(sku_inv.get(i2).getAttr_keys());
                                    skuInvBean = sku_inv.get(i2);
                                    if (skuInvBean.getAttr_keys().equals(attr_id)) {
                                        if (!TextUtils.isEmpty(skuInvBean.getDiscount_price())) {
                                            priceTv.setText(skuInvBean.getDiscount_price());
                                        } else {
                                            priceTv.setText(skuInvBean.getPrice());
                                        }
                                        return;
                                    }
                                }
                            } else {
                                // radioButton.setBackground(getResources().getDrawable(R.drawable.attr_bg_check));

                            }
                            if (!TextUtils.isEmpty(listdatas.get(finalJ).getImg_path())) {
                                Glide.with(GoodsXQActivity.this).load(listdatas.get(finalJ).getImg_path()).asBitmap().into(skuIv);
                                imagePath = listdatas.get(finalJ).getImg_path();
                            }
                        }
                    });
                    flowRadioGroup.addView(radioButton, layoutParams);

                }
                skuLl.addView(flowRadioGroup);

            }

        }

    }

    @Override
    public void initListener() {

        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                disprice = 0.00;
//                if (items.getDiscount_price() != null && !TextUtils.isEmpty(items.getDiscount_price())) {
//                    disprice = Double.parseDouble(items.getDiscount_price().toString());
//                    Log.e("TAA","disprice===" + disprice);
//                }
                counts = Integer.parseInt(amountTv.getText().toString());
                String content = "";
                //iterator遍历
                Iterator<Map.Entry<String, String>> iterator = sku_goods.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    content = content + entry.getKey() + ":" + entry.getValue() + ";";
                    Log.e("TAA","content===" + content);
                }
                Log.e("WWW","全部要传的数据===图片：" +imagePath
                        + " 名字：" +goods_name  + " 价格：" + priceTv.getText().toString() + " 内容：" + content + " 数量："  + counts);
/**
 *
 public static final String TABLE_NAME = "goods";
 public static final String GOODS_IMURL = "imageUrl";
 public static final String GOODS_NAME = "goodsName";
 public static final String GOODS_PRICE = "price";
 public static final String GOODS_CONTENT = "content";
 public static final String GOODS_COUNT = "count";
 */
                database.execSQL(
                        "insert into goods(imageUrl,goodsName,price,content,count) " +
                                "values('"+imagePath+"','"
                                +goods_name+"','"
                                +priceTv.getText().toString()+"','"
                                +content+"','"
                                +counts+"')");
                database.close();
                showToast("加购成功");

            }
        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoodsXQActivity.this.finish();
            }
        });
    }

    @OnClick({R.id.minus_iv, R.id.plus_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.minus_iv:
                //减小数量
                String count = amountTv.getText().toString();
                int anInt = Integer.parseInt(count);
                if (anInt == 1) {
                    minusIv.setEnabled(false);
                } else {
                    anInt--;
                    amountTv.setText(anInt + "");
                }
            case R.id.plus_iv:
                //增加数量
                int addcount = Integer.parseInt(amountTv.getText().toString()) + 1;
                amountTv.setText(addcount + "");
                if (addcount > 1) {
                    minusIv.setEnabled(true);
                }
        }
    }
}
