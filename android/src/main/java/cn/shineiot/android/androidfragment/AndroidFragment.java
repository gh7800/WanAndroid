package cn.shineiot.android.androidfragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.shineiot.android.R;
import cn.shineiot.android.R2;
import cn.shineiot.android.bean.AndroidNews;
import cn.shineiot.android.utils.GlideImageLoader;
import cn.shineiot.base.ARouterPath;
import cn.shineiot.base.module.BaseMvpFragment;
import cn.shineiot.base.utils.GreenDaoHelper;
import cn.shineiot.base.utils.LogUtil;
import cn.shineiot.base.utils.ToastUtils;

/**
 * @author GF63
 * android news
 */
@Route(path = "/android/androidFragment")
public class AndroidFragment extends BaseMvpFragment<AndroidView, AndroidPresenter> implements AndroidView, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
	@BindView(R2.id.recyclerView)
	RecyclerView recyclerView;
	@BindView(R2.id.swipeRefreshLayout)
	SwipeRefreshLayout swipeRefreshLayout;

	private Banner banner;

	private AndroidNewsAdapter adapter;
	private int page = 0;//当前页
	private boolean isRefresh = false;//刷新
	private boolean isLoader = false;//上拉加载
	private int size = 0;
	private List<cn.shineiot.android.bean.Banner> bannerList = new ArrayList<>();

	@Override
	public int getLayoutId() {
		return R.layout.fragment_android;
	}

	@Override
	public void initViews(View view) {
		swipeRefreshLayout.setDistanceToTriggerSync(200);
		swipeRefreshLayout.setOnRefreshListener(this);

		View layout = LayoutInflater.from(mContext).inflate(R.layout.android_banner, null);
		banner = layout.findViewById(R.id.banner);
		banner.setImageLoader(new GlideImageLoader());
		banner.setIndicatorGravity(BannerConfig.CENTER);
		banner.setDelayTime(2000);
		banner.setOnBannerListener((position) -> {
			String url = bannerList.get(position).getUrl();
			String title = bannerList.get(position).getTitle();
			ARouter.getInstance().build(ARouterPath.WEB_VIEW_ACTIVITY).withString("title", title).withString("url", url).navigation();
		});

		recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		adapter = new AndroidNewsAdapter(R.layout.item_androidnews);
		adapter.setHeaderView(layout);
		adapter.setOnItemClickListener(this);
		adapter.setOnItemChildClickListener(this);
		adapter.setOnLoadMoreListener(this, recyclerView);
		recyclerView.setAdapter(adapter);

		swipeRefreshLayout.setRefreshing(true);
		isRefresh = true;
		presenter.getBannerData();
		presenter.getAndroidNews(page);


	}

	@Override
	public AndroidPresenter initPresenter() {
		return new AndroidPresenter();
	}

	@Override
	public void onStart() {
		super.onStart();
		banner.startAutoPlay();
	}

	@Override
	public void onStop() {
		super.onStop();
		banner.stopAutoPlay();
		if (isRefresh) {
			swipeRefreshLayout.setRefreshing(false);
		} else if (isLoader) {
			adapter.loadMoreEnd();
		}
	}

	@Override
	public void successBannerData(List<cn.shineiot.android.bean.Banner> list) {
		bannerList = list;
		List<String> stringList = new ArrayList<>();
		for (cn.shineiot.android.bean.Banner banner : list) {
			stringList.add(banner.getImagePath());
		}
		banner.setImages(stringList);
		banner.start();
	}

	@Override
	public void successAndroidNews(List<AndroidNews.News> androidNewsList, int page) {

		if (null != androidNewsList && androidNewsList.size() > 0) {
			this.page = page;
			LogUtil.e("page----------------" + page);
			if (page == 1) {
				adapter.setNewData(androidNewsList);
				size = androidNewsList.size();
			} else if (isRefresh) {
				adapter.replaceData(androidNewsList);
				size = androidNewsList.size();
			} else {
				adapter.addData(size, androidNewsList);
				size += androidNewsList.size();
			}
		}
	}

	@Override
	public void successCollect(int position) {
		adapter.getData().get(position).setCollect(true);
//		adapter.notifyItemChanged(position);
	}

	@Override
	public void faildCollect(int position) {
		adapter.getData().get(position).setCollect(false);
//		adapter.notifyItemChanged(position);
	}

	@Override
	public void showLoading(String msg) {
	}

	@Override
	public void hideLoading() {
		recyclerView.postDelayed(this::endRefreshAndLoader, 1000);
	}

	/**
	 * 结束加载
	 */
	private void endRefreshAndLoader() {
		if (isRefresh) {
			isRefresh = false;
			swipeRefreshLayout.setRefreshing(false);
		} else if (isLoader) {
			isLoader = false;
			adapter.loadMoreComplete();
		}
	}

	@Override
	public void showError(String msg) {
		endRefreshAndLoader();
		ToastUtils.showErrorToast(msg);
	}

	@Override
	public void onLoadMoreRequested() {
		if (!isRefresh && !isLoader) {
			isLoader = true;
			presenter.getAndroidNews(page);
		}
	}

	@Override
	public void onRefresh() {
		isRefresh = true;
		presenter.getAndroidNews(0);
	}

	@Override
	public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
		AndroidNews.News news = this.adapter.getData().get(position);
		String url = news.getLink();
		String title = news.getTitle();
		ARouter.getInstance().build(ARouterPath.WEB_VIEW_ACTIVITY).withString("url", url).withString("title", title).navigation();
	}

	@Override
	public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
		List<AndroidNews.News> list = adapter.getData();
		AndroidNews.News news = list.get(position);
		boolean isCollect = news.isCollect();
		if (view.getId() == R.id.new_checkBox) {
			if(!isCollect) {
				presenter.collect(news.getId(), position);
			}else{
				//todo
			}
		}
	}
}
