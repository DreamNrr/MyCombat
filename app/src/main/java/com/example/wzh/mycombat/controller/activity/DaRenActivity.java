package com.example.wzh.mycombat.controller.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseActivity;
import com.example.wzh.mycombat.controller.adapter.DarxqAdapter;
import com.example.wzh.mycombat.modle.bean.DrGFBean;
import com.example.wzh.mycombat.modle.bean.DrXQBean;
import com.example.wzh.mycombat.utils.OnItemClickListener;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.example.wzh.mycombat.R.id.ib_back;
import static com.example.wzh.mycombat.utils.BaseUrl.EXPERT_ATTENTION_URL;
import static com.example.wzh.mycombat.utils.BaseUrl.EXPERT_FANS_URL;
import static com.example.wzh.mycombat.utils.BaseUrl.EXPERT_INFR_URL;
import static com.example.wzh.mycombat.utils.BaseUrl.EXPERT_LIKE_URL;

public class DaRenActivity extends BaseActivity implements OnItemClickListener {


    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.ib_search)
    ImageButton ibSearch;
    @InjectView(ib_back)
    ImageButton ibBack;
    @InjectView(R.id.ib_shopping)
    ImageButton ibShopping;
    @InjectView(R.id.imageview)
    ImageView imageview;
    @InjectView(R.id.name)
    TextView name;
    @InjectView(R.id.zhiye)
    TextView zhiye;
    @InjectView(R.id.btn_guanzhu)
    Button btnGuanzhu;
    @InjectView(R.id.sixin_btn)
    Button sixinBtn;
    @InjectView(R.id.like)
    RadioButton like;
    @InjectView(R.id.tuijian)
    RadioButton tuijian;
    @InjectView(R.id.guanzhu)
    RadioButton guanzhu;
    @InjectView(R.id.fensi)
    RadioButton fensi;
    @InjectView(R.id.radioGroup)
    RadioGroup radioGroup;
    @InjectView(R.id.activity_da_ren)
    LinearLayout activityDaRen;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;


    private List<DrXQBean.DataBean.ItemsBean.GoodsBean> datax;
    private List<DrGFBean.DataBean.ItemsBean.UsersBean> dataf;
    private DarxqAdapter adapter;
    private String uid;
    private int btnid = 0;
    private final String[] urll = {EXPERT_LIKE_URL,EXPERT_INFR_URL,EXPERT_ATTENTION_URL,EXPERT_FANS_URL};

    @Override
    public int getLayoutId() {
        return R.layout.activity_da_ren;
    }

    @Override
    public void initView() {
        super.initView();
        ibBack.setVisibility(View.VISIBLE);
        ibSearch.setVisibility(View.GONE);
        ibShopping.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        String username = getIntent().getStringExtra("username");
        String imurl = getIntent().getStringExtra("ImUrl");
        String duty = getIntent().getStringExtra("duty");
        uid = getIntent().getStringExtra("uid");

        Log.e("TAG","url======" + EXPERT_INFR_URL+uid);

        tvTitle.setText(username);
        Glide.with(this).asBitmap().load(imurl).into(imageview);
        name.setText(username);
        zhiye.setText(duty);
    }

    private void getFromNet(String url) {
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
                        //Log.e("TAA","Url======" + url);
                       processData(response);
                    }
                });
    }

    private void processData(String response) {
        switch (btnid) {
            case 0 :
            case 1 :
                DrXQBean Xbean = new Gson().fromJson(response, DrXQBean.class);
                datax = Xbean.getData().getItems().getGoods();
                if (datax != null && datax.size() > 0) {
                    //   progressbar.setVisibility(View.GONE);
                    adapter = new DarxqAdapter<DrXQBean.DataBean.ItemsBean.GoodsBean>(this, datax);
                    recyclerview.setAdapter(adapter);
//            recyclerview.setLayoutManager(
//                    new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                    recyclerview.setLayoutManager(new GridLayoutManager(this, 2,
                            GridLayoutManager.VERTICAL, false));
                    adapter.setOnItemClickListener(this);
                }
                break;
            case 2:
            case 3:
                DrGFBean Fbean = new Gson().fromJson(response, DrGFBean.class);
                dataf = Fbean.getData().getItems().getUsers();
                if (datax != null && datax.size() > 0) {
                    adapter = new DarxqAdapter<DrGFBean.DataBean.ItemsBean.UsersBean>(this, dataf);
                    recyclerview.setAdapter(adapter);
                    recyclerview.setLayoutManager(new GridLayoutManager(this, 3,
                            GridLayoutManager.VERTICAL, false));
                    adapter.setOnItemClickListener(this);
                }
                break;
        }
    }

    @Override
    public void initListener() {
      radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(RadioGroup radioGroup, int i) {
              switch (i){
                  case R.id.like:
                      btnid = 0;
                      break;
                  case R.id.tuijian:
                      btnid = 1;
                      break;
                  case R.id.guanzhu:
                      btnid = 2;
                      break;
                  case R.id.fensi:
                      btnid = 3;
                      break;
              }

              getFromNet(urll[btnid] + uid);
          }

      });
        radioGroup.check(R.id.tuijian);
    }

    @OnClick({R.id.btn_guanzhu, R.id.sixin_btn,R.id.ib_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_guanzhu:
                Toast.makeText(DaRenActivity.this, "关注", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sixin_btn:
                Toast.makeText(DaRenActivity.this, "私信", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_back:
                DaRenActivity.this.finish();
                break;
        }
    }

    @Override
    public void onItemClick(View view) {
        Toast.makeText(DaRenActivity.this, "点击了", Toast.LENGTH_SHORT).show();
    }
}
