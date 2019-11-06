package cn.shineiot.navigation.navigationfragment;

import cn.shineiot.base.module.BaseListResult;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.navigation.bean.Navigation;
import cn.shineiot.navigation.http.HttpService;
import rx.Subscriber;

/**
 * @author GF63
 */
public class NavigationPresenter extends BasePresenter<NavigationView> {
	public void getNavigationData() {
		addSubscription(HttpService.http.getNavigationData(), new Subscriber<BaseListResult<Navigation>>() {
			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable e) {
				mView.showError(e.getMessage());
			}

			@Override
			public void onNext(BaseListResult<Navigation> result) {
				if(result.getErrorCode() == 0){
					mView.successData(result.getData());
				}else{
					mView.showError(result.getErrorMsg());
				}
			}
		});
	}
}
