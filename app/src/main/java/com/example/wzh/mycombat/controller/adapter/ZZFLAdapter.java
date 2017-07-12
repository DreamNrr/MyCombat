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
import com.example.wzh.mycombat.modle.bean.ZZFLBean;
import com.example.wzh.mycombat.utils.OnItemClickListener;

import java.util.List;

/**
 * Created by WZH on 2017/7/12.
 */
public class ZZFLAdapter  extends RecyclerView.Adapter<ZZFLAdapter.MyViewHolder> implements View.OnClickListener {
    private Context mContext;
    private List<ZZFLBean.DataBean.ItemsBean> datas;
    private OnItemClickListener mOnItemClickListener;


    public ZZFLAdapter(Context mContext, List<ZZFLBean.DataBean.ItemsBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public ZZFLAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                mContext).inflate(R.layout.item_fenlei_zz, parent,
                false);
        ZZFLAdapter.MyViewHolder holder = new ZZFLAdapter.MyViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ZZFLAdapter.MyViewHolder holder, int position) {

        if(position == 0 ) {
            holder.iv.setImageResource(R.drawable.bg_topic_favour);
            holder.tv.setText("我的收藏");
            holder.iv00.setVisibility(View.VISIBLE);
        }
        if(position == 1) {
            holder.iv.setImageResource(R.drawable.bg_topic_all);
            holder.tv.setText("所有杂志");
            holder.iv00.setImageResource(R.drawable.icon_all_topics);
            holder.iv00.setVisibility(View.VISIBLE);
        }

        if(position > 1) {
            holder.iv00.setVisibility(View.GONE);
            Glide.with(mContext)
                    .load(datas.get(position).getThumb())
                    .into(holder.iv);
            //  holder.itemView.setTag(position);
            holder.tv.setText(datas.get(position).getCat_name());
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
        ImageView iv00;
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.imageview);
            iv00 = (ImageView) itemView.findViewById(R.id.im_00);
            tv = (TextView) itemView.findViewById(R.id.textview);
        }
    }
}
