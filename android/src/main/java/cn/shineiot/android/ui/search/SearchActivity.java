package cn.shineiot.android.ui.search;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.maning.mndialoglibrary.MProgressDialog;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.BindView;
import cn.shineiot.android.R;
import cn.shineiot.android.R2;
import cn.shineiot.android.androidfragment.AndroidNewsAdapter;
import cn.shineiot.android.bean.AndroidNews;
import cn.shineiot.android.bean.SearchEntity;
import cn.shineiot.base.ARouterPath;
import cn.shineiot.base.module.BaseMvpActivity;
import cn.shineiot.base.utils.LogUtil;
import cn.shineiot.base.utils.ToastUtils;

/**
 * @author GF63
 * 搜索
 */
@Route(path = ARouterPath.SEARCH_ACTIVITY)
public class SearchActivity extends BaseMvpActivity<SearchView,SearchPresenver> implements SearchView, BaseQuickAdapter.OnItemClickListener,BaseQuickAdapter.RequestLoadMoreListener {
	@BindView(R2.id.search_editText)
	EditText editText;
	@BindView(R2.id.toolbar)
	Toolbar toolbar;
	@BindView(R2.id.search_tagFlowLayout)
	TagFlowLayout tagFlowLayout;
	@BindView(R2.id.search_recyclerView)
	RecyclerView recyclerView;

	private String[] tag = {"Mvvm","Mvp","rxJava","AndroidX","Glide","EventBus"};
	private AndroidNewsAdapter adapter;
	private int page = 0;
	private String key = null;

	@Override
	protected int provideLayoutId() {
		return R.layout.activity_search;
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		setupToolbar_center(toolbar,"搜索");
		recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		adapter = new AndroidNewsAdapter(R.layout.item_androidnews);
		recyclerView.setAdapter(adapter);
		adapter.setOnItemClickListener(this);
		adapter.setOnLoadMoreListener(this,recyclerView);

		View view1 = LayoutInflater.from(mContext).inflate(R.layout.layout_nmpty, null);
		Button button = view1.findViewById(R.id.button);
		button.setVisibility(View.GONE);
		adapter.setEmptyView(view1);

		editText.setOnEditorActionListener((v, actionId, event) -> {
			if(actionId == EditorInfo.IME_ACTION_SEARCH){
				if(TextUtils.isEmpty(editText.getText())){
					ToastUtils.showToast("请输入关键字");
					return false;
				}
				showLoading("");
				key = editText.getText().toString().trim();
				presenter.seacrchData(key,page);
			}
			return true;
		});
	}

	@Override
	public SearchPresenver initPresenter() {
		return new SearchPresenver();
	}

	@Override
	public void successData(AndroidNews androidNews) {
		editText.postDelayed(()->{
			hideLoading();
			page = androidNews.getCurPage()-1;
			if(page == 0) {
				adapter.setNewData(androidNews.getDatas());
			}else{
				adapter.addData(androidNews.getDatas());
			}

			if(androidNews.getCurPage() == androidNews.getPageCount()){
				adapter.loadMoreEnd();
			}else{
				adapter.loadMoreComplete();
			}
		},1000);

	}

	@Override
	public void showLoading(String msg) {
		MProgressDialog.showProgress(mContext);
	}

	@Override
	public void hideLoading() {
		MProgressDialog.dismissProgress();
	}

	@Override
	public void showError(String msg) {
		hideLoading();
		ToastUtils.showToast(msg);
	}

	@Override
	public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
		AndroidNews.News news = (AndroidNews.News) adapter.getItem(position);
		String title = news.getTitle();
		String url = news.getLink();
		int id = news.getId();
		ARouter.getInstance().build(ARouterPath.WEB_VIEW_ACTIVITY).withString("title",title).withString("url",url).withInt("id",id).navigation();
	}

	@Override
	public void onLoadMoreRequested() {
		page++;
		presenter.seacrchData(key,page);
	}
}
