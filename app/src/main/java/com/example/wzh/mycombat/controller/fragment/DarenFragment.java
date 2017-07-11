package com.example.wzh.mycombat.controller.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseFragment;
import com.example.wzh.mycombat.controller.activity.DaRenActivity;
import com.example.wzh.mycombat.modle.bean.DrBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

import static com.example.wzh.mycombat.utils.BaseUrl.EXPERT_RECOMMEND_URL;

/**
 * Created by WZH on 2017/7/5.
 */

public class DarenFragment extends BaseFragment {
    //    public final static String EXPERT_RECOMMEND_URL = BASE_URL +
//            "user/masterList?app_key=Android&count=18&sig=79F01B94B8EBEFAC8EEB344EE2B20AA2%7C383889010803768&v=1.0&page=1";
    @InjectView(R.id.gridview)
    GridView gridview;
    private SimpleAdapter adapter;

    private List<DrBean.DataBean.ItemsBean> datas;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.daren_fragment, null);
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
                .url(EXPERT_RECOMMEND_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAA", "EXPERT_RECOMMEND_URL====" + EXPERT_RECOMMEND_URL);
                        processData(response);
                    }
                });
    }

    private void processData(String response) {
        DrBean bean = new Gson().fromJson(response, DrBean.class);
        datas = bean.getData().getItems();
        if (datas != null && datas.size() > 0) {
            final ArrayList<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();
            for (int i = 0; i < datas.size(); i++) {
                final HashMap<String, Object> map = new HashMap<String, Object>();
                final int finalI = i;
//                Glide.with(mContext)
//                        .asBitmap()
//                        .load(datas.get(i).getUser_images().getOrig())
//                        .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
//                            @Override
//                            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                map.put("ItemImage", datas.get(i).getUser_images().getOrig());//添加图像资源
                map.put("ItemTextName", datas.get(finalI).getUsername());
                Log.e("TAA", "name=======" + datas.get(finalI).getUsername());
                map.put("ItemTextDuty", datas.get(finalI).getDuty());
                Log.e("TAA", "duty=======" + datas.get(finalI).getDuty());
                items.add(map);
//                                adapter = new SimpleAdapter(mContext,
//                                        items,
//                                        R.layout.daren_item,
//                                        new String[]{"ItemImage", "ItemTextName","ItemTextDuty"},
//                                        new int[]{R.id.ItemImage, R.id.ItemTextName,R.id.ItemTextDuty});
//
//                                adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
//                                    @Override
//                                    public boolean setViewValue(View view, Object data, String arg2) {
//                                        if(view instanceof ImageView && data instanceof Bitmap){
//                                            Log.e("TAG","view=="+view);
//                                            ImageView iv = (ImageView)view;
//                                            iv.setImageBitmap((Bitmap)data);
//                                            return true;
//                                        }else{
//                                            return false;
//                                        }
//                                    }
//                                });
//
//                                //为GridView设置适配器
//                                gridview.setAdapter(adapter);
            }
//                        });
            adapter = new SimpleAdapter(mContext,
                    items,
                    R.layout.daren_item,
                    new String[]{"ItemImage", "ItemTextName", "ItemTextDuty"},
                    new int[]{R.id.ItemImage, R.id.ItemTextName, R.id.ItemTextDuty});

            adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
                @Override
                public boolean setViewValue(View view, final Object data, String arg2) {
                    if (view instanceof ImageView && data instanceof String) {
                        Log.e("TAG", "view==" + view);
                        final ImageView iv = (ImageView) view;
                        Glide.with(mContext)
                                .asBitmap()
                                .load(data)
                                .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                                    @Override
                                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                        iv.setImageBitmap(resource);
                                    }
                                });

                        return true;
                    } else {
                        return false;
                    }
                }
            });
            //为GridView设置适配器
            gridview.setAdapter(adapter);
        }


        //添加消息处理
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.e("TAA", "view==" + view + "position==" + position + "id====" + id);
//view==android.widget.LinearLayout{16b94886 V.E..... ........ 460,298-680,576}position==5id====5
                Intent intent = new Intent(mContext, DaRenActivity.class);
                String username = datas.get(position).getUsername();
                String ImUrl = datas.get(position).getUser_images().getOrig();
                String duty = datas.get(position).getDuty();
                String uid = datas.get(position).getUid();
                intent.putExtra("username", username);
                intent.putExtra("ImUrl", ImUrl);
                intent.putExtra("duty", duty);
                intent.putExtra("uid", uid);
                startActivity(intent);
            }
        });

}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
