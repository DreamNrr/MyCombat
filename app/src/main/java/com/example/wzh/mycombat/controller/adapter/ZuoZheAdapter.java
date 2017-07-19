package com.example.wzh.mycombat.controller.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.modle.bean.ZuoZheBean;
import com.example.wzh.mycombat.utils.ImageUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WZH on 2017/7/12.
 */
public class ZuoZheAdapter extends BaseAdapter {

    private Context mContext;
    private List<ZuoZheBean.DataBean.ItemsBean> datas;

    public ZuoZheAdapter(Context mContext, List<ZuoZheBean.DataBean.ItemsBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public ZuoZheBean.DataBean.ItemsBean getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view =  View.inflate(mContext, R.layout.item_zuozhe, null);
        final ViewHolder holder = new ViewHolder(view);
        Glide.with(mContext).load(datas.get(i).getThumb()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                holder.imageview.setImageBitmap(ImageUtils.toRound(resource));
            }
        });
        holder.name.setText(datas.get(i).getAuthor_name());
        holder.jieshao.setText(datas.get(i).getNote());

        return view;
    }

    static class ViewHolder {
        @InjectView(R.id.imageview)
        ImageView imageview;
        @InjectView(R.id.name)
        TextView name;
        @InjectView(R.id.jieshao)
        TextView jieshao;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
