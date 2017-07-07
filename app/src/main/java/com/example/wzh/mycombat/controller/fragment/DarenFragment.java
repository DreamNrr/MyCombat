package com.example.wzh.mycombat.controller.fragment;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseFragment;
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

public class DarenFragment extends BaseFragment
{
//    public final static String EXPERT_RECOMMEND_URL = BASE_URL +
//            "user/masterList?app_key=Android&count=18&sig=79F01B94B8EBEFAC8EEB344EE2B20AA2%7C383889010803768&v=1.0&page=1";
    @InjectView(R.id.gridview)
    GridView gridview;
    private SimpleAdapter adapter;

    private List<DrBean.DataBean.ItemsBean> datas;
    private ProgressBar progressbar;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.daren_fragment, null);
        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
       // showGridView();
        getFromNet();
    }

    private void getFromNet() {
        OkHttpUtils
                .get()
                .url(EXPERT_RECOMMEND_URL)
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG","联网失败=="+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        processData(response);
                    }
                });
    }

    private void processData(String response) {
        DrBean bean = new Gson().fromJson(response, DrBean.class);
        datas = bean.getData().getItems();
        if (datas != null && datas.size() > 0) {
            //progressbar.setVisibility(View.GONE);
                final ArrayList<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();
            for (int i = 0; i < datas.size(); i++) {
                final HashMap<String, Object> map = new HashMap<String, Object>();
                 Bitmap bitmap ;
                final int finalI = i;
                Glide.with(mContext)
                        .asBitmap()
                        .load(datas.get(i).getUser_images().getOrig())
                        .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                            @Override
                            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                map.put("ItemImage", resource);//添加图像资源
                                map.put("ItemTextName", datas.get(finalI).getNickname());
                                map.put("ItemTextDuty", datas.get(finalI).getDuty());
                                items.add(map);
                                adapter = new SimpleAdapter(mContext,
                                        items,
                                        R.layout.daren_item,
                                        new String[]{"ItemImage", "ItemTextName","ItemTextDuty"},
                                        new int[]{R.id.ItemImage, R.id.ItemTextName,R.id.ItemTextDuty});
                                adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
                                    @Override
                                    public boolean setViewValue(View view, Object data, String arg2) {
                                        if(view instanceof ImageView && data instanceof Bitmap){
                                            Log.e("TAG","view=="+view);
                                            ImageView iv = (ImageView)view;
                                            iv.setImageBitmap((Bitmap)data);
                                            return true;
                                        }else{
                                            return false;
                                        }
                                    }
                                });
                                //为GridView设置适配器
                                gridview.setAdapter(adapter);
                            }
                        });
            }



            //添加消息处理
            gridview.setOnItemClickListener(new ItemClickListener());
        }
    }

//    private void showGridView() {
//        ArrayList<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();
//        for (int i = 0; i < 10; i++) {
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            map.put("ItemImage", R.drawable.ic_launcher);//添加图像资源的ID
//            map.put("ItemText", "NO." + String.valueOf(i));//按序号做ItemText
//            items.add(map);
//        }
//        adapter = new SimpleAdapter(mContext,
//                items,
//                R.layout.daren_item,
//                new String[]{"ItemImage", "ItemText"},
//                new int[]{R.id.ItemImage, R.id.ItemText});
//
//        //为GridView设置适配器
//        gridview.setAdapter(adapter);
//        //添加消息处理
//        gridview.setOnItemClickListener(new ItemClickListener());
//    }


    class ItemClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> arg0,//The AdapterView where the click happened
                                View arg1,//The view within the AdapterView that was clicked
                                int arg2,//The position of the view in the adapter
                                long arg3//The row id of the item that was clicked
        ) {
            //在本例中arg2=arg3
            HashMap<String, Object> item = (HashMap<String, Object>) arg0.getItemAtPosition(arg2);
            //显示所选Item的ItemText
            Log.e("TAG",item + "===");
            //setTitle((String) item.get("ItemText"));
        }

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
