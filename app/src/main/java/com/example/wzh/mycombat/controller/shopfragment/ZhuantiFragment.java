package com.example.wzh.mycombat.controller.shopfragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseFragment;
import com.example.wzh.mycombat.controller.activity.ZuanTiActivity;
import com.example.wzh.mycombat.controller.adapter.ZhuanTiAdapter;
import com.example.wzh.mycombat.modle.bean.ZTBean;
import com.example.wzh.mycombat.utils.OnItemClickListener;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

import static com.example.wzh.mycombat.utils.BaseUrl.TOPIC_URL;

/**
 * Created by WZH on 2017/7/6.
 */

public class ZhuantiFragment extends BaseFragment {
//    public final static String TOPIC_URL = BASE_URL +
//            "goods/shopSpecial?app_key=Android&count=10&sig=3780CB0808528F7CE99081D295EE8C0F%7C116941220826768&uid=626138098&user_token=0516ed9429352c8e1e3bd11c63ba6f54&v=1.0&page=1";
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;


    private List<ZTBean.DataBean.ItemsBean> datas;
    private ZhuanTiAdapter adapter;
    private WebView webview;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.zhunti_fragment, null);
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
                .url(TOPIC_URL)
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
        ZTBean bean = new Gson().fromJson(response, ZTBean.class);
        datas = bean.getData().getItems();
        if (datas != null && datas.size() > 0) {
            //   progressbar.setVisibility(View.GONE);
            adapter = new ZhuanTiAdapter(mContext, datas);
            recyclerview.setAdapter(adapter);
            recyclerview.setLayoutManager(
                    new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
//            recyclerview.setLayoutManager(new GridLayoutManager(mContext, 2,
//                    GridLayoutManager.VERTICAL, false));


            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view) {
                    int childAdapterPosition = recyclerview.getChildAdapterPosition(view);
                    String h5url = datas.get(childAdapterPosition).getTopic_url();
                    String topic_name = datas.get(childAdapterPosition).getTopic_name();
                    Intent intent = new Intent(mContext, ZuanTiActivity.class);
                    intent.putExtra("HUrl",h5url);
                    intent.putExtra("topic_name",topic_name);
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
