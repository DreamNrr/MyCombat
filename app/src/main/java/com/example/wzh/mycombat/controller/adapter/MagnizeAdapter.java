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
import com.example.wzh.mycombat.utils.DateChange;
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

        Log.e("TAA","时间==" + datas.get(position).getAddtime());
        // 时间==2017-07-05 23:00:38
        String addtime = datas.get(position).getAddtime();
        Log.e("TAA","截取==" + addtime.substring(5,7) + "，" +addtime.substring(8,10) );
        // 截取==07，05

        String substring = addtime.substring(5, 7);

        String s = DateChange.dateFormat(substring);
        Log.e("TAA","拼接===" +  s);
        holder.tv3.setText("- " + s + "." + addtime.substring(8, 10) + " -");
        //+ DateChange.dateFormat(addtime.substring(5, 6)) + "."
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
