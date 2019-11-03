package cn.shineiot.compontentpro.http;

import cn.shineiot.base.http.HttpClient;
import cn.shineiot.base.module.BaseResult;
import retrofit2.http.GET;
import rx.Observable;

public interface HttpService {

	Http http = HttpClient.getInstace().create(Http.class);

	interface Http{
		@GET("user/logout/json")
		Observable<BaseResult> loginOut();
	}
}
