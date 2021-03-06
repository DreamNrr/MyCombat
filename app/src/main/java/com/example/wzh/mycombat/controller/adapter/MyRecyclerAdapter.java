package com.example.wzh.mycombat.controller.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.controller.activity.HTMLActivity;
import com.example.wzh.mycombat.modle.bean.SYBean;
import com.example.wzh.mycombat.utils.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by WZH on 2017/7/8.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private String pic_url;
    private Context mContext;
    private List<SYBean.DataBean.ItemsBean.ListBeanX> datas;
    private OnItemClickListener mOnItemClickListener;
    private String h5url;
    private String topic_name;

    public MyRecyclerAdapter(Context mContext, List<SYBean.DataBean.ItemsBean.ListBeanX> datas) {
        this.mContext = mContext;
        this.datas = datas;
        Log.e("AAA","1111===="+ datas.get(3).getOne().getPic_url());
        Log.e("AAA","2222===="+ datas.get(0).getList().get(0).getPic_url());
//        Log.e("AAA","pic_url===" + pic_url);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        //根据viewType生成viewHolder,和下面的view类型一致
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type4, null);
                viewHolder = new VH3(view);
                break;
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type1, null);
                viewHolder = new VH0(view);
                break;
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type3, null);
                viewHolder = new VH2(view);
                break;
            case 3:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type2, null);
                viewHolder = new VH1(view);
                break;
        }
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);
        Log.e("AAA","itemViewType"+itemViewType);
        switch (itemViewType) {
            case 0:
                pic_url = datas.get(position).getList().get(position).getPic_url();
                VH3 vh3 = (VH3) holder;
                Picasso.with(mContext)
                        .load(pic_url)
                        .into(vh3.imageView2);
                break;
            case 1:
                VH0 vh = (VH0) holder;
                Picasso.with(mContext)
                        .load(datas.get(position).getOne().getPic_url())
                        .into(vh.imageView1);
                if (mOnItemClickListener != null) {
                    vh.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            if(position == 0) {
//                                跳转商品那个页面
//                            }
                            h5url = datas.get(position).getOne().getTopic_url();
                            topic_name = datas.get(position).getOne().getTopic_name();
                            startAct();
                        }
                    });}
                break;
            case 2:
                VH2 vh2 = (VH2) holder;

                Picasso.with(mContext)
                        .load(datas.get(position).getOne().getPic_url())
                        .into(vh2.ImageView301);

                Picasso.with(mContext)
                        .load(datas.get(position).getTwo().getPic_url())
                        .into(vh2.ImageView302);

                vh2.ImageView301.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        h5url = datas.get(position).getOne().getTopic_url();
                        topic_name = datas.get(position).getOne().getTopic_name();
                        startAct();
                    }
                });
                vh2.ImageView302.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        h5url = datas.get(position).getTwo().getTopic_url();
                        topic_name = datas.get(position).getTwo().getTopic_name();
                        startAct();
                    }
                });
                break;
            case 3:
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
                vh1.ImageView201.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        h5url = datas.get(position).getOne().getTopic_url();
                        topic_name = datas.get(position).getOne().getTopic_name();
                        startAct();
                    }
                });
                vh1.ImageView202.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        h5url = datas.get(position).getTwo().getTopic_url();
                        topic_name = datas.get(position).getTwo().getTopic_name();
                        startAct();
                    }
                });
                vh1.ImageView203.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        h5url = datas.get(position).getThree().getTopic_url();
                        topic_name = datas.get(position).getThree().getTopic_name();
                        startAct();
                    }
                });
                vh1.ImageView204.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        h5url = datas.get(position).getFour().getTopic_url();
                        topic_name = datas.get(position).getFour().getTopic_name();
                        startAct();
                    }
                });
                break;
        }
    }

    private void startAct() {
        Intent intent = new Intent(mContext, HTMLActivity.class);
        intent.putExtra("HUrl",h5url);
        intent.putExtra("topic_name",topic_name);
        mContext.startActivity(intent);
    }


    @Override
    public int getItemViewType(int position) {
        //跟据position对应的条目返回去对应的样式（Type）
        String home_type =datas.get(position).getHome_type()+"";
        Log.e("TAG","data=="+home_type);
        if (home_type.equals("6")) {
            Log.e("AAA","home_type == '6'");
            return 0;
        } else if (home_type.equals("1")) {
            Log.e("AAA","home_type == '1'");
            return 1;
        } else if(home_type.equals("4")){
            Log.e("AAA","home_type == '4'");
            return 3;
        }else
            Log.e("AAA","home_type == '2'");
            return 2;
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

    public class VH0 extends RecyclerView.ViewHolder {
        ImageView imageView1;
        public VH0(View itemView) {
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

    public class VH3 extends RecyclerView.ViewHolder {
        ImageView imageView2;
        public VH3(View view) {
            super(view);
            imageView2 = (ImageView) itemView.findViewById(R.id.im02_type4);
        }
    }
}
