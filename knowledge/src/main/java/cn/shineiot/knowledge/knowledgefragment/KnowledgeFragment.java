package cn.shineiot.knowledge.knowledgefragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.shineiot.base.ARouterPath;
import cn.shineiot.base.module.BaseMvpFragment;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.knowledge.R;
import cn.shineiot.knowledge.R2;
import cn.shineiot.knowledge.bean.Knowledge;

/**
 * @author GF63
 * 知识体系
 */
@Route(path = "/knowledge/knowledgeFragment")
public class KnowledgeFragment extends BaseMvpFragment<KnowledgeView, KnowledgePresenter> implements KnowledgeView , BaseQuickAdapter.OnItemClickListener {

	@BindView(R2.id.knowledge_recyclerView)
	RecyclerView recyclerView;

	private KnowledgeAdapter adapter;

	@Override
	public void initViews(View view) {
		recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		adapter = new KnowledgeAdapter(R.layout.item_knowledge);
		recyclerView.setAdapter(adapter);
		adapter.setOnItemClickListener(this);

		presenter.getKnowledgeList();
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
	public void succeessData(List<Knowledge> list) {
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

	@Override
	public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
		Knowledge knowledge = (Knowledge) adapter.getItem(position);
		ArrayList<Integer> list = new ArrayList<>(knowledge.getChildren().size());
		ArrayList<String> stringList = new ArrayList<>(knowledge.getChildren().size());
		String title = knowledge.getName();
		for(Knowledge.ChildrenBean childrenBean : knowledge.getChildren()){
			list.add(childrenBean.getId());
			stringList.add(childrenBean.getName());
		}
		ARouter.getInstance().build(ARouterPath.KNOWLEDGE_ACTIVITY).withString("title",title).withIntegerArrayList("cid", list).withStringArrayList("tabName",stringList).navigation();
	}
}
