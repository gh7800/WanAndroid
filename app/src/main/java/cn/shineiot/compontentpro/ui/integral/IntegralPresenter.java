package cn.shineiot.compontentpro.ui.integral;

import cn.shineiot.base.bean.Coin;
import cn.shineiot.base.bean.Integral;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.base.module.BaseResult;
import cn.shineiot.compontentpro.http.HttpService;
import rx.Subscriber;

/**
 * @author GF63
 */
public class IntegralPresenter extends BasePresenter<IntegralView> {
	public void getMyCoin(){
		addSubscription(HttpService.http.getMyCoin(), new Subscriber<BaseResult<Coin>>() {
			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable e) {
				mView.showError(e.getMessage());
			}

			@Override
			public void onNext(BaseResult<Coin> result) {
				if(result.getErrorCode() == 0){
					mView.succeessMyCoin(result.getData());
				}else{
					mView.showError(result.getErrorMsg());
				}
			}
		});
	}

	public void getListCoin(){
		addSubscription(HttpService.http.getListCoin(), new Subscriber<BaseResult<Integral>>() {
			@Override
			public void onCompleted() {
				mView.hideLoading();
			}

			@Override
			public void onError(Throwable e) {
				mView.showError(e.getMessage());
			}

			@Override
			public void onNext(BaseResult<Integral> result) {
				if(result.getErrorCode() == 0){
					mView.successListCoin(result.getData());
				}else{
					mView.showError(result.getErrorMsg());
				}
			}
		});
	}

}
