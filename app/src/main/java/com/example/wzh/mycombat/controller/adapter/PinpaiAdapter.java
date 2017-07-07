package com.example.wzh.mycombat.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.modle.bean.PpBean;

import java.util.List;

/**
 * Created by WZH on 2017/7/7.
 */

public class PinpaiAdapter extends RecyclerView.Adapter<PinpaiAdapter.MyViewHolder>{
    Context mContext;
    List<PpBean.DataBean.ItemsBean> datas;
    public PinpaiAdapter(Context mContext, List<PpBean.DataBean.ItemsBean> datas) {
        this.datas = datas;
        this.mContext = mContext;
    }

    @Override
    public PinpaiAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PinpaiAdapter.MyViewHolder holder = new PinpaiAdapter.MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item_pinpai, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(PinpaiAdapter.MyViewHolder holder, int position) {
        Glide.with(mContext)
                .load(datas.get(position).getBrand_logo())
                .into(holder.iv);
        holder.tv.setText(datas.get(position).getBrand_name());
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.imageView);
            tv = (TextView) itemView.findViewById(R.id.textview);
        }
    }
}
