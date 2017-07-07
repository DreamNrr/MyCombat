package com.example.wzh.mycombat.controller.fragment;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.example.wzh.mycombat.utils.BaseUrl.BASE_URL;

/**
 * Created by WZH on 2017/7/5.
 */

public class GoodFragment extends BaseFragment {

    public final static String EXPERT_RECOMMEND_URL = BASE_URL +
            "user/masterList?app_key=Android&count=18&sig=79F01B94B8EBEFAC8EEB344EE2B20AA2%7C383889010803768&v=1.0&page=1";
    @InjectView(R.id.gridview)
    GridView gridview;
    private SimpleAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.daren_fragment, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        ArrayList<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", R.drawable.ic_launcher);//添加图像资源的ID
            map.put("ItemText", "NO." + String.valueOf(i));//按序号做ItemText
            items.add(map);
        }
         adapter = new SimpleAdapter(mContext,
                items,
                R.layout.daren_item,
                new String[]{"ItemImage", "ItemText"},
                new int[]{R.id.ItemImage, R.id.ItemText});

        //为GridView设置适配器
        gridview.setAdapter(adapter);
        //添加消息处理
        gridview.setOnItemClickListener(new ItemClickListener());
    }


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
