package com.example.wzh.mycombat.controller.activity;

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
import com.example.wzh.mycombat.modle.bean.DrBean;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

import static com.example.wzh.mycombat.R.id.ib_back;

public class DaRenActivity extends BaseActivity {


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

    private List<DrBean.DataBean.ItemsBean> datas;

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

        tvTitle.setText(username);
        Glide.with(this).asBitmap().load(imurl).into(imageview);
        name.setText(username);
        zhiye.setText(duty);

        getFromNet();
    }
    private void getFromNet() {
//        OkHttpUtils
//                .get()
//                .url(url)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        Log.e("TAG", "联网失败==" + e.getMessage());
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        processData(response);
//                    }
//                });
    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.btn_guanzhu, R.id.sixin_btn, R.id.like, R.id.tuijian, R.id.guanzhu, R.id.fensi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_guanzhu:
                Toast.makeText(DaRenActivity.this, "关注", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sixin_btn:
                Toast.makeText(DaRenActivity.this, "私信", Toast.LENGTH_SHORT).show();
                break;
            case R.id.like:
                break;
            case R.id.tuijian:
                break;
            case R.id.guanzhu:
                break;
            case R.id.fensi:
                break;
        }
    }
}
