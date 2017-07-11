package com.example.wzh.mycombat.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.modle.bean.MagnizeBean;
import com.example.wzh.mycombat.utils.OnItemClickListener;

import java.util.List;

/**
 * Created by WZH on 2017/7/7.
 */

public class MagnizeAdapter extends RecyclerView.Adapter<MagnizeAdapter.MyViewHolder> implements View.OnClickListener{
    Context mContext;
    List<MagnizeBean.Data.Items.ProductBean> datas;
    private OnItemClickListener mOnItemClickListener;
    public MagnizeAdapter(Context mContext, List<MagnizeBean.Data.Items.ProductBean> datas) {
        this.datas = datas;
        this.mContext = mContext;
    }

    @Override
    public MagnizeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                mContext).inflate(R.layout.item_zazhi, parent,
                false);
        MagnizeAdapter.MyViewHolder holder = new MagnizeAdapter.MyViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MagnizeAdapter.MyViewHolder holder, int position) {
        Glide.with(mContext)
                .load(datas.get(position).getCover_img())
                .into(holder.iv);
        holder.tv1.setText(datas.get(position).getTopic_name());
        Log.e("TAA","图片名字===" + datas.get(position).getTopic_name());
        holder.tv2.setText("- "+datas.get(position).getCat_name()+ " -");
//        holder.tv3.setText(datas.get(position).getTopic_name());
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
        TextView tv1;
        TextView tv2;
        TextView tv3;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.imageview);
            tv1 = (TextView) itemView.findViewById(R.id.textview1);
            tv2 = (TextView) itemView.findViewById(R.id.textview2);
            tv3 = (TextView) itemView.findViewById(R.id.textview3);
        }
    }
}
