package cn.shineiot.navigation.navigationfragment;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.shineiot.navigation.R;
import cn.shineiot.navigation.bean.Navigation;

/**
 * @author GF63
 */
public class NavigationTabAdapter extends BaseQuickAdapter<Navigation, BaseViewHolder> {

	private int currentPosition = 0;

	public NavigationTabAdapter(int layoutResId) {
		super(layoutResId);
	}

	public void setPosition(int position){
		this.currentPosition = position;
	}

	@Override
	protected void convert(@NonNull BaseViewHolder helper, Navigation item) {
		TextView textView = helper.getView(R.id.item_navigation_tab_tv);
		textView.setText(item.getName());

		int position = helper.getAdapterPosition();
		if(currentPosition == position){
			textView.setSelected(true);
		}else{
			textView.setSelected(false);
		}

	}
}
