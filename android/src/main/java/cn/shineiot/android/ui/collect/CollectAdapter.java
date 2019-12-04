package cn.shineiot.android.ui.collect;

import android.support.annotation.NonNull;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import cn.shineiot.android.R;
import cn.shineiot.base.bean.Collect;
import cn.shineiot.base.utils.StringUtils;

/**
 * @author GF63
 */
public class CollectAdapter extends BaseQuickAdapter<Collect.DatasBean, BaseViewHolder> {

	public CollectAdapter(int layoutResId) {
		super(layoutResId);
	}

	@Override
	protected void convert(@NonNull BaseViewHolder helper, Collect.DatasBean item) {
		helper.setText(R.id.new_title, StringUtils.delHtmlTags(item.getTitle()))
				.setText(R.id.new_niceDate, item.getNiceDate())
				.setText(R.id.new_chapterName, item.getChapterName())
		.addOnClickListener(R.id.new_checkBox);
	}
}
