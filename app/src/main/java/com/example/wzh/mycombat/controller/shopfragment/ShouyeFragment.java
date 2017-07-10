package com.example.wzh.mycombat.controller.shopfragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseFragment;
import com.example.wzh.mycombat.controller.adapter.MyRecyclerAdapter;
import com.example.wzh.mycombat.modle.bean.SYBean;
import com.example.wzh.mycombat.utils.OnItemClickListener;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.example.wzh.mycombat.utils.BaseUrl.HOME_PAGER_URL;

/**
 * Created by WZH on 2017/7/6.
 */

public class ShouyeFragment extends BaseFragment {
    @InjectView(R.id.recyview)
    RecyclerView recyview;
    @InjectView(R.id.floating_btn_main)
    FloatingActionButton floatingBtnMain;

    private List<SYBean.DataBean.ItemsBean.ListBean> datas;
    private MyRecyclerAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.shouye_fragment, null);
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
                .url(HOME_PAGER_URL)
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
        SYBean bean = new Gson().fromJson(response, SYBean.class);
        datas = bean.getData().getItems().getList();
        if (datas != null && datas.size() > 0) {
            //   progressbar.setVisibility(View.GONE);
            adapter = new MyRecyclerAdapter(mContext, datas);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
            recyview.setLayoutManager(linearLayoutManager);
            //设置适配器
            recyview.setAdapter(adapter);

            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view) {
                    int childAdapterPosition = recyview.getChildAdapterPosition(view);
//                    String h5url = datas.get(childAdapterPosition).getOne().getTopic_url();
//                    String topic_name = datas.get(childAdapterPosition).getTopic_name();
//                    Intent intent = new Intent(mContext, HTMLActivity.class);
//                    intent.putExtra("HUrl",h5url);
//                    intent.putExtra("topic_name",topic_name);
//                    startActivity(intent);

                    Toast.makeText(mContext, "点击了" + childAdapterPosition, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @OnClick(R.id.floating_btn_main)
    public void onViewClicked() {
        recyview.smoothScrollToPosition(0);
    }
}
