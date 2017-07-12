package com.example.wzh.mycombat.controller.zzfragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseFragment;
import com.example.wzh.mycombat.controller.adapter.ZuoZheAdapter;
import com.example.wzh.mycombat.modle.bean.ZuoZheBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

import static com.example.wzh.mycombat.utils.BaseUrl.MAGAZINE_LIST_URL;

/**
 * Created by WZH on 2017/7/12.
 */
public class ZZZFragment extends BaseFragment {

    @InjectView(R.id.listview)
    ListView listview;
    private List<ZuoZheBean.DataBean.ItemsBean> datas;
    private ZuoZheAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_zuozhe, null);
        ButterKnife.inject(this,view);
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
                .url(MAGAZINE_LIST_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id)
                    {
                        processData(response);
                    }
                });
    }
    private void processData(String response) {
        ZuoZheBean bean = new Gson().fromJson(response, ZuoZheBean.class);
        datas = bean.getData().getItems();
        if (datas != null && datas.size() > 0) {
            //   progressbar.setVisibility(View.GONE);
            adapter = new ZuoZheAdapter(mContext, datas);
            listview.setAdapter(adapter);

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
       //             Toast.makeText(mContext, "点击了", Toast.LENGTH_SHORT).show();

           //     }
         //   });



        }
    }



    @Override
    public void initListener() {
        super.initListener();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
