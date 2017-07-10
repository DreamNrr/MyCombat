package com.example.wzh.mycombat.controller.shopfragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseFragment;
import com.example.wzh.mycombat.controller.activity.LiWuActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.example.wzh.mycombat.utils.BaseUrl.BASE_URL;

/**
 * Created by WZH on 2017/7/6.
 */

public class LiwuFragment extends BaseFragment {

    @InjectView(R.id.imageView)
    ImageView imageView;
    @InjectView(R.id.ib_jieri)
    ImageButton ibJieri;
    @InjectView(R.id.ib_love)
    ImageButton ibLove;
    @InjectView(R.id.ib_brithday)
    ImageButton ibBrithday;
    @InjectView(R.id.ib_friend)
    ImageButton ibFriend;
    @InjectView(R.id.ib_kid)
    ImageButton ibKid;
    @InjectView(R.id.ib_parents)
    ImageButton ibParents;
    @InjectView(R.id.ll_tixing)
    RelativeLayout llTixing;

    private int urlId;


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.liwu_fragment, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.imageView, R.id.ib_jieri, R.id.ib_love, R.id.ib_brithday, R.id.ib_friend, R.id.ib_kid, R.id.ib_parents, R.id.ll_tixing})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView:
                urlId = 7;
                break;
            case R.id.ib_jieri:
                urlId = 1;
                break;
            case R.id.ib_love:
                urlId = 2;
                break;
            case R.id.ib_brithday:
                urlId = 3;
                break;
            case R.id.ib_friend:
                urlId = 4;
                break;
            case R.id.ib_kid:
                urlId = 5;
                break;
            case R.id.ib_parents:
                urlId = 6;
                break;
            case R.id.ll_tixing:
                Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                break;
        }
        String Url = BASE_URL + "goods/goodsList?app_key=Android&count=10&list_id=" + urlId + "&page=" + 1 + "&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
        Intent intent = new Intent(mContext, LiWuActivity.class);
        intent.putExtra("Url",Url);
        startActivity(intent);
    }
}
