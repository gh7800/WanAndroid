package cn.shineiot.android.ui.collect;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.maning.mndialoglibrary.MProgressDialog;

import java.util.List;

import butterknife.BindView;
import cn.shineiot.android.R;
import cn.shineiot.android.R2;
import cn.shineiot.base.ARouterPath;
import cn.shineiot.base.bean.Collect;
import cn.shineiot.base.module.BaseMvpActivity;
import cn.shineiot.base.utils.ToastUtils;

/**
 * @author GF63
 * 我的收藏
 */
@Route(path = ARouterPath.COLLECT_ACTIVITY)
public class CollectActivity extends BaseMvpActivity<CollectView, CollectPresenter> implements CollectView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
	@BindView(R2.id.toolbar)
	Toolbar toolbar;
	@BindView(R2.id.collect_recyclerView)
	RecyclerView recyclerView;

	private CollectAdapter adapter;

	@Override
	protected int provideLayoutId() {
		return R.layout.activity_collect;
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		setupToolbar_center(toolbar, "我的收藏");
		recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		adapter = new CollectAdapter(R.layout.item_androidnews);
		recyclerView.setAdapter(adapter);
		adapter.setOnItemChildClickListener(this);
		adapter.setOnItemClickListener(this);
		View layout = LayoutInflater.from(mContext).inflate(R.layout.layout_nmpty, null);
		Button button = layout.findViewById(R.id.button);
		button.setOnClickListener((view) -> {
			presenter.getMyCollect();
		});
		adapter.setEmptyView(layout);

		presenter.getMyCollect();
	}

	@Override
	public CollectPresenter initPresenter() {
		return new CollectPresenter();
	}

	@Override
	public void successData(List<Collect.DatasBean> list) {
		if (!list.isEmpty()) {
			adapter.setNewData(list);
		}
	}

	@Override
	public void successUnCollet(int position, boolean isCollect) {
		if (isCollect) {
			adapter.remove(position);
		} else {
			adapter.notifyItemChanged(position);
		}
	}

	@Override
	public void showLoading(String msg) {
		MProgressDialog.showProgress(mContext);
	}

	@Override
	public void hideLoading() {
		recyclerView.postDelayed(() -> {
			MProgressDialog.dismissProgress();
		}, 1000);
	}

	@Override
	public void showError(String msg) {
		ToastUtils.showErrorToast(msg);
		hideLoading();
	}

	@Override
	public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
		Collect.DatasBean datasBean = (Collect.DatasBean) adapter.getItem(position);
		presenter.unCollect(datasBean.getOriginId(), position);
	}

	@Override
	public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
		Collect.DatasBean datasBean = (Collect.DatasBean) adapter.getItem(position);
		String title = datasBean.getTitle();
		String url = datasBean.getLink();
		ARouter.getInstance().build(ARouterPath.WEB_VIEW_ACTIVITY).withString("title", title).withString("url", url).navigation();

	}
}
