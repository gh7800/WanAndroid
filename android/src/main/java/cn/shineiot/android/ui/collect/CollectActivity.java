package cn.shineiot.android.ui.collect;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import cn.shineiot.android.R;
import cn.shineiot.android.R2;
import cn.shineiot.base.ARouterPath;
import cn.shineiot.base.module.BaseMvpActivity;

/**
 * @author GF63
 * 我的收藏
 */
@Route(path = ARouterPath.COLLECT_ACTIVITY)
public class CollectActivity extends BaseMvpActivity<CollectView,CollectPresenter> implements CollectView{
	@BindView(R2.id.toolbar)
	Toolbar toolbar;
	@BindView(R2.id.collect_recyclerView)
	RecyclerView recyclerView;

	@Override
	protected int provideLayoutId() {
		return R.layout.activity_collect;
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		setupToolbar_center(toolbar,"我的收藏");
	}

	@Override
	public CollectPresenter initPresenter() {
		return new CollectPresenter();
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
