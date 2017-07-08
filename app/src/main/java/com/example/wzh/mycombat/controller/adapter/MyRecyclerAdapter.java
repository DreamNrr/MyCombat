package com.example.wzh.mycombat.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.modle.bean.SYBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by WZH on 2017/7/8.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<SYBean.DataBean.ItemsBean.ListBean> datas;

    public MyRecyclerAdapter(Context mContext, List<SYBean.DataBean.ItemsBean.ListBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        //根据viewType生成viewHolder
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type1, null);
                viewHolder = new VH(view);
                break;
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type2, null);
                viewHolder = new VH1(view);
                break;
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type3, null);
                viewHolder = new VH2(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case 0:
                VH vh = (VH) holder;
//                Glide.with(mContext)
//                        .asBitmap()
//                        .load(datas.get(position).getOne().getPic_url())
//                        .into(vh.imageView1);
                Picasso.with(mContext)
                        .load(datas.get(position).getOne().getPic_url())
                        .into(vh.imageView1);
                break;
            case 1:
                VH1 vh1 = (VH1) holder;
                Picasso.with(mContext)
                        .load(datas.get(position).getOne().getPic_url())
                        .into(vh1.ImageView201);
                Picasso.with(mContext)
                        .load(datas.get(position).getTwo().getPic_url())
                        .into(vh1.ImageView202);
                Picasso.with(mContext)
                        .load(datas.get(position).getThree().getPic_url())
                        .into(vh1.ImageView203);
                Picasso.with(mContext)
                        .load(datas.get(position).getFour().getPic_url())
                        .into(vh1.ImageView204);
                break;
            case 2:
                VH2 vh2 = (VH2) holder;
                Picasso.with(mContext)
                        .load(datas.get(position).getOne().getPic_url())
                        .into(vh2.ImageView301);
                Picasso.with(mContext)
                        .load(datas.get(position).getTwo().getPic_url())
                        .into(vh2.ImageView302);
                break;
        }


    }


    @Override
    public int getItemViewType(int position) {
        //跟据position对应的条目返回去对应的样式（Type）
        int home_type = Integer.parseInt(datas.get(position).getHome_type());
        Log.e("TAG","data=="+datas);
        if (home_type == 1) {
            return 0;
        } else if (home_type == 4) {
            return 1;
        } else
            return 2;
    }


    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        ImageView imageView1;
        public VH(View itemView) {
            super(itemView);
            imageView1 = (ImageView) itemView.findViewById(R.id.im01);
        }
    }

    public class VH1 extends RecyclerView.ViewHolder {
        ImageView ImageView201;
        ImageView ImageView202;
        ImageView ImageView203;
        ImageView ImageView204;
        public VH1(View itemView) {
            super(itemView);
            ImageView201= (ImageView) itemView.findViewById(R.id.iv1_type2);
            ImageView202= (ImageView) itemView.findViewById(R.id.iv2_type2);
            ImageView203= (ImageView) itemView.findViewById(R.id.iv3_type2);
            ImageView204= (ImageView) itemView.findViewById(R.id.iv4_type2);
        }
    }

    public class VH2 extends RecyclerView.ViewHolder {
        ImageView ImageView301;
        ImageView ImageView302;
        public VH2(View itemView) {
            super(itemView);
            ImageView301= (ImageView) itemView.findViewById(R.id.iv01_type3);
            ImageView302= (ImageView) itemView.findViewById(R.id.iv02_type3);
        }
    }

}
