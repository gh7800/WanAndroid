package cn.shineiot.android.androidfragment;

import cn.shineiot.android.bean.AndroidNews;
import cn.shineiot.android.bean.Banner;
import cn.shineiot.android.http.HttpService;
import cn.shineiot.base.module.BaseListResult;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.base.module.BaseResult;
import cn.shineiot.base.utils.LogUtil;
import cn.shineiot.base.utils.ToastUtils;
import rx.Subscriber;

/**
 * @author GF63
 */
public class AndroidPresenter extends BasePresenter<AndroidView> {
		public void getBannerData(){
			addSubscription(HttpService.HTTP.getBanner(), new Subscriber<BaseListResult<Banner>>() {

				@Override
				public void onStart() {
					super.onStart();
					mView.hideLoading();
				}

				@Override
				public void onCompleted() {
					mView.hideLoading();
				}

				@Override
				public void onError(Throwable e) {
					LogUtil.e("error---"+e.getMessage());
					mView.hideLoading();
					mView.showError(e.getMessage());
				}

				@Override
				public void onNext(BaseListResult<Banner> result) {
					if(result.getErrorCode() == 0){
						mView.successBannerData(result.getData());
					}else{
						mView.showError(result.getErrorMsg());
					}
				}
			});
		}

		public void getAndroidNews(int pages){
			addSubscription(HttpService.HTTP.getAndroidNews(pages), new Subscriber<BaseResult<AndroidNews>>() {
				@Override
				public void onCompleted() {
					LogUtil.e("completed");
					mView.hideLoading();
				}

				@Override
				public void onError(Throwable e) {
					LogUtil.e("error---"+e.getMessage());
					mView.hideLoading();
					mView.showError(e.getMessage());
				}

				@Override
				public void onNext(BaseResult<AndroidNews> result) {
					if(result.getErrorCode() == 0){
						mView.successAndroidNews(result.getData().getDatas(),result.getData().getCurPage());
					}else{
						mView.showError(result.getErrorMsg());
					}
				}
			});
		}

		public void collect(int id,int position){
			addSubscription(HttpService.HTTP.collect(id), new Subscriber<BaseResult>() {
				@Override
				public void onCompleted() {

				}

				@Override
				public void onError(Throwable e) {
					mView.showError(e.getMessage());
					mView.faildCollect(position);
				}

				@Override
				public void onNext(BaseResult result) {
					if(result.getErrorCode() == 0){
						mView.successCollect(position);
					}else{
						mView.showError(result.getErrorMsg());
						mView.faildCollect(position);
					}
				}
			});
		}
}
