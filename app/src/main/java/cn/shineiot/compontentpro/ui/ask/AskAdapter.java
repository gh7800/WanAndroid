package cn.shineiot.compontentpro.ui.ask;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.shineiot.base.bean.Collect;
import cn.shineiot.base.bean.Collect.DatasBean;
import cn.shineiot.compontentpro.R;

/**
 * @author GF63
 */
public class AskAdapter extends BaseQuickAdapter<Collect.DatasBean, BaseViewHolder> {

	public AskAdapter(int layoutResId) {
		super(layoutResId);
	}

	@Override
	protected void convert(@NonNull BaseViewHolder helper, DatasBean item) {
		helper.setText(R.id.ask_item_title, item.getTitle())
				.setText(R.id.ask_item_author, item.getAuthor());
	}
}
