package cn.shineiot.base.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.shineiot.base.BaseApplication;


/**
 * @author zhjh.
 */
public class ToastUtils {
	public static Toast toast;

	public static void showToast(Context context, String msg) {
		if (null == toast) {
			toast = Toast.makeText(BaseApplication.context(), "", Toast.LENGTH_SHORT);
		}
		toast.setText(msg);
		toast.setGravity(Gravity.BOTTOM, 0, 200);
		toast.show();
		/*if (toast == null) {
			toast = Toast.makeText(BaseApplication.context(), msg, Toast.LENGTH_SHORT);
			LinearLayout layout = (LinearLayout) toast.getView();
			layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
			TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
			v.setTextColor(Color.BLACK);
			v.setTextSize(30);
			v.setPadding(10, 10, 10, 10);
			toast.setGravity(Gravity.CENTER,0,0);
		} else {
			toast.setText(msg);
		}
		toast.show();*/
	}

	public static void showDataToast(Context context) {
		if (null == toast) {
			toast = Toast.makeText(BaseApplication.context(), "", Toast.LENGTH_SHORT);
		}
		toast.setText("数据异常，请稍后重试！");
		toast.setGravity(Gravity.BOTTOM, 0, 200);
		toast.show();
	}

	public static void showNetworkToast(Context context, String msg) {
		if (null == toast) {
			toast = Toast.makeText(BaseApplication.context(), "", Toast.LENGTH_SHORT);
		}
		toast.setText("网络异常，请稍后重试！");
		toast.setGravity(Gravity.BOTTOM, 0, 200);
		toast.show();
	}

	/**
	 * 居中显示
	 *
	 * @param context
	 * @param text
	 */
	public static void showToastCenter(Context context, String text) {
		if (null == toast) {
			toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
		}
		toast.setText(text);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	public static void showErr0r(Context context) {
		if (toast == null) {
			toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.BOTTOM, 0, 200);
		}
		toast.setText("系统繁忙，请重试！");
		toast.show();
//        Toast.makeText(context, "系统繁忙，请重试！", Toast.LENGTH_SHORT).show();
	}

	public static void cancleToast() {
		toast.cancel();
	}
}
