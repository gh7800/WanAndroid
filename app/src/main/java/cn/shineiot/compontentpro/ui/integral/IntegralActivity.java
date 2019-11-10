package cn.shineiot.compontentpro.ui.integral;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.maning.mndialoglibrary.MProgressDialog;

import java.util.List;

import butterknife.BindView;
import cn.shineiot.base.ARouterPath;
import cn.shineiot.base.bean.Coin;
import cn.shineiot.base.bean.Integral;
import cn.shineiot.base.module.BaseMvpActivity;
import cn.shineiot.base.utils.ToastUtils;
import cn.shineiot.compontentpro.R;

/**
 * @author GF63
 * 积分
 */
@Route(path = ARouterPath.INTEGRAL_ACTIVITY)
public class IntegralActivity extends BaseMvpActivity<IntegralView,IntegralPresenter>implements IntegralView {
	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.recyclerView)
	RecyclerView recyclerView;
	@BindView(R.id.coin)
	TextView textView;

	private IntegralAdapter adapter;

	@Override
	protected int provideLayoutId() {
		return R.layout.activity_integral;
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		setupToolbar_center(toolbar,"积分系统");
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
		adapter = new IntegralAdapter(R.layout.item_integral);
		recyclerView.setAdapter(adapter);

		presenter.getMyCoin();
		presenter.getListCoin();
	}

	@Override
	public IntegralPresenter initPresenter() {
		return new IntegralPresenter();
	}

	@Override
	public void succeessMyCoin(Coin coin) {
		String string = "我的积分："+coin.getCoinCount()+"\n"+"我的排名："+coin.getRank();
		textView.setText(string);
	}

	@Override
	public void successListCoin(Integral integral) {
		List<Coin> list = integral.getDatas();
		if(!list.isEmpty()){
			adapter.setNewData(list);
		}
	}

	@Override
	public void showLoading(String msg) {
		MProgressDialog.showProgress(mContext,msg);
	}

	@Override
	public void hideLoading() {
		MProgressDialog.dismissProgress();
	}

	@Override
	public void showError(String msg) {
		ToastUtils.showErrorToast(msg);
	}
}
