package cn.shineiot.compontentpro.ui.ask;

import cn.shineiot.base.bean.Collect;
import cn.shineiot.base.http.HttpClient;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.base.module.BaseResult;
import cn.shineiot.base.utils.LogUtil;
import cn.shineiot.compontentpro.http.HttpService;
import rx.Subscriber;

/**
 * @author GF63
 */
public class AskDayPresenter extends BasePresenter<AskDayView> {

	public void getData(int page){
		addSubscription(HttpService.http.getWenDa(page), new Subscriber<BaseResult<Collect>>() {
			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable e) {
				mView.showError(e.getMessage());
			}

			@Override
			public void onNext(BaseResult<Collect> result) {
					if(result.getErrorCode() == 0){
						mView.successData(result.getData());
					}else{
						mView.showError(result.getErrorMsg());
					}
			}
		});
	}
}
