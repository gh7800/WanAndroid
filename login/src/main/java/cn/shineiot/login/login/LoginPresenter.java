package cn.shineiot.login.login;

import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.base.module.BaseResult;
import cn.shineiot.base.utils.LogUtil;
import cn.shineiot.login.bean.user;
import cn.shineiot.login.http.HttpClient;
import rx.Subscriber;

/**
 * @author GF63
 */
public class LoginPresenter extends BasePresenter<LoginView> {
    public void login(String username ,String password){
            addSubscription(HttpClient.getinit().login(username, password), new Subscriber<BaseResult<user>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    LogUtil.e(e.getMessage());
                }

                @Override
                public void onNext(BaseResult<user> userBaseResult) {
                    LogUtil.e(userBaseResult.getData());
                }
            });
    }

}
