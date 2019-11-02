package cn.shineiot.base.http;

import cn.shineiot.base.BaseApplication;
import cn.shineiot.base.R;
import cn.shineiot.base.manager.HttpManager;
import cn.shineiot.base.utils.NetworkUtils;
import cn.shineiot.base.utils.ToastUtils;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @author wangs
 */
public class HttpClient {


    public static Retrofit getInstace() {
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showErrorToast( "没有网络，请检查网络设置！");
        }
        //添加token
//        String token = SharedPrefsUtil.getValue(BaseApplication.context(), Config.TOKEN, "");

//        if (!TextUtils.isEmpty(token)) {
//            HttpManager.setAuthorization(token);
//        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.context().getResources().getString(R.string.strKey))
                .client(HttpManager.getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit;
    }

}
