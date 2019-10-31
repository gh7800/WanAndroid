package cn.shineiot.android.utils;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

import cn.shineiot.base.utils.GlideUtil;

/**
 * @author GF63
 * 图片加载器
 */
public class GlideImageLoader extends ImageLoader {

	@Override
	public void displayImage(Context context, Object path, ImageView imageView) {
		String imgPath = (String) path;
		GlideUtil.loadImg(imgPath,imageView);
	}
}
