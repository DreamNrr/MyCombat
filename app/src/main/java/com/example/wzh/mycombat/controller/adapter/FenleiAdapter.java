package com.example.wzh.mycombat.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.modle.bean.FlBean;

import java.util.List;

/**
 * Created by WZH on 2017/7/7.
 */

public class FenleiAdapter extends RecyclerView.Adapter<FenleiAdapter.MyViewHolder> {
    private Context mContext;
    private List<FlBean.DataBean.ItemsBean> datas;
    public FenleiAdapter(Context mContext, List<FlBean.DataBean.ItemsBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item_fenlei, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(FenleiAdapter.MyViewHolder holder, int position) {
        Glide.with(mContext)
                    .load(datas.get(position).getNew_cover_img())
                    .into(holder.iv);

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
