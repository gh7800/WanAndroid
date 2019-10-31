package cn.shineiot.android.http;

import cn.shineiot.android.bean.AndroidNews;
import cn.shineiot.android.bean.Banner;
import cn.shineiot.base.http.HttpClient;
import cn.shineiot.base.module.BaseListResult;
import cn.shineiot.base.module.BaseResult;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author GF63
 */
public interface HttpService {

	Http HTTP = HttpClient.getInstace().create(Http.class);

	interface Http {

		/**
		 * banner
		 */
		@GET("banner/json")
		Observable<BaseListResult<Banner>> getBanner();

		/**
		 * Android news
		 */
		@GET("article/list/{page}/json")
		Observable<BaseResult<AndroidNews>> getAndroidNews(@Path("page")int page);
	}
}
