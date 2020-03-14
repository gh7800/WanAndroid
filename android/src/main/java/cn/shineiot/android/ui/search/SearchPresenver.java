package cn.shineiot.android.ui.search;

import cn.shineiot.android.bean.AndroidNews;
import cn.shineiot.android.http.HttpService;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.base.module.BaseResult;
import rx.Subscriber;

/**
 * @author GF63
 */
public class SearchPresenver extends BasePresenter<SearchView> {

	public void seacrchData(String key,int page){
		addSubscription(HttpService.HTTP.searchData(key, page), new Subscriber<BaseResult<AndroidNews>>() {
			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable e) {
				mView.showError(e.getMessage());
			}

			@Override
			public void onNext(BaseResult<AndroidNews> result) {
				if(result.getErrorCode() == 0){
					mView.successData(result.getData());
				}else{
					mView.showError(result.getErrorMsg());
				}
			}
		});
	}
}
