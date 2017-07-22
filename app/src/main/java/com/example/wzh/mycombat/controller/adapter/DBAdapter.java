package com.example.wzh.mycombat.controller.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.modle.bean.DBBean;
import com.example.wzh.mycombat.utils.ImageUtils;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WZH on 2017/7/21.
 */
public class DBAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<DBBean> dblist;
    private  CheckBox allCheck;
    private TextView payFeeTv;

    public DBAdapter(Context context, ArrayList<DBBean> dblist, CheckBox allCheck, TextView payFeeTv) {
        this.context = context;
        this.dblist = dblist;
        this.allCheck = allCheck;
        this.payFeeTv = payFeeTv;
    }

    @Override
    public int getCount() {
        return dblist == null ? 0 : dblist.size();
    }

    @Override
    public ArrayList<DBBean> getItem(int i) {
        return dblist;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null) {
            view = View.inflate(context, R.layout.shopping_car_item, null);
            viewHolder = new ViewHolder(view);
            //图片
            // Glide.with(context).load(dblist.get(i).getImageUrl()).asBitmap().into(viewHolder.ivGov);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Glide.with(context).load(dblist.get(i).getImageUrl()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                viewHolder.ivGov.setImageBitmap(ImageUtils.toRound(resource));
            }
        });

        //名字
        viewHolder.tvDescGov.setText(dblist.get(i).getGoodsName());
        Log.e("TAA", "名字：==" + dblist.get(i).getGoodsName());
        //content
        viewHolder.llSize.setText(dblist.get(i).getContent());
        //价格
        viewHolder.tvPriceGov.setText(dblist.get(i).getPrice());
        //数量
        viewHolder.tvGoodsNumber.setText("x " + dblist.get(i).getCount());

        viewHolder.cbGov.setChecked(dblist.get(i).isChicked());
        viewHolder.cbGov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //在布局中设置了不可选择，在这里变成可以选择了
                dblist.get(i).setChicked(!dblist.get(i).isChicked());

                //刷新适配器
                notifyDataSetChanged();
                //改变价格
                showTotalPrice();
                //判断里层的按钮选择状态改变外层的按钮状态
                 boxisAllChick();

            }
        });


        return view;
    }
    /*
    获取总价
     */
    public void showTotalPrice() {
        payFeeTv.setText("￥" + sum() + "");
    }

    public double sum(){
        double preValue = 0;
        if (dblist != null && dblist.size() > 0) {
            for (int i = 0; i < dblist.size(); i++) {
                if (dblist.get(i).isChicked()) {
                    preValue += Double.parseDouble(dblist.get(i).getPrice());
                }
            }
        }
        return preValue;

    }

    /*
    外面全选与全不选
     */
    public void isAllChick(boolean checked){
        if (dblist != null && dblist.size() > 0) {
            for (int i = 0; i < dblist.size(); i++) {
                DBBean dbBean = dblist.get(i);
                dbBean.setChicked(checked);
                notifyDataSetChanged();
            }
        }
    }

    /*
    里面全选与全不选
     */
    public void boxisAllChick(){
        if(dblist != null && dblist.size() >= 0) {
            int count = 0;
            for(int i = 0; i < dblist.size(); i++) {
                if(!dblist.get(i).isChicked()) {
                    allCheck.setChecked(false);
                }else {
                    count++;
                }
            }
            if(count == dblist.size()) {
                allCheck.setChecked(true);
            }else {
                allCheck.setChecked(false);
            }
        }
    }






    static class ViewHolder {
        @InjectView(R.id.iv_gov)
        ImageView ivGov;
        @InjectView(R.id.tv_desc_gov)
        TextView tvDescGov;
        @InjectView(R.id.ll_size)
        TextView llSize;
        @InjectView(R.id.tv_price_gov)
        TextView tvPriceGov;
        @InjectView(R.id.tv_goods_number)
        TextView tvGoodsNumber;
        @InjectView(R.id.cb_gov)
        CheckBox cbGov;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}
