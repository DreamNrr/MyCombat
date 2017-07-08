package com.example.wzh.mycombat.controller.shopfragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseFragment;
import com.example.wzh.mycombat.controller.activity.FenleiActivity;
import com.example.wzh.mycombat.controller.adapter.FenleiAdapter;
import com.example.wzh.mycombat.modle.bean.FlBean;
import com.example.wzh.mycombat.utils.OnItemClickListener;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

import static com.example.wzh.mycombat.utils.BaseUrl.ACCESSORY_URL;
import static com.example.wzh.mycombat.utils.BaseUrl.ART_URL;
import static com.example.wzh.mycombat.utils.BaseUrl.BEAUTYCARE_URL;
import static com.example.wzh.mycombat.utils.BaseUrl.CATEGORY_URL;
import static com.example.wzh.mycombat.utils.BaseUrl.CATE_URL;
import static com.example.wzh.mycombat.utils.BaseUrl.CHILDREN_URL;
import static com.example.wzh.mycombat.utils.BaseUrl.DIGITAL_URL;
import static com.example.wzh.mycombat.utils.BaseUrl.FURNITURE_URL;
import static com.example.wzh.mycombat.utils.BaseUrl.GIFT_URL;
import static com.example.wzh.mycombat.utils.BaseUrl.HOME_HOUSE_URL;
import static com.example.wzh.mycombat.utils.BaseUrl.KITCHEN_URL;
import static com.example.wzh.mycombat.utils.BaseUrl.LIBERTINISM_URL;
import static com.example.wzh.mycombat.utils.BaseUrl.MEN_URL;
import static com.example.wzh.mycombat.utils.BaseUrl.OUTDOORS_URL;
import static com.example.wzh.mycombat.utils.BaseUrl.PLANT_URL;
import static com.example.wzh.mycombat.utils.BaseUrl.RECOMMEND_URL;
import static com.example.wzh.mycombat.utils.BaseUrl.SHOES_URL;
import static com.example.wzh.mycombat.utils.BaseUrl.STATIONERY_URL;
import static com.example.wzh.mycombat.utils.BaseUrl.WOMEN_URL;

/**
 * Created by WZH on 2017/7/6.
 */

public class FenleiFragment extends BaseFragment {
//    public final static String CATEGORY_URL =
//            BASE_URL + "goods/goodsCategory?app_key=Android&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";
    private final String[] Furl = {HOME_HOUSE_URL ,
        FURNITURE_URL , STATIONERY_URL ,
        DIGITAL_URL ,LIBERTINISM_URL ,
        KITCHEN_URL ,CATE_URL ,
        MEN_URL ,WOMEN_URL,CHILDREN_URL ,
        SHOES_URL ,ACCESSORY_URL ,BEAUTYCARE_URL ,
        OUTDOORS_URL ,PLANT_URL ,PLANT_URL ,
        GIFT_URL ,RECOMMEND_URL ,ART_URL };

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
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view) {
                    int childAdapterPosition = recyclerview.getChildAdapterPosition(view);
                    String s = Furl[childAdapterPosition];
                    Log.e("TAG","s===="+s);
                    Intent intent = new Intent(mContext, FenleiActivity.class);
                    intent.putExtra("Url",s);
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
