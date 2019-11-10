package cn.shineiot.android.ui.collect;

import cn.shineiot.android.http.HttpService;
import cn.shineiot.base.bean.Collect;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.base.module.BaseResult;
import retrofit2.http.POST;
import rx.Subscriber;

/**
 * @author GF63
 */
public class CollectPresenter extends BasePresenter<CollectView> {
	public void getMyCollect(){
		addSubscription(HttpService.HTTP.getMyCollect(), new Subscriber<BaseResult<Collect>>() {

			@Override
			public void onStart() {
				super.onStart();
				mView.showLoading("");
			}

			@Override
			public void onCompleted() {
				mView.hideLoading();
			}

			@Override
			public void onError(Throwable e) {
				mView.showError(e.getMessage());
			}

			@Override
			public void onNext(BaseResult<Collect> result) {
				if(result.getErrorCode() == 0){
					mView.successData(result.getData().getDatas());
				}else{
					mView.showError(result.getErrorMsg());
				}
			}
		});
	}

	public void unCollect(int id,int position){
		addSubscription(HttpService.HTTP.unCollect(id), new Subscriber<BaseResult>() {
			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable e) {
				mView.showError(e.getMessage());
				mView.successUnCollet(position,false);
			}

			@Override
			public void onNext(BaseResult result) {
				if(result.getErrorCode() == 0){
					mView.successUnCollet(position,true);
				}else{
					mView.showError(result.getErrorMsg());
					mView.successUnCollet(position,false);
				}
			}
		});
	}
}
