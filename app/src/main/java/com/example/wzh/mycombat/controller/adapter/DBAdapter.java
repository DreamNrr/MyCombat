package com.example.wzh.mycombat.controller.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

    public DBAdapter(Context context, ArrayList<DBBean> dblist) {
        this.context = context;
        this.dblist = dblist;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if(view == null) {
            view = View.inflate(context, R.layout.shopping_car_item, null);
            viewHolder = new ViewHolder(view);
            //图片
           // Glide.with(context).load(dblist.get(i).getImageUrl()).asBitmap().into(viewHolder.ivGov);
            Glide.with(context).load(dblist.get(i).getImageUrl()).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    viewHolder.ivGov.setImageBitmap(ImageUtils.toRound(resource));
                }
            });

            //名字
            viewHolder.tvDescGov.setText(dblist.get(i).getGoodsName());
            Log.e("TAA","名字：==" + dblist.get(i).getGoodsName());
            //content
            viewHolder.llSize.setText(dblist.get(i).getContent());
            //价格
            viewHolder.tvPriceGov.setText(dblist.get(i).getPrice());
            //数量
            viewHolder.tvGoodsNumber.setText("x " + dblist.get(i).getCount());

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        return view;
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

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
