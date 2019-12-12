package cn.shineiot.base.http;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author GF63
 */
public class AddCookiesInterceptor implements Interceptor {
	private Context context;

	public AddCookiesInterceptor(Context context) {
		super();
		this.context = context;

	}

	@Override
	public Response intercept(Chain chain) throws IOException {

		final Request.Builder builder = chain.request().newBuilder();
		SharedPreferences sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		String cookie = sharedPreferences.getString("cookie", "");
		builder.addHeader("Cookie", cookie);
		return chain.proceed(builder.build());
	}
}