package cn.shineiot.blog.blogfragment;

import cn.shineiot.base.module.BaseListResult;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.base.module.BaseResult;
import cn.shineiot.blog.bean.WxPublic;
import cn.shineiot.blog.bean.WxPublicData;
import cn.shineiot.blog.http.HttpService;
import rx.Subscriber;

/**
 * @author GF63
 */
public class BlogPresenter extends BasePresenter<BlogView> {
	public void getWxPublic() {
		addSubscription(HttpService.http.getWxPublic(), new Subscriber<BaseListResult<WxPublic>>() {
			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable e) {
				mView.showError(e.getMessage());
			}

			@Override
			public void onNext(BaseListResult<WxPublic> result) {
				if(result.getErrorCode() == 0){
					mView.successWxPublic(result.getData());
				}else{
					mView.showError(result.getErrorMsg());
				}
			}
		});
	}

	public void getWxArticleData(int id,int page){
		addSubscription(HttpService.http.getWxArticle(id, page), new Subscriber<BaseResult<WxPublicData>>() {
			@Override
			public void onCompleted() {
//				mView.hideLoading();
			}

			@Override
			public void onError(Throwable e) {
				mView.showError(e.getMessage());
			}

			@Override
			public void onNext(BaseResult<WxPublicData> result) {
				if(result.getErrorCode() == 0){
					mView.successWxArticle(result.getData());
				}else{
					mView.showError(result.getErrorMsg());
				}
			}
		});
	}
}
