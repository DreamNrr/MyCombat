package com.example.wzh.mycombat.controller.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.holder.ADHolder;
import com.example.wzh.mycombat.holder.GifHolder;
import com.example.wzh.mycombat.holder.ImageHolder;
import com.example.wzh.mycombat.holder.TextHolder;
import com.example.wzh.mycombat.holder.VideoHoder;
import com.example.wzh.mycombat.modle.bean.GoodBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WZH on 2017/7/9.
 */
public class GoodFragmentAdapter extends BaseAdapter{
    private final Context mContext;
    private final List<GoodBean.ListBean> datas = new ArrayList<>();

    public GoodFragmentAdapter(Context mContext) {
        this.mContext = mContext;
    }
    //视频
    private static final int TYPE_VIDEO = 0;

    /**
     * 图片
     */
    private static final int TYPE_IMAGE = 1;

    //图片
    private static final int TYPE_TEXT = 2;

    //GIF图片
    private static final int TYPE_GIF = 3;


    //广告
    private static final int TYPE_AD = 4;

    //总数量
    @Override
    public int getCount() {
        return datas.size();
    }

    //返回总类型数据
    @Override
    public int getViewTypeCount() {
        return 5;
    }

    //返回Item的类型
    @Override
    public int getItemViewType(int position) {
        int itemViewType = -1;
        //根据位置，从列表中得到一个数据对象
        GoodBean.ListBean listBean = datas.get(position);
        String type = listBean.getType();//得到类型
        Log.e("TAG", "type===" + type);
        if ("video".equals(type)) {
            itemViewType = TYPE_VIDEO;
        } else if ("image".equals(type)) {
            itemViewType = TYPE_IMAGE;
        } else if ("text".equals(type)) {
            itemViewType = TYPE_TEXT;
        } else if ("gif".equals(type)) {
            itemViewType = TYPE_GIF;
        } else {
            itemViewType = TYPE_AD;//广播
        }
        return itemViewType;
    }



    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = initView(convertView, getItemViewType(position), datas.get(position));
        return convertView;
    }
    private View initView(View convertView, int itemViewType, GoodBean.ListBean mediaItem) {
        switch (itemViewType) {
            case TYPE_VIDEO://视频

                VideoHoder videoHoder;
                if (convertView == null) {
                    convertView = View.inflate(mContext, R.layout.all_video_item, null);
                    videoHoder = new VideoHoder(mContext,convertView);
                    convertView.setTag(videoHoder);
                } else {
                    videoHoder = (VideoHoder) convertView.getTag();
                }

                //设置数据
                videoHoder.setData(mediaItem);

                break;
            case TYPE_IMAGE://图片
                ImageHolder imageHolder;
                if (convertView == null) {
                    convertView = View.inflate(mContext, R.layout.all_image_item, null);
                    imageHolder = new ImageHolder(mContext,convertView);
                    convertView.setTag(imageHolder);
                } else {
                    imageHolder = (ImageHolder) convertView.getTag();
                }
                //设置数据
                imageHolder.setData(mediaItem);
                break;
            case TYPE_TEXT://文字

                TextHolder textHolder;
                if (convertView == null) {
                    convertView = View.inflate(mContext, R.layout.all_text_item, null);
                    textHolder = new TextHolder(convertView);

                    convertView.setTag(textHolder);
                } else {
                    textHolder = (TextHolder) convertView.getTag();
                }

                textHolder.setData(mediaItem);

                break;
            case TYPE_GIF://gif

                GifHolder gifHolder;
                if (convertView == null) {
                    convertView = View.inflate(mContext, R.layout.all_gif_item, null);
                    gifHolder = new GifHolder(convertView);

                    convertView.setTag(gifHolder);
                } else {
                    gifHolder = (GifHolder) convertView.getTag();
                }

                gifHolder.setData(mediaItem);

                break;
            case TYPE_AD://软件广告

                ADHolder adHolder;
                if (convertView == null) {
                    convertView = View.inflate(mContext, R.layout.all_ad_item, null);
                    adHolder = new ADHolder(convertView);
                    convertView.setTag(adHolder);
                } else {
                    adHolder = (ADHolder) convertView.getTag();
                }

                break;
        }
        return convertView;
    }

    public void setDatas(boolean isLoadMore, List<GoodBean.ListBean> trailers) {

        if(trailers != null) {
            if(!isLoadMore) {
                datas.clear();
            }
            datas.addAll(trailers);
            notifyDataSetChanged();

        }
    }
}
