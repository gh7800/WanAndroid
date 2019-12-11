package cn.shineiot.base.http;

import java.io.IOException;

import cn.shineiot.base.BaseApplication;
import cn.shineiot.base.utils.SPUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {

	@Override
	public Response intercept(Chain chain) throws IOException {

		final Request.Builder builder = chain.request().newBuilder();

		//添加Cookie
		//        if(!TextUtils.isEmpty(NetClient.COOKIE)){
		builder.addHeader("Cookie", (String) SPUtils.get(BaseApplication.context(),"cookie",""));
		//   }
		return chain.proceed(builder.build());
	}

}