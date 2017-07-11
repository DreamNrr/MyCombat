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
import com.example.wzh.mycombat.modle.bean.DrGFBean;
import com.example.wzh.mycombat.modle.bean.DrXQBean;
import com.example.wzh.mycombat.utils.OnItemClickListener;

import java.util.List;

/**
 * Created by WZH on 2017/7/10.
 */
public class DarxqAdapter<T> extends RecyclerView.Adapter<DarxqAdapter.MyViewHolder> implements View.OnClickListener {
    private Context mContext;
    private List<T> datas;
    private OnItemClickListener mOnItemClickListener;

    public DarxqAdapter(Context mContext, List<T> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public DarxqAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                mContext).inflate(R.layout.item_darenxq, parent,
                false);
        DarxqAdapter.MyViewHolder holder = new DarxqAdapter.MyViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(DarxqAdapter.MyViewHolder holder, int position) {
        T t = datas.get(position);
        if(t instanceof DrXQBean.DataBean.ItemsBean.GoodsBean) {
            DrXQBean.DataBean.ItemsBean.GoodsBean goodsBean = (DrXQBean.DataBean.ItemsBean.GoodsBean) t;
            Glide.with(mContext)
                    .load(goodsBean.getGoods_image())
                    .into(holder.iv);
            holder.tv.setVisibility(View.GONE);
        }
        if(t instanceof DrGFBean.DataBean.ItemsBean.UsersBean) {

            DrGFBean.DataBean.ItemsBean.UsersBean usersBean = (DrGFBean.DataBean.ItemsBean.UsersBean) t;
            Glide.with(mContext)
                    .load(usersBean.getUser_image().getOrig())
                    .into(holder.iv);
            Log.e("TAA","User_name====" + usersBean.getUser_name());
            Log.e("TAA","getUser_desc====" + usersBean.getUser_desc());
            Log.e("TAA","getUser_image====" + usersBean.getUser_image());
            holder.tv.setText(usersBean.getUser_name() + "\n" + usersBean.getUser_desc());
        }
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

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.imageview);
            tv = (TextView) itemView.findViewById(R.id.textview);
        }
    }
}
