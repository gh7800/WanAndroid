package cn.shineiot.navigation.http;

import cn.shineiot.base.http.HttpClient;
import cn.shineiot.base.module.BaseListResult;
import cn.shineiot.navigation.bean.Navigation;
import retrofit2.http.GET;
import rx.Observable;

/**
 * @author GF63
 */
public interface HttpService {
	Http http = HttpClient.getInstace().create(Http.class);

	interface Http{
		@GET("navi/json")
		Observable<BaseListResult<Navigation>> getNavigationData();
	}
}

