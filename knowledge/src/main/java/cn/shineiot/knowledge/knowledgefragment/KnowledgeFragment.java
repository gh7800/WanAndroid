package cn.shineiot.knowledge.knowledgefragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import cn.shineiot.base.module.BaseMvpFragment;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.knowledge.R;
import cn.shineiot.knowledge.R2;

/**
 * @author GF63
 * 知识体系
 */
@Route(path = "/knowledge/knowledgeFragment")
public class KnowledgeFragment extends BaseMvpFragment<KnowledgeView, KnowledgePresenter> implements KnowledgeView {

	@BindView(R2.id.knowledge_recyclerView)
	RecyclerView recyclerView;

	@Override
	public void initViews(View view) {

	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_knowledge;
	}

	@Override
	public KnowledgePresenter initPresenter() {
		return new KnowledgePresenter();
	}

	@Override
	public void succeessData() {

	}

	@Override
	public void showLoading(String msg) {

	}

	@Override
	public void hideLoading() {

	}

	@Override
	public void showError(String msg) {

	}
}
