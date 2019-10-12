package cn.shineiot.login.http;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;

import cn.shineiot.base.BaseApplication;
import cn.shineiot.base.manager.HttpManager;
import cn.shineiot.base.utils.NetworkUtils;
import cn.shineiot.base.utils.SharedPrefsUtil;
import cn.shineiot.base.utils.ToastUtils;
import cn.shineiot.login.R;
import cn.shineiot.login.utils.Config;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @author wangs
 */
public class HttpClient {

	private static HttpClient sInstance;
	private static HttpService httpservice;

	// public static String authorization = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjAyOGFjNDFjMGFlNDAxZjEyZDRlNGE3NjA0NzcxNDRhMjM1NGNlMTc0ZTFhMDZkMTU1N2E3ZWU0NDY5NzBiOTljNjdlYmM2YmQ1NDcwM2YyIn0.eyJhdWQiOiIzIiwianRpIjoiMDI4YWM0MWMwYWU0MDFmMTJkNGU0YTc2MDQ3NzE0NGEyMzU0Y2UxNzRlMWEwNmQxNTU3YTdlZTQ0Njk3MGI5OWM2N2ViYzZiZDU0NzAzZjIiLCJpYXQiOjE1MzAxNzg4MzksIm5iZiI6MTUzMDE3ODgzOSwiZXhwIjoxNTYxNzE0ODM5LCJzdWIiOiIzMjYzOEQ3RC1COEJGLTZEODktNkFFMy0xRDBFQjM2RURDMzUiLCJzY29wZXMiOlsiKiJdfQ.FSo6a4wm0WG0o6mYmidGHeuRGzornlPXsGfp8yQqpOnfFA9BFNVz9LjKcsSaNa5Pvz-r7TR7BpkTDaGFGkTLz0N8JexfD2b4ADnzOHLA__tIs-7Y6NFeUjJJVdvyVSop6rpFt1oXoWi7au_JuAJFBOK8lAUWr73W3d6yUyjTfarLsYHxMeRDEsbIGFLlt3XzXgRj1oT0cZBpaE-Ugz6xojPGbi2hPS2kRtEhmPw1gddAVuZGXN8i4ZeYulzR8DBMEW8bxlXf6X5d_Z1yJ6OOtLxM5XSXXmGa94KiokGhWSX3POZkm1Rs9fmi05tPshujSYls7JE3FBi4butqG5BUzze3besIe4HRB9bJneRK3uHPdMtfO0g6QNGjg6UjpCnR4cqvbVYD2cRH-W88umtbSmkzsr0pYbRjUgy3nWr5pJaraV3XSGDHV_XmqGI5lh_y8dHVGM2fDeLEp6ofAXDQdiFRllddTyO49IidgX9I1s14g0Jdn0Nt5UzzfEE2HivWURUIUXITvgJfe8nL_NAOrxfmtD3S45fGveK7EVSBxHFwaNPOsWF8vQW0dzDrP-RGonpdr6nB27z05Ie4sIN74-J2cdr7CEt5V0IlyJvAL6Uy_4eIBBOiQRBggB31M4oJuDVpMZk9busN7BlWkxZJKnoVD1KhQ2n6YtO2nmOiw3k";

	/**
	 * 获取单例
	 * @return 实例
	 */
	@SuppressLint("MissingPermission")
	public static HttpClient getInstance(Context context) {
		if (!NetworkUtils.isConnected()) {
			ToastUtils.showToast(BaseApplication.context(), "没有网络，请检查网络设置！");
		}
		if (sInstance == null) {
			sInstance = new HttpClient();
		}

		return sInstance;
	}

	@SuppressLint("MissingPermission")
	public  static HttpService getinit() {

		if (!NetworkUtils.isConnected()) {
			ToastUtils.showToast(BaseApplication.context(), "没有网络，请检查网络设置！");
		}
		//添加token
		String token = SharedPrefsUtil.getValue(BaseApplication.context(), Config.TOKEN,"");

		if(!TextUtils.isEmpty(token)){
			HttpManager.setAuthorization(token);
		}
		if (httpservice == null) {

			Retrofit retrofit = new Retrofit.Builder()
					.baseUrl(BaseApplication.context().getResources()
							.getString(R.string.strKey))
					.client(HttpManager.getOkHttpClient())
					.addConverterFactory(GsonConverterFactory.create())
					.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
					.build();

			httpservice = retrofit.create(HttpService.class);
		}
		return httpservice;
	}

	private HttpClient() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BaseApplication.context().getResources().getString(R.string.strKey))
				.client(HttpManager.getOkHttpClient())
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.build();

		httpservice = retrofit.create(HttpService.class);
	}

}
