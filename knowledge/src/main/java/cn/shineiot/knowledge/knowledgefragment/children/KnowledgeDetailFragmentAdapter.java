package cn.shineiot.knowledge.knowledgefragment.children;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

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
		helper.setText(R.id.item_knowledge_detail_title, item.getTitle())
				.setText(R.id.item_knowledge_detail_day, item.getNiceDate());
	}
}
