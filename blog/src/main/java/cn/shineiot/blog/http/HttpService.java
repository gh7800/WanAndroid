package cn.shineiot.blog.http;

import cn.shineiot.base.http.HttpClient;
import cn.shineiot.base.module.BaseListResult;
import cn.shineiot.base.module.BaseResult;
import cn.shineiot.blog.bean.WxPublic;
import cn.shineiot.blog.bean.WxPublicData;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author GF63
 */
public interface HttpService {
	Http http = HttpClient.getInstace().create(Http.class);

	interface Http {
		@GET("wxarticle/chapters/json")
		Observable<BaseListResult<WxPublic>> getWxPublic();

		@GET("wxarticle/list/{id}/{page}/json")
		Observable<BaseResult<WxPublicData>> getWxArticle(@Path("id") int id, @Path("page") int page);
	}
}
