package cn.shineiot.android.ui.articledetail;

import cn.shineiot.android.http.HttpService;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.base.module.BaseResult;
import cn.shineiot.base.utils.ToastUtils;
import rx.Subscriber;

/**
 * @author GF63
 */
public class ArticlePresenter extends BasePresenter<ArticleView> {
	public void collect(int id){
		addSubscription(HttpService.HTTP.collect(id), new Subscriber<BaseResult>() {
			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable e) {
				ToastUtils.showToast(e.getMessage());
			}

			@Override
			public void onNext(BaseResult result) {
				if(result.getErrorCode() == 0){
					ToastUtils.showToast("收藏成功");
				}else{
					ToastUtils.showToast(result.getErrorMsg());
				}
			}
		});
	}
}
