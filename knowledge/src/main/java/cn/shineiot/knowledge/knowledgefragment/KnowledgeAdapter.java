package cn.shineiot.knowledge.knowledgefragment;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.shineiot.knowledge.bean.Knowledge;

/**
 * @author GF63
 */
public class KnowledgeAdapter extends BaseQuickAdapter<Knowledge,BaseViewHolder> {
	public KnowledgeAdapter(int layoutResId) {
		super(layoutResId);
	}

	@Override
	protected void convert(@NonNull BaseViewHolder helper, Knowledge item) {

	}
}
