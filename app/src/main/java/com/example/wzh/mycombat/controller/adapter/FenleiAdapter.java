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

import static com.example.wzh.mycombat.utils.BaseUrl.BASE_URL;

/**
 * Created by WZH on 2017/7/7.
 */

public class FenleiAdapter extends RecyclerView.Adapter<FenleiAdapter.MyViewHolder> {
    public final static String CATEGORY_URL =
            BASE_URL + "goods/goodsCategory?app_key=Android&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";
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
//        DisplayImageOptions options;
//        options = new DisplayImageOptions.Builder()
//                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
//                .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
//                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
//                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
//                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
//                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
//                .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
//                .build();//构建完成
//        ImageLoader.getInstance().displayImage(CATEGORY_URL,holder.iv,options);

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
