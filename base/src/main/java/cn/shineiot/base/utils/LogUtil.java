package cn.shineiot.base.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

/**
 * @author GF63
 */
public class LogUtil {

	private static final String TAG = "tag";
	private static final boolean isDebug = false;


	public static void v(String msg) {
		if (isDebug)
			Log.v(TAG, buildMessage(msg));
	}

	public static void v(String msg, Throwable thr) {
		if (isDebug)
			Log.v(TAG, buildMessage(msg), thr);
	}

	public static void d(String msg) {
		if (isDebug)
			Log.d(TAG, buildMessage(msg));
	}

	public static void d(String msg, Throwable thr) {
		if (isDebug)
			Log.d(TAG, buildMessage(msg), thr);
	}

	public static void i(String msg) {
		Log.i(TAG, buildMessage(msg));
	}

	public static void i(String msg, Throwable thr) {
		Log.i(TAG, buildMessage(msg), thr);
	}

	public static void w(String msg) {
		Log.w(TAG, buildMessage(msg));
	}

	public static void w(String msg, Throwable thr) {
		Log.w(TAG, buildMessage(msg), thr);
	}

	public static void w(Throwable thr) {
		Log.w(TAG, buildMessage("warining"), thr);
	}

	public static void e(String msg) {
		if(null == msg) {
			return;
		}
		Log.e(TAG, buildMessage(msg));
	}

	public static void e(Object obj) {
		if(null == obj) {
			return;
		}
		Log.e(TAG, "" + obj);
	}

	public static void e(Throwable thr) {
		Log.e(TAG, buildMessage("error"), thr);
	}


	public static void e(String msg, Throwable thr) {
		Log.e(TAG, buildMessage(msg), thr);
	}

	private static String buildMessage(String msg) {
		StackTraceElement caller = new Throwable().fillInStackTrace().getStackTrace()[2];
//        return caller.getFileName()
//                + "." + caller.getMethodName()
//                + "(): " + msg;
		return msg;
	}

	/**
	 * 判断是否是debug版本
	 */
	public static boolean isDebugVersion(Context context) {
		try {
			ApplicationInfo info = context.getApplicationInfo();
			return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
