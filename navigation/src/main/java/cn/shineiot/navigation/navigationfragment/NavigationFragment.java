package cn.shineiot.navigation.navigationfragment;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.List;

import butterknife.BindView;
import cn.shineiot.base.module.BaseMvpFragment;
import cn.shineiot.base.utils.LogUtil;
import cn.shineiot.base.utils.ToastUtils;
import cn.shineiot.navigation.R;
import cn.shineiot.navigation.R2;
import cn.shineiot.navigation.bean.Navigation;

/**
 * @author GF63
 */
@Route(path = "/navigation/navigationFragment")
public class NavigationFragment extends BaseMvpFragment<NavigationView, NavigationPresenter> implements NavigationView{
	@BindView(R2.id.recyclerViewTab)
	RecyclerView recyclerViewTab;
	@BindView(R2.id.recyclerViewContent)
	RecyclerView recyclerViewContent;

	private NavigationTabAdapter tabAdapter;
	private NavigationContentAdapter contentAdapter;
	private int currentPosition = 0;
	private LinearLayoutManager linearLayoutManagerTab;
	private LinearLayoutManager linearLayoutManagerContent;

	private boolean isUp = false;//上滑
	private boolean isDown = false;//下滑
	private int firstCompletelyVisibleItemPosition = 0;//第一个完全显示的item
	private int lastCompletelyVisibleItemPosition = 0;//最后一个完全显示的item


	@Override
	public int getLayoutId() {
		return R.layout.fragment_navigation;
	}

	@Override
	public void initViews(View view) {
		recyclerViewTab.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
		recyclerViewTab.setItemAnimator(new DefaultItemAnimator());
		linearLayoutManagerTab = new LinearLayoutManager(mContext);
		recyclerViewTab.setLayoutManager(linearLayoutManagerTab);
		tabAdapter = new NavigationTabAdapter(R.layout.item_navigation_tab);
		recyclerViewTab.setAdapter(tabAdapter);

		recyclerViewContent.setItemAnimator(new DefaultItemAnimator());
		linearLayoutManagerContent = new LinearLayoutManager(mContext);
		recyclerViewContent.setLayoutManager(linearLayoutManagerContent);
		contentAdapter = new NavigationContentAdapter(R.layout.item_navigation_content);
		recyclerViewContent.setAdapter(contentAdapter);

		addOnItemClickListener();
		presenter.getNavigationData();
	}

	private void addOnItemClickListener() {
		tabAdapter.setOnItemClickListener((adapter, view, position) -> {
			if (currentPosition != position) {
				tabAdapter.setPosition(position);
				tabAdapter.notifyDataSetChanged();
				currentPosition = position;

				linearLayoutManagerContent.scrollToPositionWithOffset(position,0);//滚动到指定position并置顶
				linearLayoutManagerContent.setStackFromEnd(false);
			}
		});

		recyclerViewContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);
				int firstVisibleItemPosition = linearLayoutManagerContent.findFirstVisibleItemPosition();
				int lastVisibleItemPosition = linearLayoutManagerContent.findLastVisibleItemPosition();


				firstCompletelyVisibleItemPosition = linearLayoutManagerContent.findFirstCompletelyVisibleItemPosition();
				lastCompletelyVisibleItemPosition = linearLayoutManagerContent.findLastCompletelyVisibleItemPosition();

				//LogUtil.e("-------first/"+firstCompletelyVisibleItemPosition+"====="+firstVisibleItemPosition+"-------"+newState);
				//LogUtil.e("-------last/"+lastCompletelyVisibleItemPosition+"====="+lastVisibleItemPosition+"-------"+newState);

				if (isUp) {
					if (currentPosition != firstCompletelyVisibleItemPosition) {
						int position ;
						if(firstCompletelyVisibleItemPosition > 0){
							position = firstCompletelyVisibleItemPosition;
						}else{
							position = firstVisibleItemPosition;
						}
						tabAdapter.setPosition(position);
						tabAdapter.notifyDataSetChanged();
						currentPosition = position;
						recyclerViewTab.scrollToPosition(position);
					}
				} else if (isDown) {
					if (currentPosition != firstCompletelyVisibleItemPosition) {
						int position ;
						if(firstCompletelyVisibleItemPosition > 0){
							position = firstCompletelyVisibleItemPosition;
						}else{
							position = firstVisibleItemPosition;
						}
						tabAdapter.setPosition(position);
						tabAdapter.notifyDataSetChanged();
						currentPosition = position;
						recyclerViewTab.scrollToPosition(position);
					}
				}
			}

			@Override
			public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				if (dy > 0) {
					isUp = true;
					isDown = false;
				} else if (dy < 0) {
					isUp = false;
					isDown = true;
				}
			}
		});
	}

	@Override
	public NavigationPresenter initPresenter() {
		return new NavigationPresenter();
	}

	@Override
	public void successData(List<Navigation> list) {
			tabAdapter.setNewData(list);
			contentAdapter.setNewData(list);
			tabAdapter.setPosition(0);
			tabAdapter.notifyItemChanged(0);

	}

	@Override
	public void showLoading(String msg) {

	}

	@Override
	public void hideLoading() {

	}

	@Override
	public void showError(String msg) {
		ToastUtils.showErrorToast(msg);
	}


}
