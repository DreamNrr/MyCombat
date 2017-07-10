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
import com.example.wzh.mycombat.modle.bean.LwuBean;
import com.example.wzh.mycombat.utils.OnItemClickListener;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WZH on 2017/7/10.
 */
public class LiwuAdapter extends RecyclerView.Adapter<LiwuAdapter.MyViewHolder> implements View.OnClickListener{
    Context mContext;
    List<LwuBean.DataBean.ItemsBean> datas;
    private OnItemClickListener mOnItemClickListener;
    public LiwuAdapter(Context mContext, List<LwuBean.DataBean.ItemsBean> datas) {
        this.datas = datas;
        this.mContext = mContext;
    }

    @Override
    public LiwuAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                mContext).inflate(R.layout.item_xq_fenlei, parent,
                false);
        LiwuAdapter.MyViewHolder holder = new LiwuAdapter.MyViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(LiwuAdapter.MyViewHolder holder, int position) {
        Glide.with(mContext)
                .load(datas.get(position).getGoods_image())
                .into(holder.imageView);
        holder.titleTv.setText(datas.get(position).getGoods_name());
        holder.zuozheTv.setText(datas.get(position).getBrand_info().getBrand_name());
        holder.like.setText(datas.get(position).getLike_count());
        holder.jiaqian.setText("￥ "+ datas.get(position).getPrice());
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
