package cn.shineiot.login.http;

import cn.shineiot.base.http.HttpClient;
import cn.shineiot.base.module.BaseResult;
import cn.shineiot.base.bean.User;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author wangs
 */
public interface HttpService {

     Http HTTP = HttpClient.getInstace().create(Http.class);

    interface Http{
        /**
         * 绑定设备+用户
         */
        @FormUrlEncoded
        @POST("user/login")
        Observable<BaseResult<User>> login (@Field("username") String username, @Field("password") String password);

    }

}
