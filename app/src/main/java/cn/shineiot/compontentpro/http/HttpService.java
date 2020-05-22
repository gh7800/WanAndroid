package cn.shineiot.compontentpro.http;

import cn.shineiot.base.bean.AndroidArticle;
import cn.shineiot.base.bean.Coin;
import cn.shineiot.base.bean.Collect;
import cn.shineiot.base.bean.Integral;
import cn.shineiot.base.http.HttpClient;
import cn.shineiot.base.module.BaseResult;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author GF63
 */
public interface HttpService {

	Http http = HttpClient.getInstace().create(Http.class);

	interface Http{
		@GET("user/logout/json")
		Observable<BaseResult> loginOut();

		@GET("lg/coin/userinfo/json")
		Observable<BaseResult<Coin>> getMyCoin();

		@GET("coin/rank/1/json")
		Observable<BaseResult<Integral>> getListCoin();

		@GET("wenda/list/{page}/json")
		Observable<BaseResult<Collect>> getWenDa(@Path("page") int page);
	}

}
