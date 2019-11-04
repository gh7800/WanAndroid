package cn.shineiot.blog.blogfragment;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.shineiot.base.utils.LogUtil;
import cn.shineiot.blog.R;
import cn.shineiot.blog.bean.WxPublic;

/**
 * @author GF63
 * 微信公众号
 */
public class WxPublicAdapter extends BaseQuickAdapter<WxPublic, BaseViewHolder> {

	private int currentPosition = 0;
	public WxPublicAdapter(int layoutResId) {
		super(layoutResId);
	}

	public void setPosition(int position){
		currentPosition = position;
	}

	@Override
	protected void convert(@NonNull BaseViewHolder helper, WxPublic item) {
		int position = helper.getAdapterPosition();
		TextView textView = helper.getView(R.id.item_wx_public_box);
		textView.setText(item.getName());
		if (currentPosition == position) {
			textView.setSelected(true);
		}else{
			textView.setSelected(false);
		}

	}
}
