package cn.shineiot.knowledge.knowledgefragment.children;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.shineiot.base.utils.StringUtils;
import cn.shineiot.knowledge.R;
import cn.shineiot.knowledge.bean.KnowledgeDetail;

/**
 * @author GF63
 */
public class KnowledgeDetailFragmentAdapter extends BaseQuickAdapter<KnowledgeDetail.Children, BaseViewHolder> {
	public KnowledgeDetailFragmentAdapter(int layoutResId) {
		super(layoutResId);
	}

	@Override
	protected void convert(@NonNull BaseViewHolder helper, KnowledgeDetail.Children item) {
		String title = StringUtils.delHtmlTags(item.getTitle());
		helper.setText(R.id.item_knowledge_detail_title, title)
				.setText(R.id.item_knowledge_detail_day, item.getNiceDate());
	}
}
