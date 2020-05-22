package cn.shineiot.compontentpro.ui.ask;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import butterknife.BindView;
import cn.shineiot.base.ARouterPath;
import cn.shineiot.base.bean.Collect;
import cn.shineiot.base.module.BaseMvpActivity;
import cn.shineiot.base.utils.ToastUtils;
import cn.shineiot.compontentpro.R;

/**
 * @author GF63
 * 每日一问
 */
@Route(path = "/app/AskDayActivity")
public class AskDayActivity extends BaseMvpActivity<AskDayView,AskDayPresenter> implements AskDayView,
		SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {

	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.ask_recyclerView)
	RecyclerView recyclerView;
	@BindView(R.id.ask_swip)
	SwipeRefreshLayout refreshLayout;

	private AskAdapter adapter;
	private int page =0;
	private int[] colors = {R.color.black,R.color.red,R.color.yellow,R.color.blue};

	@Override
	protected int provideLayoutId() {
		return R.layout.activity_ask_day;
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		setupToolbar_center(toolbar,"每日一问");

		refreshLayout.setColorSchemeColors(colors);
		refreshLayout.setOnRefreshListener(this);

		adapter = new AskAdapter(R.layout.item_ask_day);
		recyclerView.setAdapter(adapter);
		adapter.setEnableLoadMore(true);
		adapter.setOnItemClickListener(this);
		adapter.setOnLoadMoreListener(this,recyclerView);
		presenter.getData(page);
	}

	@Override
	public AskDayPresenter initPresenter() {
		return new AskDayPresenter();
	}

	@Override
	public void successData(Collect collect) {
		adapter.addData(collect.getDatas());
		if(refreshLayout.isRefreshing()){
			refreshLayout.setRefreshing(false);
		}else {
			if (collect.getCurPage() == collect.getPageCount()) {
				adapter.loadMoreEnd();
			} else {
				adapter.loadMoreComplete();
			}
		}
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
	public void onRefresh() {
		page = 0;
		presenter.getData(page);
	}

	@Override
	public void onLoadMoreRequested() {
		page++;
		presenter.getData(page);
	}

	@Override
	public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
		Collect.DatasBean datasBean = (Collect.DatasBean) adapter.getItem(position);
		String url = datasBean.getLink();
		String title = datasBean.getTitle();
		int id = datasBean.getId();
		ARouter.getInstance().build(ARouterPath.WEB_VIEW_ACTIVITY).withString("url", url).withString("title", title).withInt("id",id).navigation();

	}
}
