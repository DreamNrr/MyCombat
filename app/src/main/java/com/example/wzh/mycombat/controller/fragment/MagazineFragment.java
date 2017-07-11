package com.example.wzh.mycombat.controller.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseFragment;
import com.example.wzh.mycombat.controller.adapter.MagnizeAdapter;
import com.example.wzh.mycombat.modle.bean.MagnizeBean;
import com.example.wzh.mycombat.utils.OnItemClickListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

import static com.example.wzh.mycombat.utils.BaseUrl.LIANGCANG_URL;

/**
 * Created by WZH on 2017/7/5.
 */

public class MagazineFragment extends BaseFragment {
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    private List<MagnizeBean.Data.Items.ProductBean> datas;
    private MagnizeAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.magazine_fragment, null);
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
                .url(LIANGCANG_URL)
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
        MagnizeBean bean = JSON.parseObject(response,MagnizeBean.class);
        datas = bean.getData().getItems().getDatas();
        if (datas != null && datas.size() > 0) {
            //   progressbar.setVisibility(View.GONE);
            adapter = new MagnizeAdapter(mContext, datas);
            recyclerview.setAdapter(adapter);
            recyclerview.setLayoutManager(
                    new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
//            recyclerview.setLayoutManager(new GridLayoutManager(mContext, 2,
//                    GridLayoutManager.VERTICAL, false));
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
