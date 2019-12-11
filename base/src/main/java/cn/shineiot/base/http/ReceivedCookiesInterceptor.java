package cn.shineiot.base.http;

import java.io.IOException;

import cn.shineiot.base.BaseApplication;
import cn.shineiot.base.utils.SPUtils;
import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor  implements Interceptor {
	@Override
	public Response intercept(Chain chain) throws IOException {

		Response originalResponse = chain.proceed(chain.request());

		if (!originalResponse.headers("Set-Cookie").isEmpty()) {
			StringBuilder stringBuilder = new StringBuilder();
			//解析Cookie
			for (String header : originalResponse.headers("Set-Cookie")) {

				stringBuilder.append(header);


				if(header.contains("JSESSIONID")){

					String cookie = header.substring(header.indexOf("JSESSIONID"), header.indexOf(";"));
					SPUtils.put(BaseApplication.context(),"cookie", cookie);


				}
			}
		}

		return originalResponse;

	}
}
