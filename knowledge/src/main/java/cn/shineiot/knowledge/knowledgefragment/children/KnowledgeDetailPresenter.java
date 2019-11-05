package cn.shineiot.knowledge.knowledgefragment.children;

import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.base.module.BaseResult;
import cn.shineiot.knowledge.bean.KnowledgeDetail;
import cn.shineiot.knowledge.http.HttpService;
import rx.Subscriber;

/**
 * @author GF63
 */
public class KnowledgeDetailPresenter extends BasePresenter<KnowledgeDetailView> {
	public void getKnowledgeDetail(int cid,int page){
		addSubscription(HttpService.http.getKnowledgeDetail(page, cid), new Subscriber<BaseResult<KnowledgeDetail>>() {
			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable e) {
				mView.showError(e.getMessage());
			}

			@Override
			public void onNext(BaseResult<KnowledgeDetail> result) {
				if(result.getErrorCode() == 0){
					mView.successData(result.getData().getDatas(),result.getData().getCurPage());
				}else{
					mView.showError(result.getErrorMsg());
				}
			}
		});
	}
}
