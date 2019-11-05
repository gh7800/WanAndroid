package cn.shineiot.base.utils;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.shineiot.base.BaseApplication;
import cn.shineiot.base.R;


/**
 * @author zhjh.
 */
public class ToastUtils {
	public static Toast toast;

	public static void showToast(String msg) {
		if(toast == null) {
			toast = new Toast(BaseApplication.context());
		}
		View view = LayoutInflater.from(BaseApplication.context()).inflate(R.layout.layout_toast, null);
		TextView textView = view.findViewById(R.id.toast_tv);
		ImageView imgView = view.findViewById(R.id.toast_imageView);
		imgView.setVisibility(View.GONE);
		toast.setView(view);

		if (null != msg && msg.contains("504")) {
			msg = "网络连接失败";
		}
		textView.setText(msg);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM, 0, 200);
		toast.show();

	}

	/**
	 * 居中
	 * @param msg
	 */
	public static void showToastCenter(String msg) {
		if(toast == null) {
			toast = new Toast(BaseApplication.context());
		}
		View view = LayoutInflater.from(BaseApplication.context()).inflate(R.layout.layout_toast, null);
		TextView textView = view.findViewById(R.id.toast_tv);
		ImageView imgView = view.findViewById(R.id.toast_imageView);
		imgView.setVisibility(View.GONE);
		toast.setView(view);

		if (null != msg && msg.contains("504")) {
			msg = "网络连接失败";
		}
		textView.setText(msg);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	/**
	 * 成功toast
	 * @param msg
	 */
	public static void showSucceessToast(String msg) {
		if(toast == null) {
			toast = new Toast(BaseApplication.context());
		}
		View view = LayoutInflater.from(BaseApplication.context()).inflate(R.layout.layout_toast, null);
		ImageView imgView = view.findViewById(R.id.toast_imageView);
		TextView textView = view.findViewById(R.id.toast_tv);
		imgView.setBackgroundResource(R.drawable.icon_right);
		toast.setView(view);

		textView.setText(msg);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM, 0, 200);
		toast.show();
	}

	/**
	 * 失败的toast
	 * @param msg
	 */
	public static void showErrorToast(String msg) {
		if(toast == null) {
			toast = new Toast(BaseApplication.context());
		}
		View view = LayoutInflater.from(BaseApplication.context()).inflate(R.layout.layout_toast, null);
		ImageView imgView = view.findViewById(R.id.toast_imageView);
		TextView textView = view.findViewById(R.id.toast_tv);
		imgView.setBackgroundResource(R.drawable.icon_error);
		toast.setView(view);

		if (null != msg && msg.contains("504")) {
			msg = "网络连接失败";
		}
		textView.setText(msg);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM, 0, 200);
		toast.show();
	}

	public static void cancleToast() {
		toast.cancel();
	}
}
