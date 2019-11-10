package cn.shineiot.compontentpro.ui.integral;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.shineiot.base.bean.Coin;
import cn.shineiot.compontentpro.R;

/**
 * @author GF63
 * 积分排名
 */
public class IntegralAdapter extends BaseQuickAdapter<Coin, BaseViewHolder> {

	public IntegralAdapter(int layoutResId) {
		super(layoutResId);
	}

	@Override
	protected void convert(@NonNull BaseViewHolder helper, Coin item) {
		helper.setText(R.id.username,item.getUsername())
				.setText(R.id.coin,item.getCoinCount()+"(积分)")
				.setText(R.id.rank,String.valueOf(item.getRank()));
	}
}
