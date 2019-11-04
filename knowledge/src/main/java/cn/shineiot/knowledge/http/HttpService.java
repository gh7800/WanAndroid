package cn.shineiot.knowledge.http;

import cn.shineiot.base.http.HttpClient;
import cn.shineiot.base.module.BaseListResult;
import cn.shineiot.knowledge.bean.Knowledge;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author GF63
 */
public class HttpService {
	Http http = HttpClient.getInstace().create(Http.class);

	interface Http{
		@GET("tree/json")
		Observable<BaseListResult<Knowledge>> getKnowledgeData();

		@GET("article/list/{page}/json")
		Observable<BaseListResult> getKnowledgeDetail(@Path ("page")int page,@Query("cid")int cid);
	}

}
