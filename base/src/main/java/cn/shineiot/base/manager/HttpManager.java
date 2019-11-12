package cn.shineiot.base.manager;

import android.annotation.SuppressLint;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.shineiot.base.BaseApplication;
import cn.shineiot.base.bean.HttpCookie;
import cn.shineiot.base.utils.LogUtil;
import cn.shineiot.base.utils.NetworkUtils;
import cn.shineiot.base.utils.SPUtils;
import cn.shineiot.base.utils.ToastUtils;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author zhang on 2016/10/17.
 */

public class HttpManager {


	private static String authorization = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImQ4Yjg5MjA3YzgzMDFlNTRkY2Q1ODg3ZTQ4ODNhNjcxMjk3ZjA3ZjdkOWYxMjJmY2U0YzhkMGIzMWVmNjE2N2JjYmI5ZTUwMzU5N2U4ZWQxIn0.eyJhdWQiOiIxIiwianRpIjoiZDhiODkyMDdjODMwMWU1NGRjZDU4ODdlNDg4M2E2NzEyOTdmMDdmN2Q5ZjEyMmZjZTRjOGQwYjMxZWY2MTY3YmNiYjllNTAzNTk3ZThlZDEiLCJpYXQiOjE1MzI1MDk3NzYsIm5iZiI6MTUzMjUwOTc3NiwiZXhwIjoxNTY0MDQ1Nzc2LCJzdWIiOiI1NzEwNDdFQi01QjFDLUFFRjYtMzQ2RS00NUQ0QzVGRTkyMEUiLCJzY29wZXMiOlsiKiJdfQ.rgayccVXnzi27cGW4ruS20SEsgmMKeaLVmL4i50D5MBGZwhEwwydL45-8bbTXJXbZdq6PZcSDP3wKSC4IOhgl30_zIpdJ8owCDWWDXWuiHgnZh70EzjpW963uq5XLcNSaDhdvjVM05WS38IvhIlZg3SaJuKVnsLfFgWQX0tUJd-6jdpznnyRCOQlbTJV2lRQNnsPsD3RvS1CZMMzCpa9bMa9C5wvhj7ABXMlYPvjudqsaCv7OL4l8nU5oZp61EFasJ3dXRBwXNQQs7sxdqafCZwVhawvEeI4mo6X-dKfNYMLI6oT5xMc9IL134yFSNVc-ZWKmNoHIpSqlIXMP4wXe9VSUefm8UP5jUDMM-ZzFs6f3A6EnVySPZVxmWf5CQopWx7cPvTaDvamT7TBOG6sKU9nr0KOMZh4JCdmDBCxGcporgF2XDZvM-7wrLZeTOi1ldPvkuRR52MQtt3kb5WBU8oTl2meJqtUF1EeLdK4IOFFi9mHpEqbn_Mg-8VS9oy_8g4TQHbn1rfgnA2gShsExDtIzTaHfLy6Res9Got7BFzMvmWOU83BarhtroImSJmCDp62iYc-L35moqgT4V9fKF4SizOCrEeTADhIrfjpDwKx9-_upjbVP2xPP1BKGcMH-QMFfrCHAZlz12Z_vsNDCk1jGHogYXKjPMM2Rso18Sw";
	/**
	 * 设缓存有效期为5天
	 */
	private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 5;
	/**
	 * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
	 */
	private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
	/**查询网络的Cache-Control设置，头部Cache-Control设为max-age=0*/
	/**
	 * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
	 */
	private static final String CACHE_CONTROL_NETWORK = "max-age= 5";

	private static volatile OkHttpClient sOkHttpClient;

	public static void setAuthorization(String author) {
		authorization = author;

	}

	/**
	 * 云端响应头拦截器，用来配置缓存策略
	 */
	private static Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
		@SuppressLint("MissingPermission")
		@Override
		public Response intercept(Chain chain) throws IOException {

			Request request = chain.request();

			if (!NetworkUtils.isConnected()) {
				request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
			}
			Response originalResponse = chain.proceed(request);
			if (NetworkUtils.isConnected()) {
				//有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
				String cacheControl = request.cacheControl().toString();
				return originalResponse.newBuilder().header("Cache-Control", cacheControl).removeHeader("Pragma").build();
			} else {
				return originalResponse.newBuilder().header("Cache-Control", "public, only-if-cached," + CACHE_STALE_SEC).removeHeader("Pragma").build();
			}
		}
	};


	//增加头部信息
	private static Interceptor headerInterceptor = new Interceptor() {
		@Override
		public Response intercept(Chain chain) throws IOException {

			Request build = chain.request().newBuilder().addHeader("Content-Type", "application/json").addHeader("client-type", "watch").addHeader("Authorization", authorization).build();
			return chain.proceed(build);
		}
	};

	private static Interceptor REWRITE_RESPONSE_INTERCEPTOR = new Interceptor() {
		@Override
		public okhttp3.Response intercept(Chain chain) throws IOException {
			okhttp3.Response originalResponse = chain.proceed(chain.request());
			String cacheControl = originalResponse.header("Cache-Control");
			if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") || cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
				return originalResponse.newBuilder().removeHeader("Pragma").header("Cache-Control", "public, max-age=" + 5000).build();
			} else {
				return originalResponse;
			}
		}
	};

	private static Interceptor REWRITE_RESPONSE_INTERCEPTOR_OFFLINE = new Interceptor() {
		@Override
		public okhttp3.Response intercept(Chain chain) throws IOException {
			Request request = chain.request();
			LogUtil.e("isConnect--" + NetworkUtils.isConnected());
			if (!NetworkUtils.isConnected()) {
				request = request.newBuilder().removeHeader("Pragma").header("Cache-Control", "public, only-if-cached").build();
			}
			return chain.proceed(request);
		}
	};

	/**配置OkHttpClient*/
	public static OkHttpClient getOkHttpClient() {
		if (!NetworkUtils.isConnected()) {
			ToastUtils.showErrorToast( "没有网络，请检查网络设置！");
		}
		
		if (sOkHttpClient == null) {
			synchronized (HttpManager.class) {
				if (sOkHttpClient == null) {
					// OkHttpClient配置是一样的,静态创建一次即可
					// 指定缓存路径,缓存大小100Mb
					Cache cache = new Cache(new File(BaseApplication.context().getCacheDir(), "HttpCache"), 1024 * 1024 * 50);

					final HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
						@Override
						public void log(String message) {
							Log.e("okhttp-", message);
						}
					});

					logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

					sOkHttpClient = new OkHttpClient.Builder().cache(cache)
							.addNetworkInterceptor(REWRITE_RESPONSE_INTERCEPTOR)
							.addInterceptor(REWRITE_RESPONSE_INTERCEPTOR_OFFLINE)
							//.addInterceptor(mLoggingInterceptor)
							.addInterceptor(logInterceptor)
							.addInterceptor(headerInterceptor)
							.retryOnConnectionFailure(true)
							.connectTimeout(30, TimeUnit.SECONDS)
							.readTimeout(30, TimeUnit.SECONDS)
							.writeTimeout(30, TimeUnit.SECONDS)
							.cookieJar(new CookieManager(BaseApplication.context())
							).build();

				}
			}
		}

		return sOkHttpClient;
	}

}
