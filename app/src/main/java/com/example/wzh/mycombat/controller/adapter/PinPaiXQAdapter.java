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
import com.example.wzh.mycombat.modle.bean.PPXQBean;
import com.example.wzh.mycombat.utils.OnItemClickListener;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WZH on 2017/7/7.
 */

public class PinPaiXQAdapter extends RecyclerView.Adapter<PinPaiXQAdapter.MyViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<PPXQBean.DataBean.ItemsBean> datas;
    private OnItemClickListener mOnItemClickListener;

    public PinPaiXQAdapter(Context mContext, List<PPXQBean.DataBean.ItemsBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                mContext).inflate(R.layout.item_xq_fenlei, parent,
                false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(mContext)
                .load(datas.get(position).getGoods_image())
                .into(holder.imageView);
        holder.itemView.setTag(position);
        holder.titleTv.setText(datas.get(position).getGoods_name());
        holder.zuozheTv.setText(datas.get(position).getBrand_info().getBrand_name());
        holder.like.setText(datas.get(position).getLike_count());
        holder.jiaqian.setText("￥ "+ datas.get(position).getPrice());
    }


    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(view);
        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.imageView)
        ImageView imageView;
        @InjectView(R.id.title_tv)
        TextView titleTv;
        @InjectView(R.id.zuozhe_tv)
        TextView zuozheTv;
        @InjectView(R.id.jiaqian)
        TextView jiaqian;
        @InjectView(R.id.like_count)
        TextView like;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }

}
