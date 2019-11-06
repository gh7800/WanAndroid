package cn.shineiot.navigation.navigationfragment;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import cn.shineiot.base.ARouterPath;
import cn.shineiot.base.BaseApplication;
import cn.shineiot.navigation.R;
import cn.shineiot.navigation.bean.Navigation;

/**
 * @author GF63
 */
public class NavigationContentAdapter extends BaseQuickAdapter<Navigation, BaseViewHolder> {
	public NavigationContentAdapter(int layoutResId) {
		super(layoutResId);
	}

	@Override
	protected void convert(@NonNull BaseViewHolder helper, Navigation item) {
		helper.setText(R.id.item_navigation_content_tv,item.getName()+"-"+helper.getAdapterPosition());
		TagFlowLayout tagFlowLayout = helper.getView(R.id.itemNavigationTagLayout);

		tagFlowLayout.setAdapter(new TagAdapter<Navigation.Children>(item.getArticles()) {
			@Override
			public View getView(FlowLayout parent, int position, Navigation.Children children) {
				TextView textView = (TextView) mLayoutInflater.inflate(R.layout.layout_tag_tv,tagFlowLayout,false);
				textView.setText(children.getTitle());
				return textView;
			}
		});
		tagFlowLayout.setOnTagClickListener((view, position, parent) -> {
			List<Navigation.Children> childrenList = item.getArticles();
			String title = childrenList.get(position).getTitle();
			String url = childrenList.get(position).getLink();
			ARouter.getInstance().build(ARouterPath.WEB_VIEW_ACTIVITY).withString("title",title).withString("url",url).navigation();
			return true;
		});
	}
}
