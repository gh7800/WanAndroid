package cn.shineiot.compontentpro.ui.home;

import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.base.module.BaseResult;
import cn.shineiot.compontentpro.http.HttpService;
import rx.Subscriber;

/**
 * @author GF63
 */
public class MainPresenter extends BasePresenter<MainView> {
	public void loginOut(){
		addSubscription(HttpService.http.loginOut(), new Subscriber<BaseResult>() {
			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable e) {
				mView.showError(e.getMessage());
			}

			@Override
			public void onNext(BaseResult result) {
				if(result.getErrorCode() == 0){
					mView.succeessLoginOut();
				}else{
					mView.showError(result.getErrorMsg());
				}
			}
		});
	}

}
