package cn.shineiot.login.login;

import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.base.module.BaseResult;
import cn.shineiot.base.utils.LogUtil;
import cn.shineiot.base.bean.User;
import cn.shineiot.login.http.HttpService;
import rx.Subscriber;

/**
 * @author GF63
 */
public class LoginPresenter extends BasePresenter<LoginView> {
    public void login(String username, String password) {

        mView.showLoading("登录中..");
        addSubscription(HttpService.HTTP.login(username, password), new Subscriber<BaseResult<User>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                mView.showError(e.getMessage());
            }

            @Override
            public void onNext(BaseResult<User> userBaseResult) {
                LogUtil.e(userBaseResult.getData());
                if (userBaseResult.getErrorCode() == 0) {
                    User user = userBaseResult.getData();
                    mView.SuccessData(user);
                } else {
                    mView.showError(userBaseResult.getErrorMsg());
                }
            }
        });
    }

}
