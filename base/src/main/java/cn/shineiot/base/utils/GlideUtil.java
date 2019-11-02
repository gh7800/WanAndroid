package cn.shineiot.base.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.io.File;

/**
 * Created by marno on 2016/4/16/08:55.
 * Glide的为此封装
 */
public final class GlideUtil {

	/**
	 * 加载网络图片到imageview
	 * 缓存转换后的资源
	 *
	 * @param imgUrl
	 * @param imageView
	 */
	public static void loadImg(String imgUrl, ImageView imageView) {
		Glide.with(imageView.getContext())
				.load(imgUrl)
				.centerCrop()
				.dontAnimate()
				.skipMemoryCache(true)
				.diskCacheStrategy(DiskCacheStrategy.RESULT)
				.into(imageView);
	}

	/**
	 * 加载图片，并不缓存
	 * @param imgUrl
	 * @param imageView
	 */
	public static void loadImg_NoCache(String imgUrl, ImageView imageView) {
		Glide.with(imageView.getContext())
				.load(imgUrl)
				.centerCrop()
				.dontAnimate()
				.skipMemoryCache(true)
				.diskCacheStrategy(DiskCacheStrategy.NONE)
				.into(imageView);
	}
	/**
	 *加载gif
	 */
	public static void loadGif(int res, ImageView imageView){
		Glide.with(imageView.getContext())
				.load(res)
				.diskCacheStrategy(DiskCacheStrategy.SOURCE)
				.into(new GlideDrawableImageViewTarget(imageView,1));
	}
	/**
	 * 从资源文件中加载图片
	 *
	 * @param imgRes
	 * @param imageView
	 */
	public static void loadImg(int imgRes, ImageView imageView) {

		Glide.with(imageView.getContext())
				.load(imgRes)
				.dontAnimate()
				.centerCrop()
				.skipMemoryCache(true)
				.diskCacheStrategy(DiskCacheStrategy.RESULT)
				.into(imageView);
	}

	public static void loadWithoutPlaceholder(int imgRes, ImageView imageView) {

		Glide.with(imageView.getContext())
				.load(imgRes)
				.dontAnimate()
				.into(imageView);
	}


	/**
	 * 加载圆形图片到imageview
	 *
	 * @param imgUrl
	 * @param imageView
	 */
	public static void loadRoundImg(String imgUrl, ImageView imageView) {

		Glide.with(imageView.getContext())
				.load(imgUrl)
				.dontAnimate()
				.centerCrop()
				.transform(new GlideCircleTransform(imageView.getContext()))
				.diskCacheStrategy(DiskCacheStrategy.RESULT)
				.into(imageView);
	}
	public static void loadRoundImg(int resId, ImageView imageView) {

		Glide.with(imageView.getContext())
				.load(resId)
				.dontAnimate()
				.centerCrop()
				.transform(new GlideCircleTransform(imageView.getContext()))
				.diskCacheStrategy(DiskCacheStrategy.RESULT)
				.into(imageView);
	}
	public static void loadRoundImg(File file, ImageView imageView) {

		Glide.with(imageView.getContext())
				.load(file)
				.dontAnimate()
				.centerCrop()
				.transform(new GlideCircleTransform(imageView.getContext()))
				.diskCacheStrategy(DiskCacheStrategy.RESULT)
				.into(imageView);
	}

	public static void loadRoundImg(Uri uri, ImageView imageView) {

		Glide.with(imageView.getContext())
				.load(uri)
				.dontAnimate()
				.centerCrop()
				.transform(new GlideCircleTransform(imageView.getContext()))
				.diskCacheStrategy(DiskCacheStrategy.RESULT)
				.into(imageView);
	}

	public static void loadImgUrlWithoutCrop(String imgUrl, ImageView imageView) {

		Glide.with(imageView.getContext())
				.load(imgUrl)
				.dontAnimate()
				.into(imageView);
	}

	/**
	 * 加载矩形圆角  _url
	 */
	public static void loadRectangleCorners(String imgurl, ImageView imageView) {

		Glide.with(imageView.getContext()).load(imgurl)
				//.thumbnail(0.3f)
				//.skipMemoryCache(true)
				//.placeholder(R.drawable.loadimg_bg)
				.transform(new GlideRoundTransform(imageView.getContext(), 6))
				.diskCacheStrategy(DiskCacheStrategy.RESULT)
				//.crossFade()
				.into(imageView);
	}

	/**
	 * 加载矩形圆角——drawable
	 */
	public static void loadRectangleCorners_Drawable(int resource, ImageView imageView) {

		Glide.with(imageView.getContext()).load(resource)
				.transform(new GlideRoundTransform(imageView.getContext(), 6))
				.diskCacheStrategy(DiskCacheStrategy.RESULT)
				.into(imageView);
	}

	/**
	 * 加载矩形圆角——File
	 */
	public static void loadRectangleCorners_File(File file, ImageView imageView) {

		Glide.with(imageView.getContext()).load(file)
				.transform(new GlideRoundTransform(imageView.getContext(), 6))
				.diskCacheStrategy(DiskCacheStrategy.RESULT)
				.into(imageView);
	}

	static class GlideCircleTransform extends BitmapTransformation {

		public GlideCircleTransform(Context context) {
			super(context);
		}

		@Override
		protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
			return circleCrop(pool, toTransform);
		}

		private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
			if (source == null) {
				return null;
			}
			int size = Math.min(source.getWidth(), source.getHeight());
			int x = (source.getWidth() - size) / 2;
			int y = (source.getHeight() - size) / 2;

			Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

			Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
			if (result == null) {
				result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
			}

			Canvas canvas = new Canvas(result);
			Paint paint = new Paint();
			paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
			paint.setAntiAlias(true);
			float r = size / 2f;
			canvas.drawCircle(r, r, r, paint);
			return result;
		}

		@Override
		public String getId() {
			return getClass().getName();
		}
	}

	static class GlideRoundTransform extends BitmapTransformation {

		private static float radius = 0f;

		public GlideRoundTransform(Context context) {
			this(context, 4);
		}

		public GlideRoundTransform(Context context, int dp) {
			super(context);
			this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
		}

		@Override
		protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
			return roundCrop(pool, toTransform);
		}

		private static Bitmap roundCrop(BitmapPool pool, Bitmap source) {
			if (source == null) {
				return null;
			}

			Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
			if (result == null) {
				result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
			}

			Canvas canvas = new Canvas(result);
			Paint paint = new Paint();
			paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
			paint.setAntiAlias(true);
			RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
			canvas.drawRoundRect(rectF, radius, radius, paint);
			return result;
		}

		@Override
		public String getId() {
			return getClass().getName() + Math.round(radius);
		}
	}
}
