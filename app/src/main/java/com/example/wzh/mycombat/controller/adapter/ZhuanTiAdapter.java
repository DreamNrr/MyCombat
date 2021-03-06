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
import com.example.wzh.mycombat.modle.bean.ZTBean;
import com.example.wzh.mycombat.utils.OnItemClickListener;

import java.util.List;

/**
 * Created by WZH on 2017/7/7.
 */

public class ZhuanTiAdapter extends RecyclerView.Adapter<ZhuanTiAdapter.MyViewHolder> implements View.OnClickListener{
    Context mContext;
    List<ZTBean.DataBean.ItemsBean> datas;
    private OnItemClickListener mOnItemClickListener;
    public ZhuanTiAdapter(Context mContext, List<ZTBean.DataBean.ItemsBean> datas) {
        this.datas = datas;
        this.mContext = mContext;
    }

    @Override
    public ZhuanTiAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                mContext).inflate(R.layout.item_zhuanti, parent,
                false);
        ZhuanTiAdapter.MyViewHolder holder = new ZhuanTiAdapter.MyViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ZhuanTiAdapter.MyViewHolder holder, int position) {
        Glide.with(mContext)
                .load(datas.get(position).getCover_img_new())
                .into(holder.iv);
        holder.tv.setText(datas.get(position).getTopic_name());
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(view);
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.imageview);
            tv = (TextView) itemView.findViewById(R.id.textview);
        }
    }
}
