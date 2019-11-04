package cn.shineiot.blog.blogfragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import cn.shineiot.base.ARouterPath;
import cn.shineiot.base.module.BaseMvpFragment;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.base.utils.LogUtil;
import cn.shineiot.base.utils.ToastUtils;
import cn.shineiot.blog.R;
import cn.shineiot.blog.R2;
import cn.shineiot.blog.bean.WxArticle;
import cn.shineiot.blog.bean.WxPublic;
import cn.shineiot.blog.bean.WxPublicData;

/**
 * @author GF63
 */
@Route(path = "/blog/blogFragment")
public class BlogFragment extends BaseMvpFragment<BlogView, BlogPresenter> implements BlogView {
	@BindView(R2.id.blog_recyclerView_public)
	RecyclerView recyclerViewPublic;
	@BindView(R2.id.blog_recyclerView_list)
	RecyclerView recyclerViewArticle;

	private WxPublicAdapter adapterPublic;
	private WxArticleAdapter adapterArticle;

	private int currentPosition = 0;//adapter_public 当前position
	private int id = 0;//当前公众号的id
	private int page = 0;//第几页

	@Override
	public void initViews(View view) {
		recyclerViewPublic.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
		recyclerViewPublic.setItemAnimator(new DefaultItemAnimator());
		adapterPublic = new WxPublicAdapter(R.layout.item_wx_public);
		recyclerViewPublic.setAdapter(adapterPublic);

		recyclerViewArticle.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
		recyclerViewArticle.setItemAnimator(new DefaultItemAnimator());
		adapterArticle = new WxArticleAdapter(R.layout.item_wx_article);
		adapterArticle.setEnableLoadMore(true);
		adapterArticle.setPreLoadNumber(3);
		recyclerViewArticle.setAdapter(adapterArticle);

		addOnItemClick();

		presenter.getWxPublic();
	}

	private void addOnItemClick() {
		adapterPublic.setOnItemClickListener((adapter, view, position) -> {

			WxPublic wxPublic = (WxPublic) adapter.getItem(position);
			id = wxPublic.getId();

			if (position != currentPosition) {
				presenter.getWxArticleData(wxPublic.getId(), 0);
				adapterPublic.setPosition(position);

				adapterPublic.notifyItemChanged(currentPosition);
				adapterPublic.notifyItemChanged(position);

				currentPosition = position;
			}
		});

		adapterArticle.setOnItemClickListener((adapter, view, position) -> {
			WxArticle wxArticle = (WxArticle) adapter.getItem(position);
			String title = wxArticle.getTitle();
			String url = wxArticle.getLink();
			ARouter.getInstance().build(ARouterPath.WEB_VIEW_ACTIVITY).withString("title",title).withString("url",url).navigation();
		});

		adapterArticle.setOnLoadMoreListener(() -> {
			page++;
			presenter.getWxArticleData(id, page);
		}, recyclerViewArticle);
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_blog;
	}

	@Override
	public BlogPresenter initPresenter() {
		return new BlogPresenter();
	}

	@Override
	public void successWxPublic(List<WxPublic> wxPublicList) {
		if (!wxPublicList.isEmpty()) {
			adapterPublic.setNewData(wxPublicList);
			adapterPublic.setPosition(0);

			WxPublic wxPublic = wxPublicList.get(0);
			id = wxPublic.getId();
			presenter.getWxArticleData(wxPublic.getId(), 0);
		}
	}

	@Override
	public void successWxArticle(WxPublicData wxPublicData) {
		int page = wxPublicData.getCurPage();
		List<WxArticle> list = wxPublicData.getDatas();
		if (!list.isEmpty()) {
			if (page == 0) {
				adapterArticle.setNewData(list);
			} else {
				adapterArticle.addData(list);
			}
		}
	}

	@Override
	public void showLoading(String msg) {

	}

	@Override
	public void hideLoading() {
		if (adapterArticle.isLoadMoreEnable()) {
			adapterArticle.loadMoreComplete();
		}
	}

	@Override
	public void showError(String msg) {
		ToastUtils.showErrorToast(msg);
	}
}
