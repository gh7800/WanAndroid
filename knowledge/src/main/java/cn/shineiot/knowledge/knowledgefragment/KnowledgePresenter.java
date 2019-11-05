package cn.shineiot.knowledge.knowledgefragment;

import cn.shineiot.base.module.BaseListResult;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.knowledge.bean.Knowledge;
import cn.shineiot.knowledge.http.HttpService;
import rx.Subscriber;

/**
 * @author GF63
 */
public class KnowledgePresenter extends BasePresenter<KnowledgeView> {
	public void getKnowledgeList(){
		addSubscription(HttpService.http.getKnowledgeData(), new Subscriber<BaseListResult<Knowledge>>() {
			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable e) {
				mView.showError(e.getMessage());
			}

			@Override
			public void onNext(BaseListResult<Knowledge> result) {
				if(result.getErrorCode() == 0){
					mView.succeessData(result.getData());
				}else{
					mView.showError(result.getErrorMsg());
				}
			}
		});
	}
}
