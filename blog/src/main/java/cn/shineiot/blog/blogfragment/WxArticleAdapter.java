package cn.shineiot.blog.blogfragment;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.shineiot.base.utils.StringUtils;
import cn.shineiot.blog.R;
import cn.shineiot.blog.bean.WxArticle;

/**
 * @author GF63
 * 微信历史文章
 */
public class WxArticleAdapter extends BaseQuickAdapter<WxArticle, BaseViewHolder> {
	public WxArticleAdapter(int layoutResId) {
		super(layoutResId);
	}

	@Override
	protected void convert(@NonNull BaseViewHolder helper, WxArticle item) {
		String title = StringUtils.delHtmlTags(item.getTitle());
		helper.setText(R.id.blog_title, title)
				.setText(R.id.blog_time, item.getNiceDate())
				.addOnClickListener(R.id.blog_checkBox);
	}
}
