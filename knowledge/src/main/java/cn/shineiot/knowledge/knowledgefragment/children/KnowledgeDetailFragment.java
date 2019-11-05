package cn.shineiot.knowledge.knowledgefragment.children;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import cn.shineiot.base.ARouterPath;
import cn.shineiot.base.module.BaseMvpFragment;
import cn.shineiot.knowledge.R;
import cn.shineiot.knowledge.R2;
import cn.shineiot.knowledge.bean.KnowledgeDetail;

/**
 * @author GF63
 * 知识体系文章列表
 */
public class KnowledgeDetailFragment extends BaseMvpFragment<KnowledgeDetailView,KnowledgeDetailPresenter> implements KnowledgeDetailView{
	@BindView(R2.id.recyclerView_DetailKnowledge)
	RecyclerView recyclerView;

	private int page = 0;
	private KnowledgeDetailFragmentAdapter adapter;

	public static KnowledgeDetailFragment newInstace(int cid){
		KnowledgeDetailFragment knowledgeDetailFragment = new KnowledgeDetailFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("cid",cid);
		knowledgeDetailFragment.setArguments(bundle);
		return knowledgeDetailFragment;
	}
	@Override
	public void initViews(View view) {
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
		adapter = new KnowledgeDetailFragmentAdapter(R.layout.item_knowledge_detail);
		recyclerView.setAdapter(adapter);

		int cid = getArguments().getInt("cid");
		presenter.getKnowledgeDetail(cid,page);

		addOnItemClickListener();
	}

	private void addOnItemClickListener() {
		adapter.setOnItemClickListener((adapter, view, position) -> {
			KnowledgeDetail.Children children = (KnowledgeDetail.Children) adapter.getData().get(position);
			String title = children.getTitle();
			String url = children.getLink();
			ARouter.getInstance().build(ARouterPath.WEB_VIEW_ACTIVITY)
					.withString("title",title)
					.withString("url",url)
					.navigation();
		});

		adapter.setOnItemChildClickListener((adapter, view, position) -> {

		});
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_knowledge_detail;
	}

	@Override
	public KnowledgeDetailPresenter initPresenter() {
		return new KnowledgeDetailPresenter();
	}

	@Override
	public void successData(List<KnowledgeDetail.Children> list,int page) {
		adapter.setNewData(list);
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
