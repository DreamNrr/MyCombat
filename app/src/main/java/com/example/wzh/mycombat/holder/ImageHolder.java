package com.example.wzh.mycombat.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseViewHolder;
import com.example.wzh.mycombat.modle.bean.GoodBean;


/**
 * Created by WZH on 2017/5/27.
 */

public class ImageHolder extends BaseViewHolder {
    TextView tvContext;
    ImageView ivImageIcon;
    Context mContext;


    public ImageHolder(Context mContext, View convertView) {
        super(convertView);
        //中间公共部分 -所有的都有
        tvContext = (TextView) convertView.findViewById(R.id.tv_context);
        ivImageIcon = (ImageView) convertView.findViewById(R.id.iv_image_icon);
        this.mContext = mContext;
    }

    public void setData(GoodBean.ListBean mediaItem) {
        super.setData(mediaItem);
        //设置文本-所有的都有
        tvContext.setText(mediaItem.getText() + "_" + mediaItem.getType());
        //图片特有的

        ivImageIcon.setImageResource(R.drawable.bg_item);
        if (mediaItem.getImage() != null && mediaItem.getImage() != null && mediaItem.getImage().getSmall() != null) {
            Glide.with(mContext).
                    load(mediaItem.getImage().
                            getDownload_url().
                            get(0)).
                    into(ivImageIcon);
        }


    }

}
