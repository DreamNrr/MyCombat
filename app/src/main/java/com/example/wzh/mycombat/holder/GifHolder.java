package com.example.wzh.mycombat.holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wzh.mycombat.R;
import com.example.wzh.mycombat.base.BaseViewHolder;
import com.example.wzh.mycombat.modle.bean.GoodBean;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;


/**
 * Created by WZH on 2017/5/27.
 */

public class GifHolder  extends BaseViewHolder {
    TextView tvContext;
    ImageView ivImageGif;
    private ImageOptions imageOptions;

    public GifHolder(View convertView) {
        super(convertView);
        //中间公共部分 -所有的都有
        tvContext = (TextView) convertView.findViewById(R.id.tv_context);
        ivImageGif = (ImageView) convertView.findViewById(R.id.iv_image_gif);

        imageOptions = new ImageOptions.Builder()
                //包裹类型
                .setSize(ViewGroup.LayoutParams.WRAP_CONTENT, -2)
                //设置圆角
                .setRadius(DensityUtil.dip2px(5))
                .setIgnoreGif(false)//是否忽略gif图。false表示不忽略。不写这句，默认是true
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.drawable.dummy_image)
                .setFailureDrawableId(R.drawable.dummy_image)
                .build();

    }

    public void setData(GoodBean.ListBean mediaItem) {
        super.setData(mediaItem);
        //设置文本-所有的都有
        tvContext.setText(mediaItem.getText() + "_" + mediaItem.getType());

        //下面是gif
        if (mediaItem.getGif() != null && mediaItem.getGif() != null && mediaItem.getGif().getImages() != null) {
//                Glide.with(context).load(mediaItem.getGif().getImages().get(0)).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(ivImageGif);
            x.image().bind(ivImageGif, mediaItem.getGif().getImages().get(0), imageOptions);
        }

    }
}
